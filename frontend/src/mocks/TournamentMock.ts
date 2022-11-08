import {
  Court,
  Match,
  Round,
  Status,
  Team,
  Tournament,
  TournamentType,
} from 'src/models/tournament';

import { listPlayer } from './PlayerMock';

const tournaments = [
  {
    id: 1,
    year: 2020,
    types: [TournamentType.DOUBLE_MEN, TournamentType.DOUBLE_WOMAN],
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

const courts = [
  {
    id: 1,
    name: 'Court Léane',
  },
  {
    id: 2,
    name: 'Court Mathis',
  },
  {
    id: 1,
    name: 'Court Sylvain',
  },
  {
    id: 4,
    name: 'Court Paul',
  },
] as Court[];

const matchs = [] as Match[];

export async function storeMock(tournament: Tournament) {
  createMatch(tournament);

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

function createMatch(tournament: Tournament) {
  // 1er T => 64 matchs
  tournament.types.forEach((type) => {
    for (let i = 1; i <= 64; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // 2eme tour
    for (let i = 1; i <= 32; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // 3eme tour
    for (let i = 1; i <= 16; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // 1/8 final
    for (let i = 1; i <= 8; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // 1/4 final
    for (let i = 1; i <= 4; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // 1/2 final
    for (let i = 1; i <= 2; i++) {
      const teamA = {
        id: i,
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: i,
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      matchs.push({
        court: courts[i % 4],
        id: i,
        date: '25/02/2022',
        round: Round.FIRST_ROUND,
        status: Status.PLANNED,
        teamA: teamA,
        teamB: teamB,
        tournamentId: tournament.id,
        type: type,
      } as Match);
    }
    // finale
    const teamA = {
      id: 123456879,
      personA: listPlayer[1],
    } as Team;
    const teamB = {
      id: 456789123,
      personA: listPlayer[2],
    } as Team;
    if (
      tournament.types === TournamentType.DOUBLE_MEN ||
      tournament.types === TournamentType.DOUBLE_WOMAN
    ) {
      (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
    }
    matchs.push({
      court: courts[3],
      id: 4561596753,
      date: '25/02/2022',
      round: Round.FIRST_ROUND,
      status: Status.PLANNED,
      teamA: teamA,
      teamB: teamB,
      tournamentId: tournament.id,
      type: type,
    } as Match);
  });
}
