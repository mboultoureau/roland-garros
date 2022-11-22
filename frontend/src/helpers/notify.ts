import { Notify } from 'quasar';

export function ErrorNotify() {
  Notify.create({
    message: "Erreur lors de l'authentification",
    color: 'red',
    position: 'top',
  });
}

export function CommonError() {
  Notify.create({
    message: 'Erreur est survenu',
    color: 'red',
    position: 'top-right',
  });
}

export function AuthentificationError() {
  Notify.create({
    message: "Vous n'êtes pas autorisé",
    color: 'red',
    position: 'top-right',
  });
}
