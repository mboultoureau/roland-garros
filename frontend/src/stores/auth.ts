import { defineStore } from 'pinia';
import { stringify } from 'querystring';
import { useToken } from 'src/helpers/auth-guard';
import { User } from 'src/models/user';
import { login, logout, register } from 'src/services/auth';

const { hasToken } = useToken();

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: false,
  }),
  getters: {
    getToken: (state) => {
      return state.token || hasToken;
    },
  },
  actions: {
    async login(user: User) {
      return await login(user);
    },
    async register(user: User) {
      await register(user);
    },
    logout() {
      logout();
    },

    setToken(token: string) {
      if (token) this.token = true;
    },
  },
});
