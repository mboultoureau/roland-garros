<template>
  <div class="player">
    <PlayerCard :player="player"></PlayerCard>
    <PlayerGeneral :player="player"></PlayerGeneral>
  </div>
  <Loader v-model:show="showLoader" hide-back /> 
</template>
<script setup lang="ts">
import { useRoute } from 'vue-router';
import { computed, onMounted } from 'vue'
import { usePlayerStore } from 'src/stores/player';
import PlayerCard from './components/PlayerCard.vue';
import PlayerGeneral from './components/PlayerGeneral.vue';
import Loader from '../shared/Loader.vue';

const route = useRoute()
const playerStore = usePlayerStore()

const playerId = parseInt(route.params.id as string)
const player = computed(() => playerStore.player || {})

const showLoader = computed(() => Object.entries(player.value).length === 0 || player.value?.id !== playerId)

onMounted(async () => {
  await playerStore.show(playerId)
})
</script>