import { Person } from './person';

export enum TournamentType {
  SIMPLE_MEN,
  SIMPLE_WOMAN,
  DOUBLE_MEN,
  DOUBLE_WOMAN,
  MIXED,
}

export enum Status {
  IN_PROGRESS,
  PLANNED,
  UNDEFINED,
  FINISH,
}

export enum Round {
  FIRST_ROUND,
  SECOND_ROUND,
  THIRD_ROUND,
  SIXTEENTH_ROUND,
  QUARTER_FINAL,
  SEMI_FINAL,
  FINAL_ROUND,
}

export interface Team {
  id: number;
  personA: Person;
  personB?: Person;
}

export interface Tournament {
  id: number;
  year: number;
  types: TournamentType[];
  matchs?: Match[];
}

export interface Court {
  id: number;
  name: string;
}

export interface Match {
  id: number;
  tournamentId: number;
  type: TournamentType;
  status: Status;
  round: Round;
  teamA: Team;
  teamB: Team;
  date: string;
  court: Court;
}

export interface Training {
  id: number;
  player: Person;
  startDate: string;
  endDate: string;
  court: Court;
  details: string;
}

export interface TournamentFilter {
  type: TournamentType;
}
