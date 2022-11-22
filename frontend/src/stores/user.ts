import { defineStore } from 'pinia';
import { LocalStorage } from 'quasar';
import { Roles, User } from 'src/models/user';
import { fetch, fetchRole, editU } from 'src/services/user';

export const useUserStore = defineStore('user', {
  state: () => ({
    isAuthenticated: 0,
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
    async edit(id: number, username: string, roles: Roles[]) {
      await editU(id, username, roles);
    },
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
