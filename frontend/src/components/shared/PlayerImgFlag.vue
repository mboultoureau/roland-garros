<template>
  <div class="relative w-24 h-12" v-if="isTeamDouble">
    <div class="absolute top-0 left-0 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
      <img v-if="team" class="w-12 h-12" :src="team?.personA.picture"/>
      <q-skeleton v-else animation="none" class="w-12 h-12 bg-gray-200" type="QAvatar" />
    </div>
    <div class="absolute top-0 left-10 bg-gray-100 border-2 border-gray-200 rounded-full overflow-hidden">
      <img v-if="team" class="w-12 h-12" :src="team?.personB?.picture"/>
      <q-skeleton v-else animation="none" class="w-12 h-12 bg-gray-200" type="QAvatar" />
    </div>
  </div>

  <div class="relative" v-else>
    <div class="border-2 border-gray-200 rounded-full overflow-hidden">
      <img v-if="team" class="w-12 h-12" :src="team.personA.picture"/>
      <q-skeleton v-else animation="none" class="w-12 h-12 bg-gray-200" type="QAvatar" />
    </div>
    <img v-if="team" class="rounded-sm absolute bottom-0 -right-4 w-8 h-6" :src="`https://www.rolandgarros.com/img/flags-svg/${team.personA.nationality.alpha3Code}.svg`" />
  </div>

</template>
<script setup lang="ts">
import { Team } from 'src/models/match'
import { computed } from 'vue';

const props = defineProps<{
  team: Team | null | undefined,
}>()

const isTeamDouble = computed(() => props.team?.personB)
</script>