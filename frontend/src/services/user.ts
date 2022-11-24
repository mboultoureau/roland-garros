import { api } from 'src/boot/axios';
import { Roles, User } from 'src/models/user';

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

export async function editU(user: User) {
  try {
    await api.put(`/users/${user.id}`, user);
  } catch (error) {}
}

export async function deleteU(id: number) {
  try {
    await api.delete(`users/${id}`);
  } catch (error) {}
}
