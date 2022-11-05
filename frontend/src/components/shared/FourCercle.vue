<template>
  <div class="mr-4">
    <div class="flex gap-1">
      <div class="rounded-full bg-primary" :style="{ 'width': size, 'height': size }"></div>
      <div class="rounded-full bg-primary" :style="{ 'width': size, 'height': size }"></div>
    </div>
    <div class="flex gap-1 mt-1">
      <div class="rounded-full bg-primary" :style="{ 'width': size, 'height': size }"></div>
      <div class="rounded-full bg-primary" :style="{ 'width': size, 'height': size }"></div>
    </div>
  </div>
  
</template>
<script setup lang="ts">
const props = withDefaults(
  defineProps<{
    size?: string,
  }>(), {
    size: '0.5rem'
  }
)
</script>