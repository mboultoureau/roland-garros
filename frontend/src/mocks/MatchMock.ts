import {
  MatchFilter,
  Match,
  Team,
  Court,
  Status,
  Score,
  MatchEditFilter,
} from 'src/models/match';
import { Tournament, TournamentType, Round } from 'src/models/tournament';
import { listPlayer } from './PlayerMock';
import { uid } from 'quasar';

export const matchs = [] as Match[];

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

export async function indexMock(filter: MatchFilter) {
  const matchFIltered = matchs.filter(
    (m) =>
      m.tournamentId === filter.tournamentId &&
      m.round === filter.round &&
      m.type === filter.type
  );

  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: true,
    payload: matchFIltered,
  };
}

export async function showMock(id: string | number) {
  await new Promise((s) => setTimeout(s, 2000));
  const match = matchs.find((m) => m.id == id);

  return {
    success: match ? true : false,
    payload: match,
  };
}

export async function editMock(matchEdit: MatchEditFilter) {
  const matchFund = matchs.find((match) => match.id == matchEdit.matchId);
  await new Promise((s) => setTimeout(s, 2000));
  return {
    success: matchFund === undefined ? false : true,
    payload: null,
  };
}

export function addMatch(tournament: Tournament) {
  // 1er T => 64 matchs
  tournament.types.forEach((type) => {
    const score = [
      {
        id: 1,
        set: 1,
        scoreTeamA: 4,
        scoreTeamB: 6,
      },
      {
        id: 2,
        set: 2,
        scoreTeamA: 4,
        scoreTeamB: 6,
      },
      {
        id: 3,
        set: 3,
        scoreTeamA: 6,
        scoreTeamB: 4,
      },
      {
        id: 4,
        set: 4,
        scoreTeamA: 4,
        scoreTeamB: 6,
      },
    ] as Score[];

    const randA = Math.floor(Math.random() * 4);
    const randB = Math.floor(Math.random() * 4);
    const teamA = {
      id: uid(),
      personA: listPlayer[randA],
    } as Team;
    const teamB = {
      id: uid(),
      personA: listPlayer[randB],
    } as Team;
    if (
      type === TournamentType.DOUBLE_MEN ||
      type === TournamentType.DOUBLE_WOMAN
    ) {
      teamA.personB = listPlayer[3];
      teamB.personB = listPlayer[4];
    }
    for (let i = 1; i <= 64; i++) {
      createMatch(
        tournament,
        type,
        Round.FIRST_ROUND,
        Status.PLANNED,
        teamA,
        teamB,
        score
      );
    }
    // 2eme tour
    for (let i = 1; i <= 32; i++) {
      createMatch(
        tournament,
        type,
        Round.SECOND_ROUND,
        Status.UNDEFINED,
        teamA,
        teamB,
        score
      );
    }
    // 3eme tour
    for (let i = 1; i <= 16; i++) {
      createMatch(
        tournament,
        type,
        Round.THIRD_ROUND,
        Status.UNDEFINED,
        teamA,
        teamB,
        score
      );
    }
    // 1/8 final
    for (let i = 1; i <= 8; i++) {
      createMatch(
        tournament,
        type,
        Round.SIXTEENTH_ROUND,
        Status.UNDEFINED,
        teamA,
        teamB,
        score
      );
    }
    // 1/4 final
    for (let i = 1; i <= 4; i++) {
      createMatch(
        tournament,
        type,
        Round.QUART_FINAL,
        Status.UNDEFINED,
        teamA,
        teamB,
        score
      );
    }
    // 1/2 final
    for (let i = 1; i <= 2; i++) {
      createMatch(
        tournament,
        type,
        Round.SEMI_FINAL,
        Status.UNDEFINED,
        teamA,
        teamB,
        score
      );
    }
    // finale
    createMatch(
      tournament,
      type,
      Round.FINAL_ROUND,
      Status.UNDEFINED,
      teamA,
      teamB,
      score
    );
  });
}

function createMatch(
  tournament: Tournament,
  type: TournamentType,
  round: Round,
  status: Status,
  teamA: Team | null,
  teamB: Team | null,
  score: Score[]
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
    scores: score,
  } as Match);
}
