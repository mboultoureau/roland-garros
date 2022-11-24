import { Person } from './person';

export interface Court {
  id: number;
  name: string;
  trainings: Training[];
}

export interface Training {
  id: number;
  player: Person;
  startDate: string;
  duration: number;
  details: string;
}
