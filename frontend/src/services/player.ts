import { FilterPlayer, Person } from 'src/models/person';

import {
  mockDelete,
  mockStore,
  mockEdit,
  mockShow,
} from 'src/mocks/PlayerMock';
import { api } from 'src/boot/axios';
import { ErrorNotify } from 'src/helpers/notify';

export async function fetchAll() {
  try {
    const response = await api.get('/players?size=1000');
    return response.data.content;
  } catch (error) {}
}

export async function fetch(filter?: FilterPlayer) {
  try {
    let sortApi = '';
    if (filter?.sort) {
      sortApi = `&sort=${filter.sort}`;
      if (filter.sortBy) {
        sortApi = `&sort=${filter.sort},${filter.sortBy}`;
      }
    }
    const response = await api.get(
      `players?gender=${filter?.gender}${sortApi}`
    );
    return response.data;
  } catch (error) {}
}

export async function fetchCoach() {
  try {
    const response = await api.get('/coaches');
    return response.data.content;
  } catch (error) {}
}

export async function store(player: Person) {
  const playerEdit = {
    firstname: player.firstname,
    lastname: player.lastname,
    birthDate: player.birthDate,
    birthPlace: player.birthPlace,
    hand: player.hand,
    height: parseInt(player.height),
    weight: parseInt(player.weight),
    earlyCareer: player.earlyCareer,
    coachId: player.coach.id,
    picture: player.picture,
    isPlayer: player.isPlayer,
    isCoach: player.isCoach,
    gender: player.gender,
    ranking: player.ranking,
  };

  if (typeof player.nationality === 'object') {
    playerEdit.nationality = player.nationality;
  } else {
    playerEdit.nationalityId = player.nationality;
  }
  try {
    await api.post('persons', playerEdit);
  } catch (error) {
    ErrorNotify();
  }
}

export async function destroy(id: number) {
  try {
    await api.delete(`persons/${id}`);
  } catch (error) {}
}

export async function edit(player: Person) {
  const playerEdit = {
    firstname: player.firstname,
    lastname: player.lastname,
    birthDate: player.birthDate,
    birthPlace: player.birthPlace,
    hand: player.hand,
    height: parseInt(player.height),
    weight: parseInt(player.weight),
    earlyCareer: player.earlyCareer,
    coachId: player.coach.id,
    picture: player.picture,
    isPlayer: player.isPlayer,
    isCoach: player.isCoach,
    gender: player.gender,
    ranking: player.ranking,
  };

  if (typeof player.nationality === 'object') {
    playerEdit.nationality = player.nationality;
  } else {
    playerEdit.nationalityId = player.nationality;
  }
  try {
    await api.put(`persons/${player.id}`, playerEdit);
  } catch (error) {
    ErrorNotify();
  }
}

export async function show(id: number) {
  try {
    const response = await api.get(`persons/${id}`);
    return response.data;
  } catch (error) {}
}
