import { defineStore } from 'pinia';
import { User } from 'src/models/user';

export const useUserStore = defineStore('user', {
  state: () => ({
    isAuthenticated: 0,
    user: {} as User,
  }),
});

