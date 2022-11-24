export interface User {
  id: number;
  username?: string;
  firstname?: string;
  lastname?: string;
  password?: string;
  roles?: Roles[];
  email?: string;
}

export enum Roles {
  ROLE_EDITOR_PLAYER = 'ROLE_EDITOR_PLAYER',
  ROLE_EDITOR_MATCH = 'ROLE_EDITOR_MATCH',
  ROLE_ADMIN = 'ROLE_ADMIN',
  ROLE_USER = 'ROLE_USER',
}
