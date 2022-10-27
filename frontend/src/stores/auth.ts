import { defineStore } from 'pinia';
import { User } from 'src/models/user';
import { login, logout, register } from 'src/services/auth';

export const useAuthStore = defineStore('auth', {
  state: () => ({}),

  actions: {
    async login(user: User) {
      await login(user)
    },
    async register(user: User) {
      await register(user)
    },
    logout() {
      logout()
    }
  }
});
