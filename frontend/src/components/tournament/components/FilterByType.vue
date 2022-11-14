<template>
  <q-select filled clearable v-model="selectType" label="Sélectionnez un type" :options="optionsType" :disable="disableSelect"></q-select>
</template>
<script setup lang="ts">
import { Tournament, TournamentType } from 'src/models/tournament';
import { computed, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n';

const props = defineProps<{
  tournament: Tournament | null
}>()

const emit = defineEmits<{
  (name: 'handleSelectType', value: TournamentType | null): TournamentType
}>()

const { t } = useI18n()

const selectType = ref(null)

const optionsType = computed(() => {
  const options = [] as object[]
  if(props.tournament != undefined) {
    props.tournament?.types.forEach((type) => options.push({
      label: t(`tournament.type.${type}`),
      value: type,
    }))
  }
  return options
})

const disableSelect = computed(() => optionsType.value?.length === 0)

watch(
  selectType,
  () => emit('handleSelectType', selectType.value?.value)
)

watch(
  () => props.tournament,
  () => selectType.value = null
)
</script>