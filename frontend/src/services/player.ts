import { api } from 'boot/axios';
import { Player } from 'src/models/person';

export async function fetch() {
  try {
    const response = await api.get('player');
    return response.data.payload;
  } catch (error) {}
}

export async function store(player: Player) {
  try {
    const response = api.post('player', player);
  } catch (error) {}
}
