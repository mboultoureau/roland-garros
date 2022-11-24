<template>
  <div class="mt-8 w-72 m-auto relative border-2 rounded-lg overflow-hidden group">
    <img class="h-fit object-cover w-full transition ease-in-out delay-150 hover:scale-110 duration-100" :src="player.picture" />
    <div class="translate-y-full h-full bg-gray-50 group-hover:translate-y-0 duration-300 absolute left-0 bottom-0 right-0 z-50 flex flex-col justify-center items-center text-xl">
      <div class="name font-bold text-3xl uppercase italic">{{ player.firstname }} {{ player.lastname }}</div>
      <div class="nationality text-gray-400 flex gap-x-4 mt-16"><img class="w-10 rounded-sm" :src="`https://www.rolandgarros.com/img/flags-svg/${player?.nationality.alpha3Code}.svg`"/>{{ player.nationality.name }}</div>
      <div class="age-hand text-gray-400">{{ age }} ans - {{ t(`player.show.hand.${player?.hand}`) }}</div>
      <div class="show-more mt-8"><q-btn class="bg-primary text-white" @click="goShowPlayer">Voir plus</q-btn></div>
    </div>
    <div class="absolute top-0 right-0 m-8 text-4xl font-bold italic text-primary"># {{ player.ranking }}</div>
  </div>
  <q-inner-loading :showing="showLoader">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script setup lang="ts">
import { Person } from 'src/models/person';
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import { useRouter } from 'vue-router';

const props = defineProps<{
  player: Person
}>()

const { t } = useI18n()

const router = useRouter()
const showLoader = computed(() => Object.entries(props.player).length === 0)

const goShowPlayer = () => router.push({ name: 'show-player', params: { id: props.player.id }})

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
