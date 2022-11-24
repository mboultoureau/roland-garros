import { defineStore } from 'pinia';
import { LocalStorage } from 'quasar';
import { Roles, User } from 'src/models/user';
import { fetch, fetchRole, editU, deleteU } from 'src/services/user';

export const useUserStore = defineStore('user', {
  state: () => ({
    isAuthenticated: false,
    user: {} as User,
    listUser: [] as User[],
    listRole: [],
  }),
  getters: {
    isAdmin: (state) => state.user.roles?.includes(Roles.ROLE_ADMIN) || false,
    isEditorPlayer: (state) =>
      state.user.roles?.includes(Roles.ROLE_EDITOR_PLAYER) || false,
    isEditorMatch: (state) =>
      state.user.roles?.includes(Roles.ROLE_EDITOR_MATCH) || false,
  },
  actions: {
    async fetch() {
      this.listUser = await fetch();
    },
    async fetchRole() {
      this.listRole = await fetchRole();
    },
    async delete(id: number) {
      await deleteU(id);
    },
    async edit(user: User) {
      await editU(user);
    },
    setUser(user: User) {
      this.user = user;
      this.isAuthenticated = true;
      LocalStorage.set('user', user);
    },
    logout() {
      this.user = {};
      this.isAuthenticated = false;
      LocalStorage.remove('user');
    },
  },
});
