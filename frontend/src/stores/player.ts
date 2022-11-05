import { defineStore } from 'pinia';
import { FilterPlayer, Player } from 'src/models/person';
import { fetch, store, edit, destroy, show } from 'src/services/player';

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
    async fetch(filter: FilterPlayer) {
      this.listPlayer = await fetch(filter);
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
    async show(id: number) {
      this.player = await show(id);
    },
  },
});
