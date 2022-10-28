export interface Player extends Person {
  ranking: number;
  hand: 'Gauche' | 'Droite';
  'career-start': string;
  coach: Player;
}

export interface Person {
  firstname: string;
  lastname: string;
  birthday: string;
  birthplace: string;
  nationality: string;
  height: number;
  weight: number;
}
