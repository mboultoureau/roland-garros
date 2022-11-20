import { defineStore } from 'pinia';
import { Match, MatchFilter } from 'src/models/match';
import { fetch, show } from 'src/services/match';

export const useMatchStore = defineStore('match', {
  state: () => ({
    match: {} as Match,
    listMatch: [] as Match[],
    listMatchNextOrPrevious: [] as Match[],
  }),
  actions: {
    async fetch(filter: MatchFilter, next = false) {
      if (next) {
        this.listMatchNextOrPrevious = await fetch(filter);
      } else {
        this.listMatch = await fetch(filter);
      }
    },
    async show(id: string | number) {
      this.match = await show(id);
    },
    async edit(match: Match) {
      console.log(match);
    },
    set(match: Match) {
      this.match = match;
    },
  },
});
