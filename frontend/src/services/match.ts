import { indexMock, showMock } from 'src/mocks/MatchMock';
import { MatchFilter } from 'src/models/match';

export async function fetch(filter: MatchFilter) {
  try {
    const matchs = await indexMock(filter);
    return matchs.payload;
  } catch (error) {}
}

export async function show(id: string | number) {
  try {
    const response = await showMock(id);
    return response.payload;
  } catch (error) {}
}
