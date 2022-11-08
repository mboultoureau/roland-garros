import { defineStore } from 'pinia';
import { Tournament, TournamentType } from 'src/models/tournament';
import { fetch, store, destroy, show } from 'src/services/tournament';

export const useTournamentStore = defineStore('tournament', {
  state: () => ({
    tournament: {} as Tournament,
    listTournament: [] as Tournament[],
  }),
  actions: {
    async fetch() {
      this.listTournament = await fetch();
    },
    async store(tournament: Tournament) {
      await store(tournament);
    },
    async delete(id: number) {
      await destroy(id);
    },
    async show(id: number, type: TournamentType | null) {
      this.tournament = await show(id, type);
    },
  },
});
