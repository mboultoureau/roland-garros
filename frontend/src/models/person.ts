export interface Person {
  id: number;
  gender: Gender;
  firstname: string;
  lastname: string;
  birthDate: string;
  birthPlace: string;
  nationality: Nationality;
  height: number;
  weight: number;
  picture: string;
  flag: string;
  ranking: number;
  hand: 'right' | 'left';
  coach: Person;
  isCoach: boolean;
  isPlayer: boolean;
  earlyCareer: string;
}

export enum Gender {
  MEN = 'MALE',
  WOMAN = 'FEMALE',
  OTHER = 'OTHER',
  AT_AT = 'AT_AT',
}

export interface Nationality {
  id?: number;
  name?: string;
  alpha2Code?: string;
  alpha3Code: string;
}

export interface FilterPlayer {
  gender?: Gender;
  sort?: null | string;
  sortBy?: null | 'asc' | 'desc';
}
