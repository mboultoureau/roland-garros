import { defineStore } from 'pinia';
import { Match } from 'src/models/match';
import { Tournament, TournamentType, Round } from 'src/models/tournament';
import {
  fetch,
  store,
  destroy,
  show,
  getTournamentTypes,
} from 'src/services/tournament';

export const useTournamentStore = defineStore('tournament', {
  state: () => ({
    tournament: {} as { tournament: Tournament; matchs?: Match[] },
    listTournament: [] as Tournament[],
  }),
  getters: {
    getByFilter: (state) => (type: TournamentType, round: Round) =>
      state.tournament?.matchs.filter(
        (match: Match) => match.type === type && match.round === round
      ),
  },
  actions: {
    async fetch() {
      this.listTournament = await fetch();
    },
    async store(tournament: Tournament) {
      await store(tournament);
      await this.fetch();
    },
    async delete(id: number) {
      await destroy(id);
    },
    async show(id: number, type: TournamentType | null) {
      this.tournament = await show(id, type);
    },
    async getTournamentTypes(id: number) {
      this.tournament = await getTournamentTypes(id);
    },
  },
});
