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
        ],
      },
    ],
    meta: {
      requireAuth: true,
    },
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
