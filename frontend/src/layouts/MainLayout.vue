<template>
  <div class="flex flex-col min-h-screen">
    <div class="sticky top-0 z-50">
      <q-toolbar class="bg-white">
        <q-toolbar-title>
          <img
            style="cursor: pointer;"
            @click="handleHome"
            class="w-20 my-2"
            src="https://upload.wikimedia.org/wikipedia/fr/thumb/1/1d/Logo_Roland-Garros.svg/350px-Logo_Roland-Garros.svg.png?20120514221714"
          />
        </q-toolbar-title>

        <q-btn
          flat
          @click="handleTournament"
          class="text-black hover:font-bold">Tournois</q-btn
        >
        <q-btn flat @click="handlePlayer" class="text-black hover:font-bold">Joueurs</q-btn
        >
        <q-btn flat @click="handleCourt" class="text-black hover:font-bold">Courts</q-btn>
        <q-btn flat @click="handleStat" class="text-black hover:font-bold">Statistiques</q-btn>

        <q-btn v-if="isAuth" push color="primary" label="Mon compte" class="mx-8">
          <q-menu>
            <div class="row no-wrap p-4">
              <div class="column justify-center">
                <div class="flex flex-col">
                  <div v-for="role in user.roles" :key="role">
                    <q-btn color="primary" :label="t(`admin.role.${role}`)" @click="handleAdminPage(role)"></q-btn>
                  </div>
                </div>
              </div>

              <q-separator vertical inset class="q-mx-lg" />

              <div class="column items-center">
                <q-avatar size="72px">
                  <img src="https://cdn.quasar.dev/img/avatar4.jpg" />
                </q-avatar>

                <div class="py-4">{{ user.username }}</div>

                <q-btn
                  color="primary"
                  label="Logout"
                  size="sm"
                  @click="handleLogout"
                  v-close-popup
                />
              </div>
            </div>
          </q-menu>
        </q-btn>
        <q-btn v-else color="primary" @click="handleLogin">Connexion</q-btn>
      </q-toolbar>
    </div>

    <div :class="{ 'xl:mx-90 lg:mx-68 md:mx-48 sm:mx-16 mt-8 mx-8 mb-16': !isIndexPage}">
      <router-view />
    </div>
    <div class="mt-auto">
      <div class="bg-secondary">
        <div class="flex justify-center items-center">
          <div class="mx-24">
            <img
              class="w-16 my-2"
              src="https://upload.wikimedia.org/wikipedia/fr/thumb/1/1d/Logo_Roland-Garros.svg/350px-Logo_Roland-Garros.svg.png?20120514221714"
            />
          </div>
          <div class="mx-24 text-center text-white my-3">
            <b>Retrouvez-nous et prolongez l'expérience !</b> <br />
            <p class="text-sm">
              <a href="https://www.rolandgarros.com/fr-fr/page/cgu">Mentions légales et Conditions Générales d'Utilisation</a>
            </p>
          </div>
          <div class="mx-24">
            <img
              class="w-12 my-2"
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Logo_F%C3%A9d%C3%A9ration_Fran%C3%A7aise_de_Tennis_%282015_-_Pr%C3%A9sent%29.png/200px-Logo_F%C3%A9d%C3%A9ration_Fran%C3%A7aise_de_Tennis_%282015_-_Pr%C3%A9sent%29.png"
            />
          </div>
        </div>
      </div>
      <div class="p-2 text-center bg-primary text-orange-200">
        ©2022 - tous droits réservés Fédération Française de Tennis
      </div>
    </div>
  </div>
  <loader :show="loaderPage" hide-back></loader>
</template>
<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { LocalStorage } from 'quasar';
import Loader from 'src/components/shared/Loader.vue'
import { useCountryStore } from 'src/stores/country';
import { computed, onMounted, ref } from 'vue';
import { useUserStore } from 'src/stores/user';
import { useI18n } from 'vue-i18n';
import { Roles } from 'src/models/user';
import { useMatchStore } from 'src/stores/match';

const loaderPage = ref(false)
const countryStore = useCountryStore()
const matchStore = useMatchStore()
const router = useRouter();
const userStore = useUserStore()
const route = useRoute()

const { t } = useI18n()

const user = computed(() => userStore.user)

const isAuth = computed(() => Object.entries(user.value).length > 0)
const handleLogin = () => { if(!isAuth.value) router.push({path: 'login'}) }
const handleLogout = () => {
  userStore.logout()
  router.push({path: '/'})
}
const handleHome = () => router.push({path: '/'})
const handleCourt = () => router.push({path: '/court'})
const handleStat = () => router.push({ path: '/statistics'})
const handleAdminPage = (role: Roles) => {
  switch(role) {
    case Roles.ROLE_ADMIN: router.push({path: '/admin'})
    break
    case Roles.ROLE_EDITOR_PLAYER: router.push({path: '/admin/player'}) 
    break
    case Roles.ROLE_EDITOR_MATCH: router.push({path: '/admin/tournaments'}) 
    break
    case Roles.ROLE_USER: router.push({path: '/'}) 
    break
  }
}

const isIndexPage = computed(() => route.path === '/')

onMounted(async () => {
  loaderPage.value = true
  
  //***************************** */
  //TODO: fetch si role === admin
  //await courtStore.fetch() 
  //await playerStore.fetch()
  await userStore.fetchRole()
  
  await countryStore.fetch()
  //***************************** */

  loaderPage.value = false
})

const handlePlayer = () => router.push({ name: 'all-players' });
const handleTournament = () => {
  matchStore.resetList()
  router.push({name: 'all-tournaments'})
} 
</script>
<style lang="scss"></style>
