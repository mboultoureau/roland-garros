<template>
  <div class="flex flex-col gap-4 mt-16">
    <CardTournament v-for="tour in listTournament" :key="tour.id" :tournament="tour" /> 
  </div>
  
  <q-inner-loading :showing="showLoader">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script setup lang="ts">
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref } from 'vue';
import CardTournament from './CardTournament.vue';

const tournamentStore = useTournamentStore()

const listTournament = computed(() => tournamentStore.listTournament)
const showLoader = ref(false)

onMounted(async () => {
  showLoader.value = true
  await tournamentStore.fetch()
  showLoader.value = false 
})
</script>