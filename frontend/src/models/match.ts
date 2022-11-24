import { TournamentType, Round } from './tournament';
import { Person } from './person';
import { Court } from './court';

export enum Status {
  IN_PROGRESS = 'IN_PROGRESS',
  PLANNED = 'PLANNED',
  UNDEFINED = 'UNDEFINED',
  FINISH = 'FINISH',
}

export interface Score {
  id: number;
  set: number;
  scoreTeamA: number;
  scoreTeamB: number;
}

export interface Match {
  id: number | string;
  tournamentId: number;
  type: TournamentType;
  status: Status;
  round: Round;
  teamA?: Team;
  teamB?: Team;
  date?: string;
  court?: Court;
  scores?: Score[];
}

export interface Team {
  id: number | string;
  personA: Person;
  personB?: Person;
}

export interface MatchFilter {
  type: TournamentType;
  tournamentId: number;
  round: Round;
}

export interface MatchEditFilter {
  matchId: number | string;
  teamA: number | number[] | null;
  teamB: number | number[] | null;
  date: string | null;
  time: string | null;
  court: number | null;
}
