<template>
  <div class="header px-4 py-8 mb-8 bg-gray-100 rounded-md">
    <div class="flex items-center text-xl font-bold mb-4"><FourCercle />
      {{ t('tournament.index.title') }}
    </div>
    <div class="flex gap-4">
      <FilterByYears class="flex-1" :tournaments="listTournament" @handle-select-tournament="handleSelectTournament"/>
      <FilterByType class="flex-1" :tournament="tournamentSelect" @handle-select-type="handleSelectType"/>
    </div>
  </div>
  <ScoreboardNavigation @handle-select-round="handleSelectRound"/>
  <div class="flex justify-around gap-16" :class="{'flex-row-reverse': matchFiltre.round === Round.FINAL_ROUND}">
    <div class="flex flex-col">
      <div class="flex text-2xl text-gray-300 my-8 items-center justify-center" v-if="matchFiltre?.round"><FourCercle />{{ t(`match.round.${matchFiltre.round}`) }}</div>
      <div class="flex flex-1 flex-col justify-center items-center">
        <component :is="matchComponent" v-for="match in listMatch" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
      </div>
    </div>
    <div class="flex flex-col">
      <div class="flex text-2xl text-gray-300 my-8 items-center justify-center" v-if="matchFiltre?.round"><FourCercle />{{ t(`match.round.${getNextOrPreviousRound(matchFiltre.round)}`) }}</div>
      <div class="flex flex-1 flex-col justify-around">
        <component :is="matchComponent" v-for="match in listMatchNextOrPrevious" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
      </div>
    </div> 
  </div>
  <Loader v-model:show="showLoader" />
</template>
<script lang="ts">
import { Match, MatchFilter } from 'src/models/match';
import { Round, Tournament, TournamentType } from 'src/models/tournament';
import { useMatchStore } from 'src/stores/match';
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';
import FourCercle from '../shared/FourCercle.vue';
import FilterByType from './components/FilterByType.vue';
import FilterByYears from './components/FilterByYears.vue';
import MatchCard from '../match/components/MatchCard.vue';
import MatchDoubleCard from '../match/components/MatchDoubleCard.vue';
import ScoreboardNavigation from './components/ScoreboardNavigation.vue';
import { useRouter } from 'vue-router';
import Loader from '../shared/Loader.vue';

export default {
    name: 'IndexMatch',
    components: { ScoreboardNavigation, MatchCard, MatchDoubleCard, FilterByYears, FourCercle, FilterByType, Loader }
}
</script>
<script setup lang="ts">
const { t } = useI18n()

const tournamentStore = useTournamentStore()
const matchStore = useMatchStore()
const router = useRouter()

const showLoader = ref(false)

const listTournament = computed(() => tournamentStore.listTournament)
const listMatch = computed(() => matchStore.listMatch)
const listMatchNextOrPrevious = computed(() => matchStore.listMatchNextOrPrevious)

const tournamentSelect = ref(null as Tournament | null)

const matchFiltre = ref({
  tournamentId: 0,
  round: null as Round | null,
  type: null as TournamentType | null
} as MatchFilter)

const handleSelectTournament = (tournament: Tournament | null) => {
  matchFiltre.value.tournamentId = tournament?.id || 0
  tournamentSelect.value = tournament
}
const handleSelectType = (type: TournamentType | null) => matchFiltre.value.type = type
const handleSelectRound = (round: Round) => matchFiltre.value.round = round
const handleShowMatch = (match: Match) => router.push({ name: 'show-match', params: { idT: tournamentSelect.value?.id, idM: match.id }})

const matchComponent = computed(() => {
  let component = ''
  if(matchFiltre.value.type != null) {
    if(matchFiltre.value?.type.includes('DOUBLE_')) {
      component = 'MatchDoubleCard'
    } else {
      component = 'MatchCard'
    }
  }
  return component
})

watch(
  matchFiltre.value,
  async () => {
    showLoader.value = true
    if(matchFiltre.value.round && matchFiltre.value.type && matchFiltre.value.tournamentId > 0) {
      await matchStore.fetch({
        tournamentId: matchFiltre.value.tournamentId,
        round: matchFiltre.value.round,
        type: matchFiltre.value.type
      })
      await matchStore.fetch({
        tournamentId: matchFiltre.value.tournamentId,
        round: getNextOrPreviousRound(matchFiltre.value.round),
        type: matchFiltre.value.type
      }, true)
    }
    showLoader.value = false
})

const getNextOrPreviousRound = (round: Round): Round => {
  switch(round) {
    case Round.FIRST_ROUND: return Round.SECOND_ROUND;
    break;
    case Round.SECOND_ROUND: return Round.THIRD_ROUND;
    break;
    case Round.THIRD_ROUND: return Round.SIXTEENTH_ROUND;
    break;
    case Round.SIXTEENTH_ROUND: return Round.QUART_FINAL;
    break;
    case Round.QUART_FINAL: return Round.SEMI_FINAL;
    break;
    case Round.SEMI_FINAL: return Round.FINAL_ROUND;
    break;
    case Round.FINAL_ROUND: return Round.SEMI_FINAL;
    break;
  }
}

onMounted(async () => {
  showLoader.value = true
  await tournamentStore.fetch()
  showLoader.value = false
})

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