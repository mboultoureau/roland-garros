<template>
  <HeaderC :title="t('tournament.index.title')">
    <template v-slot:btn>
      <q-btn color="primary" @click="handlePlayerTournaments">{{ t(`tournament.index.btn.${btnTitle}`) }}</q-btn>    
    </template>
    <template v-slot:default>
      <div class="flex gap-4 mt-8">
        <FilterByYears class="flex-1" :tournaments="listTournament" @handle-select-tournament="handleSelectTournament"/>
        <FilterByType class="flex-1" :tournament="tournamentSelect" @handle-select-type="handleSelectType"/>
      </div>
    </template>
  </HeaderC>
  <q-tab-panels v-model="btnTitle" animated>
    <q-tab-panel name="tournaments">
      <TabListPlayer v-model:current-tab="currentTab"></TabListPlayer>
    </q-tab-panel>
    <q-tab-panel name="players">
      <ScoreboardNavigation @handle-select-round="handleSelectRound"/>
      <ListMatchTournament :match-filtre="matchFiltre" :type="matchComponent"></ListMatchTournament>
    </q-tab-panel>
  </q-tab-panels>
  
  <Loader v-model:show="showLoader" />
</template>
<script lang="ts">
export default {
  name: 'IndexMatch',
}
</script>
<script setup lang="ts">
import { MatchFilter } from 'src/models/match';
import { Round, Tournament, TournamentType } from 'src/models/tournament';
import { useMatchStore } from 'src/stores/match';
import { useTournamentStore } from 'src/stores/tournament';
import { computed, onMounted, ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';
import FilterByType from './components/FilterByType.vue';
import FilterByYears from './components/FilterByYears.vue';
import Loader from '../shared/Loader.vue';
import HeaderC from '../shared/Header.vue';
import ListMatchTournament from './components/ListMatchTournament.vue';
import ScoreboardNavigation from './components/ScoreboardNavigation.vue'
import TabListPlayer from '../player/components/TabListPlayer.vue';
import { Gender } from 'src/models/person';
import { usePlayerStore } from 'src/stores/player';


const { t } = useI18n()

const tournamentStore = useTournamentStore()
const matchStore = useMatchStore()
const playerStore = usePlayerStore()

const showLoader = ref(false)
const btnTitle = ref('players')

const listTournament = computed(() => tournamentStore.listTournament)

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
const handlePlayerTournaments = () => {
  if(btnTitle.value === 'players') {
    btnTitle.value = 'tournaments'
  } else {
    btnTitle.value = 'players'
  }
}

const currentTab = ref(Gender.MEN)

watch(
  () => currentTab.value,
  async () => await playerStore.getPlayersByTournament(tournamentSelect.value?.id, currentTab.value)
)

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

const matchComponent = computed(() => {
  let component = ''
  if(matchFiltre.value?.type != null) {
    if(matchFiltre.value?.type.includes('DOUBLE_')) {
      component = 'MatchDoubleCard'
    } else {
      component = 'MatchCard'
    }
  }
  return component
})

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