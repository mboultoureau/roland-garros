<template>
  <div v-if="hasTwoPlayer" class="relative h-96 w-32">
    <img :src="teamComputed?.personA.picture" class="absolute top-0 left-0 m-auto rounded-full"/>
    <img :src="teamComputed?.personB.picture" class="absolute top-0 left-32 m-auto rounded-full"/>
  </div>
  <div v-else>
    <img :src="teamComputed?.personA.picture" class="m-auto rounded-full"/>
  </div>
</template>

<script setup lang="ts">
import { Team } from 'src/models/match';
import { computed } from 'vue';

const props = defineProps<{
  team: Team
}>()

const hasTwoPlayer = computed(() => {
  return (props.team && props.team?.personB)
})

const teamComputed = computed(() => props.team)
</script>