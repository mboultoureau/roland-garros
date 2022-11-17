<template>
  <div class="lg:mx-80 md:mx-52 mx-8">
    <div class="header my-8 px-4 py-8 bg-gray-100 rounded-md">
      <div class="flex items-center text-xl font-bold mb-4"><FourCercle />
        {{ t('tournament.index.title') }}
      </div>
      <div class="flex gap-4">
        <FilterByYears class="flex-1" :tournaments="listTournament" @handle-select-tournament="handleSelectTournament"/>
        <FilterByType class="flex-1" :tournament="tournamentSelect" @handle-select-type="handleSelectType"/>
      </div>
    </div>
    <ScoreboardNavigation @handle-select-round="handleSelectRound"/>
    <div class="flex-col items-center justify-center gap-16">
      <div class="flex text-2xl text-gray-300 my-8 items-center justify-center" v-if="matchFiltre?.round"><FourCercle />{{ t(`match.round.${matchFiltre.round}`) }}</div>
      <div class="flex flex-col justify-center items-center">
        <component :is="matchComponent" v-for="match in listMatch" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
      </div>
    </div>
  </div>
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

export default {
    name: 'IndexMatch',
    components: { ScoreboardNavigation, MatchCard, MatchDoubleCard, FilterByYears, FourCercle, FilterByType }
}
</script>
<script setup lang="ts">
const { t } = useI18n()

const tournamentStore = useTournamentStore()
const matchStore = useMatchStore()
const router = useRouter()

const listTournament = computed(() => tournamentStore.listTournament)
const listMatch = computed(() => matchStore.listMatch)

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
  async () => await matchStore.fetch({
    tournamentId: 1,
    round: matchFiltre.value.round,
    type: matchFiltre.value.type
  })
)

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