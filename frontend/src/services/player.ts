import { Player } from 'src/models/person';

import { mockDelete, mockFetch, mockStore } from 'src/mocks/PlayerMock';

export async function fetch() {
  try {
    const response = await mockFetch();
    return response.payload;
  } catch (error) {}
}

export async function store(player: Player) {
  try {
    await mockStore(player);
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await mockDelete(id);
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await mockDelete(id);
  } catch (error) {}
}
