import { api } from 'boot/axios';
import { Training } from 'src/models/court';

export async function fetch(startWeek?: string) {
  try {
    const responses = await api.get('courts', { params: { startWeek } });
    return responses.data;
  } catch (error) {}
}

export async function storeTraining(trainings: Training) {
  try {
    await api.post('/trainings', trainings);
  } catch (error) {}
}
