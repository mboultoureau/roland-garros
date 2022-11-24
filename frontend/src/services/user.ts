import { api } from 'src/boot/axios';
import { Roles } from 'src/models/user';

export async function fetch() {
  try {
    const response = await api.get('/users');
    return response.data;
  } catch (error) {}
}

export async function fetchRole() {
  try {
    const response = await api.get('/roles');
    return response.data;
  } catch (error) {}
}

export async function editU(id: number, username: string, roles: Roles[]) {
  try {
    await api.put(`/users/${id}`, { username, roles });
  } catch (error) {}
}
