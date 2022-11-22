import { authApi } from 'src/boot/axios';
import { User } from 'src/models/user';

import { useToken } from 'src/helpers/auth-guard';
import { ErrorNotify } from 'src/helpers/notify';
import { useAuthStore } from 'src/stores/auth';

const { saveToken, deleteToken } = useToken();

export async function refreshToken() {
  const authStore = useAuthStore();
  const response = await authApi.get('token');
  authStore.setToken(response.data.accessToken);
  saveToken(response.data.accessToken);
}

export async function login(user: User) {
  const authStore = useAuthStore();
  try {
    const response = await authApi.post('login', user);
    authStore.setToken(response.data.accessToken);
    saveToken(response.data.accessToken);
    return response.data;
  } catch (error) {
    ErrorNotify();
  }
}

export async function register(user: User) {
  const response = await authApi.post('register', user);
  saveToken(response.data);
}

export function logout() {
  deleteToken();
}
