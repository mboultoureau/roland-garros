import { Person } from './person';

export enum TournamentType {
  SIMPLE_MEN = 'SIMPLE_MEN',
  SIMPLE_WOMAN = 'SIMPLE_WOMAN',
  DOUBLE_MEN = 'DOUBLE_MEN',
  DOUBLE_WOMAN = 'DOUBLE_WOMAN',
  MIXED = 'MIXED',
}

export const optionsTournamentTypes = [
  {
    value: TournamentType.SIMPLE_MEN,
  },
  {
    value: TournamentType.SIMPLE_WOMAN,
  },
  {
    value: TournamentType.DOUBLE_MEN,
  },
  {
    value: TournamentType.DOUBLE_WOMAN,
  },
  {
    value: TournamentType.MIXED,
  },
];

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
  id: number | string;
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

export interface TournamentFilter {
  type: TournamentType;
}
