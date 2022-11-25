<template>
  <div class="p-8 border-2 border-gray-200">
    <div class="flex">
      <MatchPlayerImg class="flex-1" :team="match?.teamA" />
      <div class="flex flex-col items-center justify-around flex-1">
        <div class="text-4xl uppercase text-primary font-bold italic">{{ t(`match.round.${match?.round}`) }}</div>
        <ScoreMatch :team-a="matchComp?.teamA" :team-b="matchComp?.teamB" :scores="matchComp?.scores" />
      </div>
      <MatchPlayerImg class="flex-1" :team="match?.teamB" />
    </div>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match';
import { computed, watch } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';
import MatchPlayerImg from './MatchPlayerImg.vue';
import ScoreMatch from './ScoreMatch.vue';

const { t } = useI18n()

const props = defineProps<{
  match: Match
}>()

const router = useRouter()

const matchComp = computed(() => props.match)

watch(
  matchComp,
  () => { if(matchComp.value === undefined) router.back()}
)
</script>