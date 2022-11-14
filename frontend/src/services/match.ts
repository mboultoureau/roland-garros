import { indexMock } from 'src/mocks/MatchMock';
import { MatchFilter } from 'src/models/match';

export async function fetch(filter: MatchFilter) {
  try {
    const matchs = await indexMock(filter);
    return matchs.payload;
  } catch (error) {}
}
