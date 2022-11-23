<template>
  <div class="scoreboard-navigation">
    <div class="container">
      <div class="title-1 text-gray-400">{{ t('match.round.FIRST_ROUND') }}</div>
      <div class="first-tour">
        <RectangleWithLine :nb-line="4" :is-selected="nbRectSelected === 1" @click="handleSelectedRect(1, Round.FIRST_ROUND)"/>
        <RectangleWithLine :nb-line="4" :is-selected="nbRectSelected === 2" @click="handleSelectedRect(2, Round.FIRST_ROUND)"/>
        <RectangleWithLine :nb-line="4" :is-selected="nbRectSelected === 3" @click="handleSelectedRect(3, Round.FIRST_ROUND)"/>
        <RectangleWithLine :nb-line="4" :is-selected="nbRectSelected === 4" @click="handleSelectedRect(4, Round.FIRST_ROUND)"/>
      </div>
      <div class="title-2 text-gray-400">{{ t('match.round.SECOND_ROUND') }}</div>
      <div class="second-tour">
        <RectangleWithLine :nb-line="6" :is-selected="nbRectSelected === 5" @click="handleSelectedRect(5, Round.SECOND_ROUND)"/>
        <RectangleWithLine :nb-line="6" :is-selected="nbRectSelected === 6" @click="handleSelectedRect(6, Round.SECOND_ROUND)"/>
      </div>
      <div class="title-3 text-gray-400">{{ t('match.round.THIRD_ROUND') }}</div>
      <div class="third-tour">
        <RectangleWithLine :nb-line="10" :nb-rectangle="1" :is-selected="nbRectSelected === 7" @click="handleSelectedRect(7, Round.THIRD_ROUND)"/>
      </div>
      <div class="title-8 text-gray-400">{{ t('match.round.SIXTEENTH_ROUND') }}</div>
      <div class="round-16">
        <RectangleWithLine :nb-line="8" :nb-rectangle="1" :is-selected="nbRectSelected === 8" @click="handleSelectedRect(8, Round.SIXTEENTH_ROUND)"/>
      </div>
      <div class="title-4 text-gray-400">{{ t('match.round.QUART_FINAL') }}</div>
      <div class="QUART-final">
        <RectangleWithLine :nb-line="4" :nb-rectangle="1" :is-selected="nbRectSelected === 9" @click="handleSelectedRect(9, Round.QUART_FINAL)"/>
      </div>
      <div class="title-half text-gray-400">{{ t('match.round.SEMI_FINAL') }}</div>
      <div class="half-final">
        <RectangleWithLine :nb-line="2" :nb-rectangle="1" :is-selected="nbRectSelected === 10" @click="handleSelectedRect(10, Round.SEMI_FINAL)"/>
      </div>
      <div class="title-final text-gray-400">{{ t('match.round.FINAL_ROUND') }}</div>
      <div class="final">
        <RectangleWithLine :nb-line="1" :nb-rectangle="1" :is-selected="nbRectSelected === 11" @click="handleSelectedRect(11, Round.FINAL_ROUND)"/>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { Round } from 'src/models/tournament';
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';
import RectangleWithLine from './RectangleWithLine.vue';

const emit = defineEmits<{
  (name: 'handleSelectRound', value: Round): Round
}>();

const { t } = useI18n()

const nbRectSelected = ref(0)

const handleSelectedRect = (number: number, round: Round) => {
  nbRectSelected.value = number
  emit('handleSelectRound', round)
}
</script>
<style lang="scss" scoped>
.container {  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-template-rows: 0.2fr 1.8fr;
  grid-auto-columns: 1fr;
  grid-auto-rows: 1fr;
  gap: 1rem 0.75rem;
  grid-auto-flow: row;
  grid-template-areas:
    "title-1 title-2 title-3 title-8 title-4 title-half title-final"
    "first-tour second-tour third-tour round-16 QUART-final half-final final";
}

.first-tour {  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr;
  gap: 1em 0px;
  grid-auto-flow: row;
  grid-template-areas:
    "."
    "."
    "."
    ".";
  grid-area: first-tour;
}

.second-tour {  display: grid;
  grid-template-columns: 1fr;
  grid-template-rows: 1fr 1fr;
  gap: 0.75em 0px;
  grid-auto-flow: row;
  grid-template-areas:
    "."
    ".";
  grid-area: second-tour;
}

.third-tour { grid-area: third-tour; }

.round-16 { grid-area: round-16; }

.QUART-final { grid-area: QUART-final; }

.half-final { grid-area: half-final; }

.final { grid-area: final; }

.title-1 { grid-area: title-1; text-align: center; }
.title-2 { grid-area: title-2; text-align: center; }
.title-3 { grid-area: title-3; text-align: center; }
.title-8 { grid-area: title-8; text-align: center; }
.title-4 { grid-area: title-4; text-align: center; }
.title-half { grid-area: title-half; text-align: center;}

.title-final { grid-area: title-final; text-align: center;}
  
</style>