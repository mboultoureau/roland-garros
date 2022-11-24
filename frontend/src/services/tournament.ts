import {
  indexMock,
  showMock,
  destroyMock,
  storeMock,
} from 'src/mocks/TournamentMock';
import { Tournament, TournamentType } from 'src/models/tournament';
import { api } from 'boot/axios';

export async function fetch() {
  try {
    const tournaments = await api.get('/tournaments');
    return tournaments.data;
  } catch (error) {}
}

export async function show(id: number, type: TournamentType | null) {
  try {
    const tournament = await api.get(`/tournaments/${id}`, {
      params: { type },
    });
    return tournament.data;
  } catch (error) {}
}

export async function getTournamentTypes(id: number) {
  try {
    const response = await api.get(`tournament/${id}`);
    return response.data[0];
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await destroyMock(id);
  } catch (error) {}
}

export async function store(tournament: Tournament) {
  try {
    await api.post('/tournaments', tournament);
  } catch (error) {}
}
