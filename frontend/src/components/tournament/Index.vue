<template>
  <div class="lg:mx-80 md:mx-52 mx-8">
    <div class="header my-8 px-4 py-8 bg-gray-100 rounded-md">
      <div class="flex items-center text-xl font-bold mb-4"><FourCercle />{{ t('tournament.index.title') }}</div>
      <div class="flex gap-4">
        <FilterByYears class="flex-1" :tournaments="listTournament" @handle-select-tournament="handleSelectTournament"/>
        <FilterByType class="flex-1" :tournament="tournamentSelect"/>
      </div>
    </div>
    <ScoreboardNavigation />
    <div class="flex justify-center gap-16">
      <div class="flex flex-col justify-center items-center">
        <!--<MatchCard v-for="(match, index) in nbMatch" :key="index" :class="{ 'mb-16': ((index+1) % 2 === 0 && (index+1) != nbMatch) }" />-->
        <MatchDoubleCard />
      </div>
      <div class="flex flex-col justify-around mobile-match-card">
        <MatchCard v-for="(match, index) in nbMatch2" :key="index" />
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Tournament } from 'src/models/tournament';
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import FourCercle from '../shared/FourCercle.vue';
import FilterByType from './components/FilterByType.vue';
import FilterByYears from './components/FilterByYears.vue';
import MatchCard from './components/MatchCard.vue';
import MatchDoubleCard from './components/MatchDoubleCard.vue';
import ScoreboardNavigation from './components/ScoreboardNavigation.vue';

export default {
    name: 'IndexMatch',
    components: { ScoreboardNavigation, MatchCard, MatchDoubleCard, FilterByYears, FourCercle, FilterByType }
}
</script>
<script setup lang="ts">

const nbMatch = 8
const nbMatch2 = 4

const { t } = useI18n()

const tournamentStore = useTournamentStore()

const listTournament = computed(() => tournamentStore.listTournament)

const tournamentSelect = ref(null as Tournament | null)

const handleSelectTournament = (tournament: Tournament | null) => tournamentSelect.value = tournament

onMounted(async () => await tournamentStore.fetch())

</script>
<style lang="scss" scoped>
.mobile-match-card  {
  display: block;
}
@media screen and (max-width: 1280px) {
  .mobile-match-card  {
    display: none;
  } 
}

</style>