<template>
  <div class="w-96 px-16 py-4 flex gap-4 flex-col justify-around">
    <div class="score-teamA flex">
      <div class="name-teamA flex gap-4 items-center">
        <div class="flex gap-1 flex-col">
          <div class="player-A-1 flex items-center gap-2">
            <img class="rounded-md w-12" :src="`https://www.rolandgarros.com/img/flags-svg/${teamAComp?.personA.nationality.alpha3Code}.svg`"/>
            <div>{{ teamAComp?.personA?.firstname }}</div>
          </div>
          <div class="player-A-2 flex items-center gap-2" v-if="teamA?.personB">
            <img class="rounded-md w-12" :src="`https://www.rolandgarros.com/img/flags-svg/${teamA?.personB?.nationality.alpha3Code}.svg`"/>
            <div>{{ teamA?.personB?.firstname }}</div>
          </div>
        </div>
        <!--<q-badge rounded color="green" />-->
      </div>
      <!--<div v-for="(score, index) in scoreTeamA" :key="index"></div>-->
      <div class="score-teamA flex flex-1 justify-end items-center gap-4">
        <div v-for="score in scores" :key="score.id" :class="{ 'font-bold text-primary': score.scoreTeamA >= 6}" class="text-lg text-tertiary">{{ score.scoreTeamA}}</div>
      </div>
    </div>
    <div class="score-teamB flex">
      <div class="name-teamB flex gap-4 items-center">
        <div class="flex gap-1 flex-col">
          <div class="player-B-1 flex items-center gap-2">
            <img class="rounded-md w-12" :src="`https://www.rolandgarros.com/img/flags-svg/${teamBComp?.personA.nationality.alpha3Code}.svg`"/>
            <div>{{ teamBComp?.personA?.firstname }}</div>
          </div>
          <div class="player-B-2 flex items-center gap-2" v-if="teamA?.personB">
            <img class="rounded-md w-12" :src="`https://www.rolandgarros.com/img/flags-svg/${teamB?.personB?.nationality.alpha3Code}.svg`"/>
            <div>{{ teamB?.personB?.firstname }}</div>
          </div>
        </div>
        <!--<q-badge rounded color="green" />-->
      </div>
      <!--<div v-for="(score, index) in scoreTeamB" :key="index"></div>-->
      <div class="flex flex-1 justify-end items-center gap-4">
        <div v-for="score in scores" :key="score.id" :class="{ 'font-bold text-primary': score.scoreTeamB >= 6}" class="text-lg text-tertiary">{{ score.scoreTeamB }}</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Score, Team } from 'src/models/match'
import { computed } from 'vue'

const props = defineProps<{
  teamA: Team,
  teamB: Team,
  scores: Score[]
}>()

const teamAComp = computed(() => props.teamA)
const teamBComp = computed(() => props.teamB)

const scoreTeamA = computed(() => props.scores?.filter((sc) => sc.scoreTeamA))
const scoreTeamB = computed(() => props.scores?.filter((sc) => sc.scoreTeamB))
</script>
