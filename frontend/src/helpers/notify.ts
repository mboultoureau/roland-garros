import { Notify } from 'quasar';

export function ErrorNotify() {
  Notify.create({
    message: "Erreur lors de l'authentification",
    color: 'red',
    position: 'top',
  });
}
