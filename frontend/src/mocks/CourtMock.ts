import { Court } from 'src/models/match';

const courts = [
  {
    id: 1,
    name: 'Court Léane',
  },
  {
    id: 2,
    name: 'Court Mathis',
  },
  {
    id: 3,
    name: 'Court Sylvain',
  },
  {
    id: 4,
    name: 'Court Paul',
  },
] as Court[];

export async function fetchMock() {
  await new Promise((s) => setTimeout(s, 2000));
  return {
    success: true,
    payload: courts,
  };
}
