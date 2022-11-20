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
import Loader from 'src/components/shared/Loader.vue'
import { useCourtStore } from 'src/stores/court';
import { usePlayerStore } from 'src/stores/player';
import { onMounted, ref } from 'vue';

const loaderPage = ref(false)
const playerStore = usePlayerStore()
const courtStore = useCourtStore()

onMounted(async () => {
  loaderPage.value = true

  //***************************** */
  //TODO: fetch si role === admin
  await courtStore.fetch() 
  await playerStore.fetch()
  //***************************** */

  loaderPage.value = false
})
</script>

