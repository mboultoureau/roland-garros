import { Api } from 'src/models/api';
import { Player } from 'src/models/person';

/**
 * Create list player
 */
const listPlayer = [] as Player[];

for (let i = 0; i < 10; i++) {
  const player = {
    id: i,
    firstname: 'First name ' + i,
    lastname: 'Last name ' + i,
    birthday: i + '/01/2000',
    birthplace: 'FRANCE',
    hand: 'right',
    height: i,
    weight: i * 100,
    'career-start': '12/09/2012',
    coach: {} as Player,
    nationality: 'FRANCE',
    ranking: 5,
  } as Player;
  listPlayer.push(player);
}

export const mockFetch = async (): Promise<Api> => {
  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: true,
    payload: listPlayer,
  };
};

export const show = async (id: number): Promise<Api> => {
  const player = listPlayer.find((player: Player) => player.id === id);

  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: player === undefined ? false : true,
    payload: player === undefined ? {} : player,
  };
};

export const mockStore = async (player: Player): Promise<Api> => {
  const lastId = listPlayer.length;

  listPlayer.push({ ...player, id: lastId });
  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: true,
    payload: {},
  };
};

export const mockDelete = async (id: number): Promise<Api> => {
  const isFind = listPlayer.findIndex((player: Player) => player.id === id);
  listPlayer.splice(isFind, 1);

  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: isFind === undefined ? false : true,
    payload: {},
  };
};

export const mockEdit = async (editPlayer: Player): Promise<Api> => {
  const isFind = listPlayer.findIndex(
    (player: Player) => player.id === editPlayer.id
  );
  listPlayer.splice(isFind, 1);
  listPlayer.splice(isFind, 0, editPlayer);

  await new Promise((s) => setTimeout(s, 2000));

  return {
    success: isFind === undefined ? false : true,
    payload: {},
  };
};
