import { api } from 'src/boot/axios';

export async function fetch() {
  try {
    const response = await api.get('countries');
    return response.data;
  } catch (error) {}
}
