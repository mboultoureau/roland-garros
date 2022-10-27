import axios, { api } from "src/boot/axios";
import { User } from "src/models/user";

import useToken from 'src/helpers/auth-guard'

const { saveToken, deleteToken } = useToken()

export async function login(user: User) {
  console.log(user)
  // const response = await api.post('auth/login', user)
  // saveToken(response.data.payload.token)
}

export async function register(user: User) {
  console.log(user)
  //const response = await api.post('')
}

export function logout() {
  deleteToken()
}
