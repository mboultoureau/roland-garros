<template>
  <div class="xl:mx-96 lg:mx-68 md:mx-48 sm:mx-16 mx-8 mt-8">
    <HeaderFIlterTournament :tournament="tournament.tournament" @handle-select-round="handleSelectRound" @handle-select-type="handleSelectType"/>

    <ListMatch :type="typeSelected" :round="roundSelected" :tournament-id="tournament?.tournament?.id"></ListMatch>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match';
import { Round, Tournament, TournamentType } from 'src/models/tournament';
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import HeaderFIlterTournament from './components/HeaderFIlterTournament.vue';
import ListMatch from './components/ListMatch.vue';

const tournamentStore = useTournamentStore()
const route = useRoute()

const tournament = computed(() => (tournamentStore.tournament as { tournament: Tournament, match: Match }))

onMounted(async () => await tournamentStore.show(parseInt(route.params.id as string) as number, null))

const typeSelected = ref()
const roundSelected = ref()

const handleSelectRound = (round: Round) => roundSelected.value = round
const handleSelectType = (type: TournamentType) => typeSelected.value = type
</script>