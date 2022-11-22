import { defineStore } from 'pinia';
import { LocalStorage } from 'quasar';
import { Nationality } from 'src/models/person';
import { fetch } from 'src/services/country';

export const useCountryStore = defineStore('country', {
  state: () => ({
    listNationality: [] as Nationality[],
  }),
  getters: {
    getListNationality: () =>
      localStorage.getItem('nationality') || ([] as Nationality[]),
  },
  actions: {
    async fetch() {
      const response = await fetch();
      this.listNationality = response;
      LocalStorage.set('nationality', response);
    },
  },
});
