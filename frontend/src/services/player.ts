import { Player } from 'src/models/person';

import {
  mockDelete,
  mockFetch,
  mockStore,
  mockEdit,
  mockShow,
} from 'src/mocks/PlayerMock';

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

export async function edit(player: Player) {
  try {
    await mockEdit(player);
  } catch (error) {}
}

export async function show(id: number) {
  try {
    const response = await mockShow(id);
    return response.payload as Player;
  } catch (error) {}
}
