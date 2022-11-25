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
    const response = await api.get(`/matches/${id}`);
    return response.data;
  } catch (error) {}
}

export async function edit(matchEdit: MatchEditFilter) {
  try {
    const response = await api.put(
      `/matches/${matchEdit.matchId || matchEdit.id}`,
      matchEdit
    );
    return response;
  } catch (error) {}
}
