import { api } from 'boot/axios';

export async function fetch(startWeek?: string) {
  try {
    const responses = await api.get('courts', { params: { startWeek } });
    return responses.data;
  } catch (error) {}
}
