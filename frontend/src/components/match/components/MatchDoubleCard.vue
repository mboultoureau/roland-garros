<template>
  <div class="match-card w-96 my-2 rounded-md bg-gray-100">
    <div class="team-A flex items-center justify-between p-2">
      <div class="flex items-center">
        <PlayerImgFlag :team="match.teamA" /> 
        <div class="flex flex-col gap-1">
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">{{ reduceNamePlayer(match?.teamA?.personA.firstname, match?.teamA?.personA.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamA?.personA.nationality.alpha3Code}.svg`" />
          </div>
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">{{ reduceNamePlayer(match?.teamA?.personB?.firstname, match?.teamA?.personB?.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamA?.personB?.nationality.alpha3Code}.svg`" />
          </div>
        </div>
      </div>
          
      <div class="sets flex gap-3">
        <div v-if="teamAWin" class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}"><q-badge color="green" rounded></q-badge></div>
        <div v-for="score in match?.scores" :key="score.id" class="font-bold text-tertiary" :class="{'text-secondary': score.scoreTeamA >= 6}">{{ score.scoreTeamA }}</div>
      </div>
    </div>
    <hr class="mx-4 border-gray-300">
    <div class="team-B flex items-center justify-between p-2">
      <div class="flex items-center">
        <PlayerImgFlag :team="match.teamB" /> 
        <div class="flex flex-col gap-1">
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary">{{ reduceNamePlayer(match?.teamB?.personA?.firstname, match?.teamB?.personA?.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamB?.personA?.nationality.alpha3Code}.svg`" />
          </div>
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary">{{ reduceNamePlayer(match?.teamB?.personB?.firstname, match?.teamB?.personB?.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamB?.personB?.nationality.alpha3Code}.svg`" />
          </div>
        </div>
      </div>
      <div class="sets flex gap-3">
        <div v-if="teamBWin" class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}"><q-badge color="green" rounded></q-badge></div>
        <div v-for="score in match?.scores" :key="score.id" class="font-bold text-tertiary" :class="{'text-secondary': score.scoreTeamB >= 6}">{{ score.scoreTeamB }}</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match'
import { useMatchCard } from '../functions/match'
import PlayerImgFlag from 'src/components/shared/PlayerImgFlag.vue';

const props = defineProps<{
  match: Match
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