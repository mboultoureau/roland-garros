import { defineStore } from 'pinia';
import { Player } from 'src/models/person';
import { fetch, store, edit, destroy } from 'src/services/player';

export const usePlayerStore = defineStore('player', {
  state: () => ({
    player: {} as Player,
    listPlayer: [] as Player[],
  }),
  getters: {
    getById: (state) => (id: number) =>
      state.listPlayer.find((player: Player) => player.id === id),
  },
  actions: {
    async fetch() {
      this.listPlayer = await fetch();
    },
    async store(player: Player) {
      await store(player);
    },
    async delete(id: number) {
      await destroy(id);
    },
    async edit(player: Player) {
      await edit(player);
    },
  },
});
