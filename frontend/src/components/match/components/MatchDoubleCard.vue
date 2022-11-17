<template>
  <div class="match-card w-96 my-2 rounded-md bg-gray-100">
    <div class="team-A flex items-center justify-between p-2">
      <div class="flex items-center">
        <div class="relative w-24 h-12">
          <div class="absolute top-0 left-0 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" :src="match?.teamA.personA.url"/>
          </div>
          <div class="absolute top-0 left-10 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" :src="match?.teamA.personB?.url"/>
          </div>
        </div>
        <div class="flex flex-col gap-1">
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">{{ reduceNamePlayer(match?.teamA.personA.firstname, match?.teamA.personA.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamA.personA.flag}.svg`" />
          </div>
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">{{ reduceNamePlayer(match?.teamA.personB?.firstname, match?.teamA?.personB?.lastname) }}</div>
            <img class="rounded-sm w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${match?.teamA.personB?.flag}.svg`" />
          </div>
        </div>
      </div>
          
      <div class="sets flex">
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}">v</div>
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}">6</div>
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': false}">4</div>
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}">6</div>
      </div>
    </div>
    <hr class="mx-4 border-gray-300">
    <div class="team-B flex items-center justify-between p-2">
      <div class="flex items-center">
        <div class="relative w-24 h-12">
          <div class="absolute top-0 left-0 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" src="https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400"/>
          </div>
          <div class="absolute top-0 left-10 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
            <img class="w-12 h-12" src="https://images.prismic.io/fft-rg-site/29bcebad-30f5-46c2-a7dd-e648518dea74_20220310_PJ_NadalRafael_US.png?auto=compress,format&rect=0,0,1080,1080&w=400&h=400"/>
          </div>
        </div>
        <div class="flex flex-col gap-1">
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">R.Nadal (1)</div>
            <img class="rounded-sm w-8 h-6" src="https://www.rolandgarros.com/img/flags-svg/FRA.svg" />
          </div>
          <div class="flex gap-1" >
            <div class="font-bold ml-6 text-tertiary" :class="{'text-secondary': true}">R.Nadal (1)</div>
            <img class="rounded-sm w-8 h-6" src="https://www.rolandgarros.com/img/flags-svg/FRA.svg" />
          </div>
        </div>
      </div>
      <div class="sets flex">
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': false}">4</div>
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': true}">6</div>
        <div class="font-bold mx-2 text-tertiary" :class="{'text-secondary': false}">4</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match'
import { useMatchCard } from '../functions/match'

const props = defineProps<{
  match: Match
}>()

const { reduceNamePlayer } = useMatchCard()
</script>