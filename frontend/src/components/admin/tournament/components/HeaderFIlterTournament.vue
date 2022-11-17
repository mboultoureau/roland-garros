<template>
  <div class="bg-gray-100 p-8 rounded-md">
    <div class="flex items-center"><FourCercle />
      <div class="m-auto text-2xl font-bold">{{ t('tournament.index.title') }}</div>
    </div>
    <div class="flex gap-4 mt-16">
      <q-select filled v-model="typeSelected" label="Sélectionnez type" :options="optionsType" class="flex-1"></q-select>
      <q-select filled v-model="phaseSelected" label="Sélectionnez phase" :options="optionsPhase" class="flex-1"></q-select>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useI18n } from 'vue-i18n';
import FourCercle from 'src/components/shared/FourCercle.vue';
import { computed, ref, watch } from 'vue';
import { Round, Tournament, TournamentType } from 'src/models/tournament';

const props = defineProps<{
  tournament: Tournament
}>()

const emit = defineEmits<{
  (name: 'handle-select-type', value: TournamentType): TournamentType
  (name: 'handle-select-round', value: Round): Round 
}>()

const { t } = useI18n()

const optionsType = computed(() => {
  const options = [] as object[]
  if(props.tournament) {
    props.tournament?.types?.forEach((type) => options.push({
      label: t(`tournament.type.${type}`),
      value: type,
    }))
  }
  return options
})

const optionsPhase = ref([
  {
    label: t('match.round.FIRST_ROUND'),
    value: Round.FIRST_ROUND
  },
  {
    label: t('match.round.SECOND_ROUND'),
    value: Round.SECOND_ROUND
  },
  {
    label: t('match.round.THIRD_ROUND'),
    value: Round.THIRD_ROUND,
  },
  {
    label: t('match.round.SIXTEENTH_ROUND'),
    value: Round.SIXTEENTH_ROUND
  },
  {
    label: t('match.round.QUARTER_FINAL'),
    value: Round.QUARTER_FINAL
  },
  {
    label: t('match.round.SEMI_FINAL'),
    value: Round.SEMI_FINAL
  },
  {
    label: t('match.round.FINAL_ROUND'),
    value: Round.FINAL_ROUND,
  },
])

const typeSelected = ref()
const phaseSelected = ref()

watch(
  typeSelected,
  () => emit('handle-select-type', typeSelected.value.value)
)
watch(
  phaseSelected,
  () => emit('handle-select-round', phaseSelected.value.value)
)
</script>
