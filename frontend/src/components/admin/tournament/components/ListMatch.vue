<template>
  <div class="grid grid-cols-2">
    <div v-for="match in listMatch" :key="match.id"></div>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match';
import { Round, TournamentType } from 'src/models/tournament'
import { useTournamentStore } from 'src/stores/tournament';
import { computed, ref, watch } from 'vue';

const tournamentStore = useTournamentStore()

const props = defineProps<{
  type: TournamentType,
  round: Round,
  tournamentId: number
}>()

const typeComp = computed(() => props.type)
const roundComp = computed(() => props.round)
const tournIdComp = computed(() => props.tournamentId)

const listMatch = ref<Match[]>([])

watch(
  typeComp,
  () => {
    if(typeComp.value && roundComp.value && tournIdComp.value) {
      listMatch.value = tournamentStore.getByFilter(props.type, props.round)
    }
  }  
)

watch(
  roundComp,
  async () => {
    if(typeComp.value && roundComp.value && tournIdComp.value) {
      listMatch.value =tournamentStore.getByFilter(props.type, props.round)
    }
  }  
)
</script>