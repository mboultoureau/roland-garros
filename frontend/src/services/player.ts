import { FilterPlayer, Person } from 'src/models/person';

import {
  mockDelete,
  mockStore,
  mockEdit,
  mockShow,
} from 'src/mocks/PlayerMock';
import { api } from 'src/boot/axios';

export async function fetch(filter?: FilterPlayer) {
  try {
    const response = await api.get('players', { params: filter });
    return response.data;
  } catch (error) {}
}

export async function store(player: Person) {
  try {
    await api.post('persons', player);
  } catch (error) {}
}

export async function destroy(id: number) {
  try {
    await mockDelete(id);
  } catch (error) {}
}

export async function edit(player: Person) {
  console.log(player);
  try {
    await api.put(`persons/${player.id}`, player);
  } catch (error) {}
}

export async function show(id: number) {
  try {
    const response = await api.get(`persons/${id}`);
    return response.data;
  } catch (error) {}
}
