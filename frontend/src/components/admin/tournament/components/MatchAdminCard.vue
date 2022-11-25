<template>
  <div class="flex items-center border-2 border-gray-300 rounded-md py-2 px-6">
    <div class="flex flex-col gap-2">
      <div class="flex gap-16 items-end">
        <PlayerImgFlag :team="match?.teamA"></PlayerImgFlag>
        <div v-if="match?.teamA" class="text-gray-500 font-bold">{{ reduceNamePlayer(match?.teamA?.personA?.firstname, match?.teamA?.personA.lastname) }}</div>
        <div v-else><q-skeleton class="w-24 h-8" animation="none" type="text"/></div>
      </div>
      <div class="flex gap-16 items-start">
        <PlayerImgFlag :team="match?.teamB"></PlayerImgFlag>
        <div v-if="match?.teamB" class="text-gray-500 font-bold">{{ reduceNamePlayer(match?.teamB?.personA?.firstname, match?.teamB?.personA?.lastname) }}</div>
        <div v-else><q-skeleton class="w-24 h-8" animation="none" type="text"/></div>
      </div>  
    </div>
    <div class="status m-auto">
      <q-badge>{{ t(`match.status.${match?.status}`) }}</q-badge>
    </div>
    <div class="flex gap-4 justify-self-end">
      <q-btn flat icon="visibility" />
      <q-btn flat icon="edit" @click="handleEditMatch" />
      <q-btn color="primary" @click="handleAddScores">+ score(s)</q-btn>
    </div>
  </div>
  <DialogAddScore :match="match" v-model:show="showDialog"></DialogAddScore>
</template>
<script setup lang="ts">
import { useMatchCard } from 'src/components/match/functions/match';
import PlayerImgFlag from 'src/components/shared/PlayerImgFlag.vue';
import DialogAddScore from './DialogAddScore.vue';
import { Match } from 'src/models/match';
import { useMatchStore } from 'src/stores/match';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import { ref } from 'vue'

const { t } = useI18n()
const router = useRouter()
const matchStore = useMatchStore()
const showDialog = ref(false)

const props = defineProps<{
  match: Match
}>()

const handleEditMatch = () => {
  matchStore.resetList()
  matchStore.set(props.match)
  router.push({name: 'edit-match-tournament', params: {idT: props.match?.tournament.id, idM: props.match?.id}})
}

const handleAddScores = () => showDialog.value = true
const { reduceNamePlayer } = useMatchCard()
</script>