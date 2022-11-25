import { boot } from 'quasar/wrappers';
import axios, { AxiosInstance } from 'axios';
import { LocalStorage } from 'quasar';

declare module '@vue/runtime-core' {
  interface ComponentCustomProperties {
    $axios: AxiosInstance;
  }
}

const api = axios.create({
  baseURL: `http://${process.env.BACKEND_URL}/api`,
  withCredentials: false,
  headers: {
    Authorization: `Bearer ${LocalStorage.getItem('token')}`,
  },
});

const authApi = axios.create({
  baseURL: `http://${process.env.BACKEND_URL}/auth`,
});

authApi.defaults.headers.common['Content-Type'] = 'application/json';
api.defaults.headers.common['Content-Type'] = 'application/json';

export default boot(({ app, router }) => {
  // for use inside Vue files (Options API) through this.$axios and this.$api

  app.config.globalProperties.$axios = axios;
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = api;
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
});

export { api, authApi };
