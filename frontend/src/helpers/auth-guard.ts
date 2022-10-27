import { LocalStorage } from 'quasar';

export function authGuard() {
  const token = LocalStorage.getItem('token')

  if(token) {
    return true
  } else {
    return false
  }
}

export default function useToken() {
  const saveToken = (token: string) => LocalStorage.set('token', token)

  const deleteToken = () => LocalStorage.remove('token')

  return {
    saveToken,
    deleteToken
  }
} 