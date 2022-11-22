<template>
  <div class="flex w-full my-4">
    <div class="flex-1 text-center" :class="{'bg-primary rounded-lg': currentNbDay === getNbDay(day) }" v-for="day in nbDays" :key="day">{{ getNbDay(day) }}</div>
  </div>
  <div class="flex">
    <div class="agenda flex-1" v-for="day in nbDays" :key="day">
      <div class="match border-2 p-2 h-full" :class="{'bg-primary border-y-primary text-gray-50': hasEvent(getNbDay(day), horaire) }" v-for="(horaire, index) in ndHours" :key="index">{{ hasEvent(getNbDay(day), horaire) }}</div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { addDays, addHours, startOfWeek  } from 'date-fns'  
import { useMatchCard } from 'src/components/match/functions/match';
import { ref } from 'vue'

const { reduceNamePlayer } = useMatchCard()

const curr = new Date();
const currentNbDay = curr.getDate()
const firstDayOfWeek = startOfWeek(curr)

const nbDays = 7
const ndHours = [
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

const training = ref([
  {
    startDate: new Date('2022-11-23T08:00:00'),
    duration: 3,
    player: {
      firstname: 'Sylvain',
      lastname: 'Mestre'
    }
  },
  {
    startDate: new Date('2022-11-23T12:00:00'),
    duration: 1,
    player: {
      firstname: 'Mathis',
      lastname: 'Boultoureau'
    }
  },
  {
    startDate: new Date('2022-11-21T20:00:00'),
    duration: 2,
    details: 'lala',
    player: {
      firstname: 'Paul',
      lastname: 'Breton'
    }
  },
  {
    startDate: new Date('2022-11-26T18:00:00'),
    duration: 2,
    player: {
      firstname: 'Leane',
      lastname: 'Diraison'
    }
  }
])

const allEvents = () => {
  const events = []
  training.value.forEach((t) => {
    for(let i=0; i<t.duration;i++) {
      events.push({ date: t.startDate.getDate(), horaire: addHours(t.startDate, i).getHours(), player: t.player})
    }
  })
  return events
}

const tabE = allEvents()

const hasEvent = (date: number, time: number) => {
  const evt = tabE.find((evt) => evt.date === date && evt.horaire === time)
  if(evt) return reduceNamePlayer(evt.player.firstname, evt.player.lastname)
  else return ''
}
</script>
<style lang="scss" scoped>
.agenda {
  width: fit;
  height: 4rem;
} 
</style>