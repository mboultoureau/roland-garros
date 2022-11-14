import { TournamentType, Round } from './tournament';
import { Person } from './person';

export interface Court {
  id: number;
  name: string;
}

export enum Status {
  IN_PROGRESS,
  PLANNED,
  UNDEFINED,
  FINISH,
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
  teamA: Team;
  teamB: Team;
  date: string;
  court: Court;
  scores: Score[];
}

export interface Training {
  id: number;
  player: Person;
  startDate: string;
  endDate: string;
  court: Court;
  details: string;
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
