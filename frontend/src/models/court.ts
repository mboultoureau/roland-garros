import { Person } from './person';

export interface Court {
  id: number;
  name: string;
  trainings: Training[];
}

export interface Training {
  id: number;
  person: Person;
  startDate: string;
  endDate: string;
  cour: Court;
  details: string;
}
