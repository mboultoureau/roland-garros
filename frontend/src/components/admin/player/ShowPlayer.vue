<template>
  <div class="player">
    <DisplayPlayerCircle :player="player"></DisplayPlayerCircle>
  </div> 
  <div class="coach"></div> 
</template>
<script setup lang="ts">
import { useRoute } from 'vue-router';
import DisplayPlayerCircle from '../../shared/DisplayPlayerCircle.vue'
import { computed, onMounted } from 'vue'
import { usePlayerStore } from 'src/stores/player';

const route = useRoute()
const playerStore = usePlayerStore()

const player = computed(() => playerStore.player || {})

onMounted(async () => {
  await playerStore.show(parseInt(route.params.id as string))
})
</script>
<style lang="scss">
</style>