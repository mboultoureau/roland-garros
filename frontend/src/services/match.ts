import { indexMock, showMock, editMock } from 'src/mocks/MatchMock';
import { MatchEditFilter, MatchFilter } from 'src/models/match';
import { api } from 'src/boot/axios';

export async function fetch(filter: MatchFilter) {
  try {
    const matchs = await api.get(`/tournaments/${filter.tournamentId}`, {
      params: filter,
    });
    return matchs.data.matches;
  } catch (error) {}
}

export async function show(id: string | number) {
  try {
    const response = await showMock(id);
    return response.payload;
  } catch (error) {}
}

export async function edit(matchEdit: MatchEditFilter) {
  try {
    const response = await editMock(matchEdit);
    return response.success;
  } catch (error) {}
}
