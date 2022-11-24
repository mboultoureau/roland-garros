import { Court } from 'src/models/court';

const courts = [
  {
    id: 1,
    name: 'Court Léane',
    trainings: [],
  },
  {
    id: 2,
    name: 'Court Mathis',
    trainings: [],
  },
  {
    id: 3,
    name: 'Court Sylvain',
    trainings: [],
  },
  {
    id: 4,
    name: 'Court Paul',
    trainings: [],
  },
] as Court[];

export async function fetchMock() {
  await new Promise((s) => setTimeout(s, 2000));
  return {
    success: true,
    payload: courts,
  };
}
