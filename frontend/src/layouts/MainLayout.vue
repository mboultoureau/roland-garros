<template>
  <q-layout>
    <q-header class="sticky top-0">
      <q-toolbar class="bg-white">
        <q-toolbar-title>
          <img
            class="w-20 my-2"
            src="https://upload.wikimedia.org/wikipedia/fr/thumb/1/1d/Logo_Roland-Garros.svg/350px-Logo_Roland-Garros.svg.png?20120514221714"
          />
        </q-toolbar-title>

        <q-btn
          flat
          @click="handleTournament"
          class="text-black mx-8 hover:font-bold"
          >Tournois</q-btn
        >
        <q-btn flat @click="handlePlayer" class="text-black hover:font-bold"
          >Joueurs</q-btn
        >
        <q-btn flat class="text-black mx-8 hover:font-bold"
          >Infos & Billets</q-btn
        >

        <q-btn push color="primary" label="Connexion" class="mx-8">
          <q-menu>
            <div class="row no-wrap q-pa-md">
              <div class="column justify-center">
                <div>Entraineur</div>
              </div>

              <q-separator vertical inset class="q-mx-lg" />

              <div class="column items-center">
                <q-avatar size="72px">
                  <img src="https://cdn.quasar.dev/img/avatar4.jpg" />
                </q-avatar>

                <div class="text-subtitle1 q-mt-md q-mb-xs">Paul Breton</div>

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

    <div>
      <router-view />
    </div>

    <q-footer class="relative bottom-0">
      <div>
        <q-toolbar class="bg-secondary">
          <q-toolbar-title>
            <div class="flex justify-center">
              <div class="mx-24">
                <img
                  class="w-16 my-2"
                  src="https://upload.wikimedia.org/wikipedia/fr/thumb/1/1d/Logo_Roland-Garros.svg/350px-Logo_Roland-Garros.svg.png?20120514221714"
                />
              </div>
              <div class="mx-24 text-center my-3">
                <b>Retrouvez-nous et prolongez l'expérience !</b> <br />
                <p class="text-sm">
                  Mentions légales et conditions générales d'utilisation
                </p>
              </div>
              <div class="mx-24">
                <img
                  class="w-12 my-2"
                  src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Logo_F%C3%A9d%C3%A9ration_Fran%C3%A7aise_de_Tennis_%282015_-_Pr%C3%A9sent%29.png/200px-Logo_F%C3%A9d%C3%A9ration_Fran%C3%A7aise_de_Tennis_%282015_-_Pr%C3%A9sent%29.png"
                />
              </div>
            </div>
          </q-toolbar-title>
        </q-toolbar>
        <q-toolbar class="min-h-[30px] flex justify-center text-orange-300">
          ©2022 - tous droits réservés Fédération Française de Tennis
        </q-toolbar>
      </div>
    </q-footer>
  </q-layout>
  <loader :show="loaderPage" hide-back></loader>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router';
import { LocalStorage } from 'quasar';
import Loader from 'src/components/shared/Loader.vue'
import { useCountryStore } from 'src/stores/country';
import { onMounted, ref } from 'vue';

const loaderPage = ref(false)
const countryStore = useCountryStore()
const router = useRouter();

onMounted(async () => {
  loaderPage.value = true
  
  //***************************** */
  //TODO: fetch si role === admin
  //await courtStore.fetch() 
  //await playerStore.fetch()
  if(!LocalStorage.has('nationality')) {
    await countryStore.fetch()
  }
  //***************************** */

  loaderPage.value = false
})

const handlePlayer = () => router.push({ name: 'all-players' });
const handleTournament = () => router.push({name: 'all-tournaments'})
</script>
<style lang="scss"></style>
