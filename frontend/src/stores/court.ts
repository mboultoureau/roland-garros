import { defineStore } from 'pinia';
import { Training } from 'src/models/court';
import { Court } from 'src/models/match';
import { fetch, storeTraining } from 'src/services/court';

export const useCourtStore = defineStore('court', {
  state: () => ({
    court: {} as Court,
    listCourt: [] as Court[],
  }),
  actions: {
    async fetch(date?: string) {
      this.listCourt = await fetch(date);
    },
    async postTraining(trainings: Training) {
      await storeTraining(trainings);
    },
  },
});
