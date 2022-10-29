import { api } from 'boot/axios';
import { Player } from 'src/models/person';
import { mockFetch, mockDelete } from 'src/mocks/PlayerMock';

export async function fetch() {
  try {
    const response = await mockFetch();
    return response.payload;
  } catch (error) {}
}

export async function store(player: Player) {
  try {
    const response = api.post('player', player);
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await mockDelete(id);
  } catch (error) {}
}
