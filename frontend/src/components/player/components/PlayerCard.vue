<template>
  <div class="flex justify-between border-2 rounded-lg p-8">
    <img class="rounded-full overflow-hidden w-fit" :src="player?.picture"/>
    <div class="flex flex-col flex-1 justify-around px-16">
      <div>
        <div class="text-4xl font-bold">{{ player?.firstname }} {{ player?.lastname }}</div>
        <div class="text-gray-400 text-xl">{{ age }} ans</div>
      </div>
      <div class="flex items-center gap-8 font-bold text-gray-400">
        <img class="rounded-md w-12" :src="`https://www.rolandgarros.com/img/flags-svg/${player?.nationality?.alpha3Code}.svg`"/>
        <span>{{ player?.nationality?.name }}</span>
      </div>
      <div class="flex gap-2">
        <q-icon class="p-2 bg-primary rounded-md text-white" size="xs" name="fa-brands fa-facebook"></q-icon>
        <q-icon class="p-2 bg-primary rounded-md text-white" size="xs" name="fa-brands fa-instagram"></q-icon>
        <q-icon class="p-2 bg-primary rounded-md text-white" size="xs" name="fa-brands fa-twitter"></q-icon>
        <q-icon class="p-2 bg-primary rounded-md text-white" size="xs" name="fa-brands fa-linkedin-in"></q-icon>
      </div>
    </div>
    <div class="flex flex-col justify-center text-center">
      <div class="text-primary text-8xl mb-8">{{ player.ranking }}</div>
      <div class="uppercase text-gray-400">{{ t('player.show.individual.ranking') }}</div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { computed } from 'vue'
import { useI18n } from 'vue-i18n';
import { Person } from 'src/models/person'

const { t } = useI18n()

const props = defineProps<{
  player: Person
}>()

const age = computed(() => {
  const today = new Date();
  const birthDate = new Date(props.player?.birthDate);
  let age = today.getFullYear() - birthDate.getFullYear();
  const m = today.getMonth() - birthDate.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
  }
  return age;
})
</script>