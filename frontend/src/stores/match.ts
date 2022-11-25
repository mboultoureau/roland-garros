import { defineStore } from 'pinia';
import { Match, MatchEditFilter, MatchFilter } from 'src/models/match';
import { edit, fetch, show } from 'src/services/match';

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
    async edit(match: MatchEditFilter) {
      await edit(match);
    },
    set(match: Match) {
      this.match = match;
    },
    resetList() {
      this.listMatch = [];
      this.listMatchNextOrPrevious = [];
    },
  },
});
