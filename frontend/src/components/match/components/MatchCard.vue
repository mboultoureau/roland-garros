<template>
  <div class="match-card w-96 my-2 rounded-md bg-gray-100">
    <div class="team-A flex items-center justify-between py-2 px-4">
      <div class="flex items-center">
        <PlayerImgFlag :team="match.teamA"></PlayerImgFlag>
        <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">
          {{ reduceNamePlayer(match?.teamA?.personA.firstname, match?.teamA?.personA.lastname) }}
        </div>
      </div>
          
      <div class="sets flex gap-4">
        <div v-if="teamAWin" class="font-bold"><q-badge color="green" rounded /></div>
        <div v-for="score in match?.scores" :key="score.id" class="font-bold text-tertiary" :class="{'text-secondary': score.scoreTeamA >= 6}">{{ score.scoreTeamA }}</div>
      </div>
    </div>
    <hr class="mx-4 border-gray-300">
    <div class="team-A flex items-center justify-between py-2 px-4">
      <div class="flex items-center">
        <PlayerImgFlag :team="match.teamB"></PlayerImgFlag>
        <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': false}">
          {{ reduceNamePlayer(match?.teamB?.personA.firstname, match?.teamB?.personA.lastname) }}
        </div>
      </div>
      <div class="sets flex gap-4">
        <div v-if="teamBWin" class="font-bold"><q-badge color="green" rounded /></div>
        <div v-for="score in match?.scores" :key="score.id" class="font-bold text-tertiary" :class="{'text-secondary': score.scoreTeamB >= 6}">{{ score.scoreTeamB }}</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match'
import { useMatchCard } from '../functions/match';
import PlayerImgFlag from 'src/components/shared/PlayerImgFlag.vue';

const props = defineProps<{
  match: Match,
}>()

const ptTeamA = props.match.scores?.map((sc) => sc.scoreTeamA).reduce(
  (accumulator, currentValue) => accumulator + currentValue,
  0
) || 0;

const ptTeamB = props.match.scores?.map((sc) => sc.scoreTeamB).reduce(
  (accumulator, currentValue) => accumulator + currentValue,
  0
) || 0;

const teamAWin = ptTeamA > ptTeamB
const teamBWin = ptTeamA < ptTeamB

const { reduceNamePlayer } = useMatchCard()

</script>