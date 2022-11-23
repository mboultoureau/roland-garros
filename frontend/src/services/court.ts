import { api } from 'boot/axios';

export async function fetch(date: string) {
  try {
    const responses = await api.get('courts', { params: { date } });
    return responses.data;
  } catch (error) {}
}
