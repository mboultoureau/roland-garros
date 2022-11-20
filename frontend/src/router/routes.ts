import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/IndexPage.vue') }],
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('pages/user/Login.vue'),
  },
  {
    path: '/register',
    component: () => import('pages/user/Register.vue'),
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/admin/Index.vue') },
      {
        path: 'player',
        children: [
          {
            name: 'players',
            path: '',
            component: () => import('pages/admin/player/Index.vue'),
          },
          {
            path: 'new-player',
            name: 'new-player',
            component: () => import('pages/admin/player/NewPlayer.vue'),
          },
          {
            path: 'edit/:id',
            name: 'edit',
            component: () => import('pages/admin/player/UpdatePlayer.vue'),
          },
          {
            path: ':id',
            name: 'show-player-admin',
            component: () => import('pages/admin/player/ShowPlayer.vue'),
          },
        ],
      },
      {
        path: 'tournaments',
        children: [
          {
            path: '',
            component: () => import('pages/admin/tournament/Index.vue'),
          },
          {
            path: ':id',
            name: 'show-tournament-admin',
            component: () =>
              import('pages/admin/tournament/ShowTournament.vue'),
          },
          {
            path: ':idT/edit/:idM',
            name: 'edit-match-tournament',
            component: () => import('pages/admin/tournament/EditMatch.vue'),
          },
        ],
      },
    ],
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/player',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: ':id',
        name: 'show-player',
        component: () => import('pages/player/ShowPlayer.vue'),
      },
      {
        path: '',
        component: () => import('pages/player/Index.vue'),
      },
    ],
  },
  {
    path: '/tournaments',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        name: 'list-match',
        component: () => import('pages/tournament/Index.vue'),
      },
      {
        path: ':idT/matchs/:idM',
        name: 'show-match',
        component: () => import('pages/match/Index.vue'),
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
