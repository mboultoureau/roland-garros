<template>
  <HeaderFIlterTournament :tournament="tournament" @handle-select-round="handleSelectRound" @handle-select-type="handleSelectType"/>
  <ListMatch :type="typeSelected" :round="roundSelected" :tournament-id="tournament.id"></ListMatch>
</template>
<script setup lang="ts">
import { Round, Tournament, TournamentType } from 'src/models/tournament';
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import HeaderFIlterTournament from './components/HeaderFIlterTournament.vue';
import ListMatch from './components/ListMatch.vue';

const tournamentStore = useTournamentStore()
const route = useRoute()

const tournament = computed(() => (tournamentStore.tournament as Tournament))

onMounted(async () => await tournamentStore.getTournamentTypes(parseInt(route.params.id as string) as number))

const typeSelected = ref()
const roundSelected = ref()

const handleSelectRound = (round: Round) => roundSelected.value = round
const handleSelectType = (type: TournamentType) => typeSelected.value = type
</script>