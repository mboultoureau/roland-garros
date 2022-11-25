<template>
  <Header :title="t('tournament.index.title')">
    <template v-slot:default>
      <div class="flex gap-4 mt-16">
        <q-select filled v-model="typeSelected" :label="t('tournament.index.filtre.type')" :options="optionsType" class="flex-1"></q-select>
        <q-select filled v-model="phaseSelected" :label="t('tournament.index.filtre.round')" :options="optionsPhase" class="flex-1"></q-select>
      </div>
    </template>
  </Header>
</template>
<script setup lang="ts">
import { useI18n } from 'vue-i18n';
import Header from 'src/components/shared/Header.vue';
import { computed, ref, watch, onMounted } from 'vue';
import { Round, Tournament, TournamentType } from 'src/models/tournament';
import { useMatchStore } from 'src/stores/match';

const matchStore = useMatchStore()

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

onMounted(() => matchStore.resetList())

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
    label: t('match.round.QUART_FINAL'),
    value: Round.QUART_FINAL
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
