import { fetchMock } from 'src/mocks/CourtMock';

export async function fetch() {
  try {
    const responses = await fetchMock();
    return responses.payload;
  } catch (error) {}
}
