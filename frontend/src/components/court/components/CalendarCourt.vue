<template>
  <div class="flex gap-4 w-full h-screen" v-if="trainings">
    <div class="flex flex-col justify-around">
      <div v-for="hour in nbHours" :key="hour">{{ hour }}h</div>
    </div>
    <div class="flex-1">
      <div class="flex w-full my-4">
        <div class="flex-1 text-center" :class="{'bg-primary rounded-lg text-white font-bold': currentNbDay === getNbDay(day) }" v-for="day in nbDays" :key="day">{{ getNbDay(day) }}</div>
      </div>
      <div class="flex-1 flex"> 
        <div class="agenda flex-1" v-for="day in nbDays" :key="day">
          <div class="match border-2 p-2 h-full" :class="{'bg-primary border-primary text-gray-50': hasEvent(getNbDay(day), horaire) }" v-for="(horaire, index) in nbHours" :key="index">{{ hasEvent(getNbDay(day), horaire) }}</div>
        </div>
      </div>
    </div>
  </div>
  <div v-else class="text-center my-16 text-gray-500 font-bold">
    {{ t('court.index.no.selected') }}
  </div>
</template>
<script setup lang="ts">
import { addDays } from 'date-fns'  
import { useMatchCard } from 'src/components/match/functions/match';
import { Training } from 'src/models/court';
import { computed, watch } from 'vue'
import { useI18n } from 'vue-i18n';
import { currentNbDay, firstDayOfWeek, allEvents } from '../functions/calendar';

const props = defineProps<{
  trainings: Training[]
}>()

const { t } = useI18n()

const trainingsComp = computed(() => props.trainings || [])

const { reduceNamePlayer } = useMatchCard()

const nbDays = 7
const nbHours = [
  8,
  9,
  10,
  11,
  12,
  13,
  14,
  15,
  16,
  17,
  18,
  19,
  20,
]

const getNbDay = (nb: number) => addDays(firstDayOfWeek, nb).getDate()

let tabE = []

const hasEvent = (date: number, time: number) => {
  const evt = tabE.find((evt) => evt.date === date && evt.horaire === time)
  if(evt && evt.player != null) return reduceNamePlayer(evt.player.firstname, evt.player.lastname)
  else return ''
}

watch(
  trainingsComp,
  () => tabE = allEvents(trainingsComp.value)
)

</script>
<style lang="scss" scoped>
.agenda {
  width: fit;
  height: 4rem;
} 
</style>