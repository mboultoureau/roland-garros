import { Match } from './match';

export enum TournamentType {
  SIMPLE_MEN = 'SIMPLE_MEN',
  SIMPLE_WOMEN = 'SIMPLE_WOMEN',
  DOUBLE_MEN = 'DOUBLE_MEN',
  DOUBLE_WOMAN = 'DOUBLE_WOMAN',
  MIXED = 'MIXED',
}

export const optionsTournamentTypes = [
  {
    value: TournamentType.SIMPLE_MEN,
  },
  {
    value: TournamentType.SIMPLE_WOMEN,
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

export enum Round {
  FIRST_ROUND = 'FIRST_ROUND',
  SECOND_ROUND = 'SECOND_ROUND',
  THIRD_ROUND = 'THIRD_ROUND',
  SIXTEENTH_ROUND = 'SIXTEENTH_ROUND',
  QUART_FINAL = 'QUART_FINAL',
  SEMI_FINAL = 'SEMI_FINAL',
  FINAL_ROUND = 'FINAL_ROUND',
}

export interface Tournament {
  id: number;
  year: number;
  types: TournamentType[];
  matchs?: Match[];
}
