import { defineStore } from 'pinia';
import { Court } from 'src/models/match';
import { fetch } from 'src/services/court';

export const useCourtStore = defineStore('court', {
  state: () => ({
    court: {} as Court,
    listCourt: [] as Court[],
  }),
  actions: {
    async fetch(date?: string) {
      this.listCourt = await fetch(date);
    },
  },
});
