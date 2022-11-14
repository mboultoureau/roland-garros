import { MatchFilter, Match, Team, Court, Status } from 'src/models/match';
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

  return {
    success: true,
    payload: matchFIltered,
  };
}

export function addMatch(tournament: Tournament) {
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
