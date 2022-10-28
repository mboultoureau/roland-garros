import { boot } from 'quasar/wrappers';
import axios, { AxiosInstance } from 'axios';
import { refreshToken } from 'src/services/auth';

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
  }
}

const api = axios.create({
  baseURL: 'http://localhost:8000/api',
});

const authApi = axios.create({
  baseURL: 'http://localhost:8000/',
  withCredentials: true,
});

authApi.defaults.headers.common['Content-Type'] = 'application/json';

/**
 * Incterceptor
 */
authApi.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;
    const errMessage = error.response.data.message as string;
    if (errMessage.includes('not logged in') && !originalRequest._retry) {
      originalRequest._retry = true;
      await refreshToken();
      return authApi(originalRequest);
    }
    return Promise.reject(error);
  }
);

export default boot(({ app }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios;
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api;
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
});

export { api, authApi };
