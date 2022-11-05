<template>
  <div class="player xl:96 lg:mx-68 md:mx-48" :class="{'blur': showLoader}">
    <PlayerCard :player="player"></PlayerCard>
    <PlayerGeneral :player="player"></PlayerGeneral>
  </div>
  <q-inner-loading :showing="showLoader">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script setup lang="ts">
import { useRoute } from 'vue-router';
import { computed, onMounted } from 'vue'
import { usePlayerStore } from 'src/stores/player';
import PlayerCard from './components/PlayerCard.vue';
import PlayerGeneral from './components/PlayerGeneral.vue';

const route = useRoute()
const playerStore = usePlayerStore()

const playerId = parseInt(route.params.id as string)
const player = computed(() => playerStore.player || {})

const showLoader = computed(() => Object.entries(player.value).length === 0 || player.value?.id !== playerId)

onMounted(async () => {
  await playerStore.show(playerId)
})
</script>