<template>
  <div class="flex items-center border-2 border-gray-300 rounded-md py-2 px-6">
    <div class="flex flex-col gap-2">
      <div class="flex gap-16 items-end">
        <PlayerImgFlag :nationality="{alpha3code: 'FRA'}" :picture="match?.teamA?.personA.url"></PlayerImgFlag>
        <div v-if="match?.teamA" class="text-gray-500 font-bold">{{ reduceNamePlayer(match?.teamA?.personA?.firstname, match?.teamA?.personA.lastname) }}</div>
        <div v-else><q-skeleton class="w-24 h-8" animation="none" type="text"/></div>
      </div>
      <div class="flex gap-16 items-start">
        <PlayerImgFlag :nationality="{alpha3code: 'BEL'}" :picture="match?.teamA?.personA?.url"></PlayerImgFlag>
        <div v-if="match?.teamB" class="text-gray-500 font-bold">{{ reduceNamePlayer(match?.teamA?.personA?.firstname, match?.teamA?.personA?.lastname) }}</div>
        <div v-else><q-skeleton class="w-24 h-8" animation="none" type="text"/></div>
      </div>  
    </div>
    <div class="status m-auto">
      <q-badge>{{ t(`match.status.${match?.status}`) }}</q-badge>
    </div>
    <div class="flex gap-4 justify-self-end">
      <q-btn flat icon="visibility" />
      <q-btn flat icon="edit" />
    </div>
  </div>
</template>
<script setup lang="ts">
import { useMatchCard } from 'src/components/match/functions/match';
import PlayerImgFlag from 'src/components/shared/PlayerImgFlag.vue';
import { Match } from 'src/models/match';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const props = defineProps<{
  match: Match
}>()

const { reduceNamePlayer } = useMatchCard()
</script>