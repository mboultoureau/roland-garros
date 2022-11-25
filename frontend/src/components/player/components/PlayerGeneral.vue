<template>
  <div class="mt-16 flex">
    <div class="container">
      <div class="title-personnel font-bold text-2xl flex items-center mb-8"><FourCercle />{{ t('player.show.fiche.perso') }}</div>
      <div class="title-pro font-bold text-2xl flex items-center mb-8"><FourCercle /> {{ t('player.show.fiche.pro') }}</div>
      <div class="age px-4 pb-4">
        <div>{{ t('player.show.birthday') }}</div>
        <div class="font-bold">{{ player?.birthDate }}</div>
      </div>
      <div class="height px-4">
        <div>{{ t('player.show.height') }}</div>
        <div class="font-bold">{{ player?.height }} cm</div>
      </div>
      <div class="start-career px-4">
        <div>{{ t('player.show.start.career') }}</div>
        <div class="font-bold">{{ player?.earlyCareer }}</div>
      </div>
      <div class="weight px-4">
        <div>{{ t('player.show.weight') }}</div>
        <div class="font-bold">{{ player?.weight }} kg</div>
      </div>
      <div class="coach px-4">
        <div>{{ t('player.show.coach') }}</div>
        <div class="font-bold">{{ player?.coach?.firstname || ' ' }} {{ player?.coach?.lastname || '' }}</div>
      </div>
      <div class="hand px-4">
        <div>{{ t('player.show.hand') }}</div>
        <div class="font-bold">{{ t(`player.show.hand.${player?.hand}`) }}</div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import FourCercle from 'src/components/shared/FourCercle.vue';
import { Person } from 'src/models/person';
import { useI18n } from 'vue-i18n';

const props = defineProps<{
  player: Person
}>()

const { t } = useI18n()
</script>
<style lang="scss" scoped>
  .container {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 2fr 1fr 1fr 1fr;
    grid-auto-columns: 1fr;
    grid-auto-rows: 1fr;
    grid-auto-flow: row;
    grid-template-areas:
      "title-personnel title-pro"
      "age start-career"
      "height coach"
      "weight hand";
  }

.age {  
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: age;
}

.height {  
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: height;
}

.start-career {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: start-career;
}

.weight {  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: weight;
}

.coach {  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: coach;
}

.hand {  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  grid-auto-flow: row;
  grid-template-areas:
    ". .";
  grid-area: hand;
}

.title-personnel { grid-area: title-personnel; }

.title-pro { grid-area: title-pro; }
 
</style>