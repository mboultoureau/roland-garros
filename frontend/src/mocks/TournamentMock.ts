import { uid } from 'quasar';
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

function initTournament() {
  if (matchs.length === 0) {
    addMatch(tournaments[0]);
    addMatch(tournaments[1]);
  }
  console.log(matchs);
}

initTournament();

export async function storeMock(tournament: Tournament) {
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

function addMatch(tournament: Tournament) {
  // 1er T => 64 matchs
  tournament.types.forEach((type) => {
    for (let i = 1; i <= 64; i++) {
      const teamA = {
        id: uid(),
        personA: listPlayer[1],
      } as Team;
      const teamB = {
        id: uid(),
        personA: listPlayer[2],
      } as Team;
      if (
        tournament.types === TournamentType.DOUBLE_MEN ||
        tournament.types === TournamentType.DOUBLE_WOMAN
      ) {
        (teamA.personB = listPlayer[3]), (teamB.personB = listPlayer[4]);
      }
      createMatch(
        tournament,
        type,
        Round.FIRST_ROUND,
        Status.PLANNED,
        teamA,
        teamB
      );
    }
    // 2eme tour
    for (let i = 1; i <= 32; i++) {
      createMatch(
        tournament,
        type,
        Round.SECOND_ROUND,
        Status.UNDEFINED,
        null,
        null
      );
    }
    // 3eme tour
    for (let i = 1; i <= 16; i++) {
      createMatch(
        tournament,
        type,
        Round.THIRD_ROUND,
        Status.UNDEFINED,
        null,
        null
      );
    }
    // 1/8 final
    for (let i = 1; i <= 8; i++) {
      createMatch(
        tournament,
        type,
        Round.SIXTEENTH_ROUND,
        Status.UNDEFINED,
        null,
        null
      );
    }
    // 1/4 final
    for (let i = 1; i <= 4; i++) {
      createMatch(
        tournament,
        type,
        Round.QUARTER_FINAL,
        Status.UNDEFINED,
        null,
        null
      );
    }
    // 1/2 final
    for (let i = 1; i <= 2; i++) {
      createMatch(
        tournament,
        type,
        Round.SEMI_FINAL,
        Status.UNDEFINED,
        null,
        null
      );
    }
    // finale
    createMatch(
      tournament,
      type,
      Round.FINAL_ROUND,
      Status.UNDEFINED,
      null,
      null
    );
  });
}

function createMatch(
  tournament: Tournament,
  type: TournamentType,
  round: Round,
  status: Status,
  teamA: Team | null,
  teamB: Team | null
) {
  const nbCourt = Math.floor(Math.random() * 4);
  matchs.push({
    court: courts[nbCourt],
    id: uid(),
    date: '25/02/2022',
    round: round,
    status: status,
    teamA: teamA,
    teamB: teamB,
    tournamentId: tournament.id,
    type: type,
    scores: [],
  } as Match);
}
