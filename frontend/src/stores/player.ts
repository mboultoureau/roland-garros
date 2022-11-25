import { defineStore } from 'pinia';
import { FilterPlayer, Person } from 'src/models/person';
import { TournamentType } from 'src/models/tournament';
import {
  fetch,
  store,
  edit,
  destroy,
  show,
  fetchCoach,
  fetchAll,
  getPlayersByTournament,
} from 'src/services/player';

export const usePlayerStore = defineStore('player', {
  state: () => ({
    player: {} as Person,
    listPlayer: [] as Person[],
    listCoach: [] as Person[],
  }),
  getters: {
    getById: (state) => (id: number) =>
      state.listPlayer.find((player: Person) => player.id === id),
  },
  actions: {
    async fetchAll() {
      return await fetchAll();
    },
    async fetch(filter?: FilterPlayer) {
      const response = await fetch(filter);
      this.listPlayer = response.content;
    },
    async fetchCoach() {
      this.listCoach = await fetchCoach();
    },
    async store(player: Person) {
      await store(player);
    },
    async delete(id: number) {
      await destroy(id);
    },
    async editP(player: Person) {
      await edit(player);
    },
    async show(id: number) {
      this.player = await show(id);
    },
    async getPlayersByTournament(id: number, type: TournamentType) {
      this.listPlayer = await getPlayersByTournament(id, type);
    },
  },
});
