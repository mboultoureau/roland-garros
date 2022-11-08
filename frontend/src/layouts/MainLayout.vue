<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar class="bg-white">

        <q-toolbar-title>
          <img class="w-20 my-2" src="https://upload.wikimedia.org/wikipedia/fr/thumb/1/1d/Logo_Roland-Garros.svg/350px-Logo_Roland-Garros.svg.png?20120514221714" />
        </q-toolbar-title>

        <q-btn flat class="mx-8">Tournois</q-btn>
        <q-btn flat >Joueurs</q-btn>
        <q-btn flat class="mx-12">Infos & Billets</q-btn>


        <q-btn push color="orange" label="Connexion">
              <q-menu>
                <div class="row no-wrap q-pa-md">
                  <div class="column justify-center">
                    <div>Entraineur</div>
                  </div>

                  <q-separator vertical inset class="q-mx-lg" />

                  <div class="column items-center">
                    <q-avatar size="72px">
                      <img src="https://cdn.quasar.dev/img/avatar4.jpg">
                    </q-avatar>

                    <div class="text-subtitle1 q-mt-md q-mb-xs">John Doe</div>

                    <q-btn
                      color="primary"
                      label="Logout"
                      push
                      size="sm"
                      v-close-popup
                    />
                  </div>
                </div>
              </q-menu>
            </q-btn>
      </q-toolbar>
    </q-header>



    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

