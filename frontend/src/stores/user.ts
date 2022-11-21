import { defineStore } from 'pinia';
import { LocalStorage } from 'quasar';
import { Roles, User } from 'src/models/user';

export const useUserStore = defineStore('user', {
  state: () => ({
    isAuthenticated: 0,
    user: {} as User,
  }),
  getters: {
    isAdmin: (state) => state.user.roles?.includes(Roles.ROLE_ADMIN) || false,
    isEditorPlayer: (state) =>
      state.user.roles?.includes(Roles.ROLE_EDITOR_PLAYER) || false,
    isEditorMatch: (state) =>
      state.user.roles?.includes(Roles.ROLE_EDITOR_MATCH) || false,
  },
  actions: {
    setUser(user: User) {
      this.user = user;
      LocalStorage.set('user', user);
    },
    logout() {
      this.user = {};
      LocalStorage.remove('user');
    },
  },
});
