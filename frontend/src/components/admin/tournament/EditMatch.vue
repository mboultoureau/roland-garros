<template>
  <MatchAdminEdit 
    :double="isDoubleMatch"
    v-model:teamA="teamA"
    v-model:team-b="teamB"
    v-model:date="date"
    v-model:court="court"
    v-model:duration="duration"
    v-model:time="time"
    :show-loader-btn="showLoaderBtn"
    :options-players="optionsPlayers"
    @submit="handleSubmit"
    ></MatchAdminEdit>
</template>
<script setup lang="ts">
import { MatchEditFilter } from 'src/models/match';
import { TournamentType } from 'src/models/tournament';
import { useCourtStore } from 'src/stores/court';
import { useMatchStore } from 'src/stores/match';
import { usePlayerStore } from 'src/stores/player';
import { ref, onMounted, watch, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import PlayerCardAdmin from '../player/components/PlayerCardAdmin.vue';
import MatchAdminEdit from './components/MatchAdminEdit.vue';
import { format } from 'date-fns';

const route = useRoute()
const router = useRouter()
const matchStore = useMatchStore()
const playerStore = usePlayerStore()
const courtStore = useCourtStore()

const matchId = route.params.idM

const match = computed(() => matchStore.match)

const isDoubleMatch = computed(() => [TournamentType.DOUBLE_MEN, TournamentType.DOUBLE_WOMAN].includes(match.value?.type))
const getTeamA = () => {
  if(match.value.teamA === null) return isDoubleMatch.value ? [] : null
  else {
    if(isDoubleMatch.value) {
      return [match.value.teamA?.personA.id, match.value.teamA?.personB?.id]
    } else {
      return match.value.teamA?.personA.id
    }
  }
}
const getTeamB = () => {
  if(match.value.teamB === null) return isDoubleMatch.value ? [] : null
  else {
    if(isDoubleMatch.value) {
      return [match.value.teamB?.personA.id, match.value.teamB?.personB?.id]
    } else {
      return match.value.teamB?.personA.id
    }
  }
}
const teamA = ref(getTeamA())
const teamB = ref(getTeamB())
const date = ref(match.value.startDate != null ? format(new Date(match.value?.startDate), 'yyyy-MM-dd') : null)
const time = ref(match.value.startDate != null ? format(new Date(match.value?.startDate), 'HH:mm') : null)
const court = ref(match.value.court?.id || null)
const duration = ref(match.value?.duration || null)
const status = ref(match.value?.status || null)

const showLoaderBtn = ref(false)

const optionsPlayers = ref([])

onMounted(async () => {
  optionsPlayers.value = await playerStore.fetchAll()
  await courtStore.fetch()
})

const handleSubmit = async () => {
  showLoaderBtn.value = true
  let teamAComp = {
    personAId: teamA.value
  }
  let teamBComp = {
    personAId: teamB.value
  }
  if(match.value?.type.includes('DOUBLE_')) {
    teamAComp.personAId = teamA.value[0]
    teamBComp.personAId = teamB.value[0]
    teamAComp.personBId = teamA.value[1]
    teamBComp.personBId = teamB.value[1]
  }
  await matchStore.edit({
    matchId: matchId,
    teamA: teamAComp,
    teamB: teamBComp,
    courtId: court.value,
    startDate: `${date.value}T${time.value}`,
    duration: duration.value,
    status: status.value,
    scores: [],
    round: match.value.round,
    type: match.value.type 
  })

  showLoaderBtn.value = false
  router.back()
}

onMounted(async () => {
  if(Object?.entries(matchStore.match)?.length === 0) {
    await matchStore.show(matchId as string)
  }
})

watch(
  () => match.value,
  () => { if(match.value === undefined) router.back()}
)
</script>