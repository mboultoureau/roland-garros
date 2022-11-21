import {
  indexMock,
  showMock,
  destroyMock,
  storeMock,
} from 'src/mocks/TournamentMock';
import { Tournament, TournamentType } from 'src/models/tournament';

export async function fetch() {
  try {
    const tournaments = await indexMock();
    return tournaments.payload;
  } catch (error) {}
}

export async function show(id: number, type: TournamentType | null) {
  try {
    const tournament = await showMock(id, type);
    return tournament.payload;
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await destroyMock(id);
  } catch (error) {}
}

export async function store(tournament: Tournament) {
  try {
    await storeMock(tournament);
  } catch (error) {}
}
