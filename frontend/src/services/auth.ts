import { authApi } from 'src/boot/axios';
import { User } from 'src/models/user';

import { useToken } from 'src/helpers/auth-guard';

const { saveToken, deleteToken } = useToken();

export async function refreshToken() {
  const response = await authApi.get('auth/token');
  return response.data;
}

export async function login(user: User) {
  const response = await authApi.post('auth/login', user);
  saveToken(response.data.payload.token);
}

export async function register(user: User) {
  const response = await authApi.post('auth/register', user);
  saveToken(response.data);
}

export function logout() {
  deleteToken();
}
