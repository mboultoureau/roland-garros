import { LocalStorage } from 'quasar';

export function useToken() {
  const saveToken = (token: string) => {
    LocalStorage.set('token', token);
  };

  const deleteToken = () => LocalStorage.remove('token');

  const hasToken = LocalStorage.has('token');

  return {
    saveToken,
    deleteToken,
    hasToken,
  };
}

export default {
  useToken,
};
