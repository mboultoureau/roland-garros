import { Tournament, TournamentType } from 'src/models/tournament';

import { addMatch, matchs } from './MatchMock';

export const tournaments = [
  {
    id: 1,
    year: 2020,
    types: [TournamentType.SIMPLE_MEN, TournamentType.DOUBLE_WOMAN],
  },
  {
    id: 2,
    year: 2021,
    types: [
      TournamentType.DOUBLE_MEN,
      TournamentType.DOUBLE_WOMAN,
      TournamentType.SIMPLE_MEN,
      TournamentType.MIXED,
    ],
  },
] as Tournament[];

export function initMatchTournament() {
  if (matchs.length === 0) {
    addMatch(tournaments[0]);
    addMatch(tournaments[1]);
  }
}

initMatchTournament();

export async function storeMock(tournament: Tournament) {
  tournament.id = tournaments.length;
  addMatch(tournament);
  await new Promise((s) => setTimeout(s, 2000));

  tournaments.push(tournament);

  return {
    success: true,
    payload: [],
  };
}

export async function indexMock() {
  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: true,
    payload: tournaments,
  };
}

export async function destroyMock(id: number) {
  const indexTournament = tournaments.findIndex((t) => t.id === id);
  tournaments.slice(indexTournament, 1);
  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: indexTournament ? true : false,
    payload: [],
  };
}

export async function showMock(id: number, type: TournamentType | null) {
  const tournament = tournaments.find((t) => t.id === id);
  let matchOfThisTour = matchs.filter((m) => m.tournamentId === id);

  if (type) {
    matchOfThisTour = matchOfThisTour.filter((m) => m.type === type);
  }

  return {
    success: true,
    payload: {
      tournament,
      matchs: matchOfThisTour,
    },
  };
}
