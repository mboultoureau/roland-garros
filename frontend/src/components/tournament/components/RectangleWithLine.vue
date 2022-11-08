<template>
  <div class="bg-gray-300 hover:bg-tertiary px-4 py-2 flex flex-col justify-around min-h-full cursor-pointer" :class="{'bg-tertiary': isSelected }" >
    <Line v-for="(nb, index) in nbLine" :key="index" class="my-0.5"></Line>
  </div>
</template>
<script setup lang="ts">
import Line from './Line.vue';

const props = defineProps<{
  nbLine: number,
  isSelected: boolean
}>()

</script>