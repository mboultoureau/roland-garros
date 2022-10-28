import { defineStore } from 'pinia';
import { Player } from 'src/models/person';

export const usePlayerStore = defineStore('player', {
  state: () => ({
    player: {} as Player,
    'list-player': [] as Player[],
  }),
  actions: {
    async fetch() {},
  },
});
