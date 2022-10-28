import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/IndexPage.vue') }],
  },
  {
    path: '/login',
    component: () => import('pages/user/Login.vue'),
  },
  {
    path: '/register',
    component: () => import('pages/user/Register.vue'),
  },
  {
    path: '/admin',
    children: [{ path: '', component: () => import('pages/admin/Admin.vue') }],
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
