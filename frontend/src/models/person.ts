export interface Player extends Person {
  ranking: number;
  hand: 'right' | 'left';
  'career-start': string;
  coach: Player;
}

export interface Person {
  id: number;
  firstname: string;
  lastname: string;
  birthday: string;
  birthplace: string;
  nationality: string;
  height: number;
  weight: number;
  url: string;
  flag: string;
}
