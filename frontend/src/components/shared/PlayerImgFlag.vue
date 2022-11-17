<template>
  <div class="relative">
    <div class="border-2 border-gray-200 rounded-full overflow-hidden">
      <img v-if="picture" class="w-12 h-12" :src="picture"/>
      <q-skeleton v-else animation="none" class="w-12 h-12 bg-gray-200" type="QAvatar" />
    </div>
    <img v-if="nationality" class="rounded-sm absolute bottom-0 -right-4 w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${nationality.alpha3code}.svg`" />
  </div>
</template>
<script setup lang="ts">
import { Nationality } from 'src/models/person'

const props = defineProps<{
  picture: string | null | undefined,
  nationality: Nationality | null | undefined
}>()
</script>