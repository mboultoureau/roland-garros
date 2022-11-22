import { Api } from 'src/models/api';
import { Player } from 'src/models/person';

/**
 * Create list player
 */
export const listPlayer = [] as Player[];

const tabFlag = ['ARG', 'AUS', 'FRA', 'NOR', 'TPE', 'ITA'];
const tabFN = ['Léane', 'Mathis', 'Sylvain', 'Paul'];
const tabLN = ['Diraison', 'Boultoureau', 'Mestre', 'Breton'];
const url = [
  'https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400',
  'https://images.prismic.io/fft-rg-site/6bbfe32a-28dd-4c05-9499-fd96dd1b10d0_20200119_PJ_TsitsipasStephanos_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400',
  'https://images.prismic.io/fft-rg-site/0d0bedfb-6ff3-442d-adda-05796c81df8b_20190113_PJ_DjokovicNovak_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400',
  'https://images.prismic.io/fft-rg-site/b176471a-ad08-44ab-9619-ced4ce74a0b6_20220310_PJ_AugerAliassimeFelix_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400',
  'https://images.prismic.io/fft-rg-site/f042ee8b-8b20-4e36-9ee7-e1018210759e_20220307_PJ_HurkaczHubert_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400',
];

for (let i = 0; i < 10; i++) {
  const player = {
    id: i,
    firstname: tabFN[i % 4],
    lastname: tabLN[i % 4],
    birthday: i + 1 + '/01/2000',
    birthplace: 'FRANCE',
    hand: 'right',
    height: i,
    weight: i * 100,
    'career-start': '12/09/2012',
    coach: {} as Player,
    nationality: 'FRANCE',
    ranking: i + 1,
    flag: tabFlag[i % 6],
    url: url[i % 5],
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

export const mockShow = async (id: number): Promise<Api> => {
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
