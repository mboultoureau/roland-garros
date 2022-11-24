<template>
  <div class="bg-gray-100 p-8 rounded-md mb-8">
    <div class="flex items-center">
      <FourCercle />
      <div class="m-auto text-2xl font-bold">{{ title }}</div>
      <slot name="btn"></slot>
    </div>
    <slot class="mt-8"></slot>
  </div>
</template>
<script lang="ts">
import FourCercle from './FourCercle.vue';
export default {
  name: 'HeaderComponent'
}
</script>
<script setup lang="ts">
const props = defineProps<{
  title: string
}>()
</script>