<template>
  <div class="flex justify-between items-center border-2 rounded-md border-gray-100 p-6 cursor-pointer hover:bg-gray-100 " @click="handleSelectTournament">
    <div>{{ tournament.year }}</div>
    <div><q-badge>{{ labelBadge }}</q-badge></div>
    <div><q-icon name="navigate_next" size="2rem" class="text-gray-500" /></div>
  </div>
</template>
<script setup lang="ts">
import { Tournament } from 'src/models/tournament';
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const props = defineProps<{
  tournament: Tournament
}>()

const { t } = useI18n()
const router = useRouter()

const date = new Date()
const currentYear = date.getFullYear()

const handleSelectTournament = () => router.push({ name: 'show-tournament-admin', params: { id: props.tournament.id }})

const labelBadge = computed(() => {
  let label = ''
  if(props.tournament?.year == currentYear) label = t('match.status.IN_PROGRESS')
  else if (props.tournament?.year < currentYear) label = t('match.status.FINISH')
  else label = t('match.status.PLANNED')
  return label
})
</script>