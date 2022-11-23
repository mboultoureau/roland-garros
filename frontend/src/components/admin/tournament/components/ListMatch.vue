<template>
  <div class="flex flex-col gap-4 mt-8">
    <MatchAdminCard v-for="match in listMatch" :key="match.id" :match="match"></MatchAdminCard>
  </div>
  <Loader :show="showLoader"></Loader>
</template>
<script setup lang="ts">
import Loader from 'src/components/shared/Loader.vue';
import { Round, TournamentType } from 'src/models/tournament'
import { useMatchStore } from 'src/stores/match';
import { computed, ref, watch } from 'vue';
import MatchAdminCard from './MatchAdminCard.vue';

const matchStore = useMatchStore()

const props = defineProps<{
  type: TournamentType,
  round: Round,
  tournamentId: number
}>()

const typeComp = computed(() => props.type)
const roundComp = computed(() => props.round)
const tournIdComp = computed(() => props.tournamentId)

const showLoader = ref(false)
const listMatch = computed(() => matchStore.listMatch)

watch(
  typeComp,
  async () => {
    showLoader.value = true
    if(typeComp.value && roundComp.value && tournIdComp.value) {
      await matchStore.fetch({ type: props.type, round: props.round, tournamentId: props.tournamentId})
    }
    showLoader.value = false
  }  
)

watch(
  roundComp,
  async () => {
    showLoader.value = true
    if(typeComp.value && roundComp.value && tournIdComp.value) {
      await matchStore.fetch({ type: props.type, round: props.round, tournamentId: props.tournamentId})
    }
    showLoader.value = false
  }
)
</script>