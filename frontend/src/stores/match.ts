import { defineStore } from 'pinia';
import { Match, MatchFilter } from 'src/models/match';
import { fetch } from 'src/services/match';

export const useMatchStore = defineStore('match', {
  state: () => ({
    match: {} as Match,
    listMatch: [] as Match[],
  }),
  actions: {
    async fetch(filter: MatchFilter) {
      this.listMatch = await fetch(filter);
    },
  },
});
