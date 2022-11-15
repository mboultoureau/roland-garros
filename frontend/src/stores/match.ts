import { defineStore } from 'pinia';
import { Match, MatchFilter } from 'src/models/match';
import { fetch, show } from 'src/services/match';

export const useMatchStore = defineStore('match', {
  state: () => ({
    match: {} as Match,
    listMatch: [] as Match[],
  }),
  actions: {
    async fetch(filter: MatchFilter) {
      this.listMatch = await fetch(filter);
    },
    async show(id: string) {
      this.match = await show(id);
    },
  },
});
