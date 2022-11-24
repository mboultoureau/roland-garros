<template>
  <q-select filled clearable v-model="selectTournament" label="Sélectionnez un tournoi" :options="optionsTournament"></q-select>
</template>
<script setup lang="ts">
import { Tournament } from 'src/models/tournament';
import { computed, ref, watch } from 'vue'

const props = defineProps<{
  tournaments: Tournament[]
}>()

const emit = defineEmits<{
  (name: 'handleSelectTournament', value: Tournament | null): Tournament
}>()

const optionsTournament = computed(() => {
  const options = [] as object[]
  props.tournaments.forEach((tournament) => options.push({
    label: tournament.year,
    value: tournament.id,
    tournament: tournament
  }))
  return options
})

const selectTournament = ref(null)

watch(
  selectTournament,
  () => emit('handleSelectTournament', selectTournament.value?.tournament)
)
</script>