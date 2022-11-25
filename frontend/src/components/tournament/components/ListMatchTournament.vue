<template>
  <div class="flex justify-around gap-16" :class="{'flex-row-reverse': matchFiltre.round === Round.FINAL_ROUND}">
    <div class="flex flex-col">
      <div class="flex text-2xl text-gray-300 my-8 items-center justify-center" v-if="matchFiltre?.round"><FourCercle />{{ t(`match.round.${matchFiltre.round}`) }}</div>
      <div class="flex flex-1 flex-col justify-center items-center">
        <div v-if="isSimpleMatch">
          <MatchCard v-for="match in listMatch" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
        </div>
        <div v-else>
          <MatchDoubleCard v-for="match in listMatch" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
        </div>
      </div>
    </div>
    <div class="flex flex-col">
      <div class="flex text-2xl text-gray-300 my-8 items-center justify-center" v-if="matchFiltre?.round"><FourCercle />{{ t(`match.round.${getNextOrPreviousRound(matchFiltre.round)}`) }}</div>
        <div class="flex flex-1 flex-col justify-around" v-if="isSimpleMatch">
          <MatchCard v-for="match in listMatchNextOrPrevious" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
        </div>
        <div class="flex flex-1 flex-col justify-around" v-else>
          <MatchCard v-for="match in listMatchNextOrPrevious" :key="match.id" :match="match" @click="handleShowMatch(match)" class="cursor-pointer"/>
        </div>
    </div> 
  </div>
</template>
<script setup lang="ts">
import { Match, MatchFilter } from 'src/models/match';
import { Round } from 'src/models/tournament';
import { useMatchStore } from 'src/stores/match';
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import FourCercle from '../../shared/FourCercle.vue';
import { useRouter } from 'vue-router';
import ScoreboardNavigation from './ScoreboardNavigation.vue';
import MatchCard from 'src/components/match/components/MatchCard.vue';
import MatchDoubleCard from 'src/components/match/components/MatchDoubleCard.vue';

const props = defineProps<{
  matchFiltre: MatchFilter,
  type: string,
}>()

const { t } = useI18n()

const matchStore = useMatchStore()

const router = useRouter()

const listMatch = computed(() => matchStore.listMatch)
const listMatchNextOrPrevious = computed(() => matchStore.listMatchNextOrPrevious)

const isSimpleMatch = computed(() => props.type === 'MatchCard')

const tournamentSelect = ref()

const handleShowMatch = (match: Match) => {
  matchStore.resetList()
  router.push({ name: 'show-match', params: { idT: match?.tournament?.id, idM: match.id }})
}

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




</script>