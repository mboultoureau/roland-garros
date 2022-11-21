import { useAuthStore } from 'src/stores/auth';
import { useUserStore } from 'src/stores/user';
import { AuthentificationError } from 'src/helpers/notify';
import { Roles } from 'src/models/user';

export default ({ router }) => {
  const authStore = useAuthStore();
  const userStore = useUserStore();
  // Avant de rediriger le visiteur
  router.beforeEach((to, from, next) => {
    if (to.matched.some((record) => record.meta.requireAuth)) {
      if (authStore.getToken) {
        if (
          (to.meta.rolePermit.includes(Roles.ROLE_EDITOR_MATCH) &&
            (userStore.isEditorMatch || userStore.isAdmin)) ||
          (to.meta.rolePermit.includes(Roles.ROLE_EDITOR_PLAYER) &&
            (userStore.isEditorPlayer || userStore.isAdmin))
        ) {
          next();
        } else {
          next(from);
          AuthentificationError();
        }
      } else {
        next({ name: 'login', query: { redirect: to.fullPath } });
      }
    } else {
      next();
    }
  });
};
