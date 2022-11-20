<template>
  <div class="xl:mx-96 lg:mx-68 md:mx-48 sm:mx-16 mx-8 mt-8">
    <MatchAdminEdit 
      :double="isDoubleMatch"
      v-model:teamA="teamAId"
      v-model:team-b="teamBId"
      v-model:date="date"
      v-model:court="court"
      :time="time"
      :show-loader-btn="showLoaderBtn"
      @submit="handleSubmit"
      ></MatchAdminEdit>
  </div>
</template>
<script setup lang="ts">
import { TournamentType } from 'src/models/tournament';
import { useMatchStore } from 'src/stores/match';
import { ref, onMounted, watch, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import MatchAdminEdit from './components/MatchAdminEdit.vue';

const route = useRoute()
const router = useRouter()
const matchStore = useMatchStore()

const matchId = route.params.idM

const match = computed(() => matchStore.match)

const isDoubleMatch = computed(() => [TournamentType.DOUBLE_MEN, TournamentType.DOUBLE_WOMAN].includes(match.value?.type))
const teamAId = ref(isDoubleMatch.value ? [match.value.teamA?.personA.id, match.value.teamA?.personB?.id] as number[] : match.value.teamA?.personA.id as number)
const teamBId = ref(isDoubleMatch.value ? [match.value.teamB?.personA.id, match.value.teamB?.personB?.id] as number[] : match.value.teamB?.personA.id as number)
const date = ref(match.value?.date || null)
const time = ref(null)
const court = ref(match.value.court?.id || null)
const showLoaderBtn = ref(false)

const handleSubmit = async () => {
  showLoaderBtn.value = true
  //TODO: update match
  await new Promise((s) => setTimeout(s, 2000));
  showLoaderBtn.value = false
}

onMounted(async () => {
  if(Object?.entries(matchStore.match)?.length === 0) {
    console.log('show match')
    await matchStore.show(matchId as string)
  }
})

watch(
  () => match.value,
  () => { if(match.value === undefined) router.back()}
)
</script>