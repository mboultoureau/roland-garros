<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
        />

        <q-toolbar-title>
          Roland-Garros 
        </q-toolbar-title>

        <q-btn
          flat
          dense
        >Login</q-btn>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
  <loader :show="loaderPage" hide-back></loader>
</template>
<script setup lang="ts">
import { LocalStorage } from 'quasar';
import Loader from 'src/components/shared/Loader.vue'
import { useCountryStore } from 'src/stores/country';
import { onMounted, ref } from 'vue';

const loaderPage = ref(false)
const countryStore = useCountryStore()

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
</script>

