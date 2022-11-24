<template>
  <q-dialog v-model="showComputed">
    <q-card>
        <q-card-section>
          <div class="text-h6">{{ t('tournament.dialog.create.title') }}</div>
        </q-card-section>

        <q-card-section class="flex gap-4">
          <q-select :label="t('tournament.dialog.select.year')" class="flex-1 w-56" filled v-model="newTournament.year" :options="optionsYears"></q-select>
          <q-select :label="t('tournament.dialog.select.type')" class="flex-1 w-72" filled use-chips v-model="newTournament.types" emit-value map-options multiple :options="optionsTournamentTypes"></q-select>
        </q-card-section>

        <q-card-actions align="right" class="text-primary my-4">
          <q-btn color="primary" :loading="loaderBtn" :label="t('btn.confirm')" @click="handleCreateTournament"/>
          <q-btn flat :label="t('btn.cancel')" v-close-popup />
        </q-card-actions>
      </q-card>
  </q-dialog>
</template>
<script setup lang="ts">
import { TournamentType, optionsTournamentTypes, Tournament } from 'src/models/tournament'
import { useTournamentStore } from 'src/stores/tournament';
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()
const tournamentStore = useTournamentStore()

const props = defineProps<{
  show: boolean,
}>()

const emit = defineEmits<{
  (name: 'update:show', value: boolean):boolean
}>()

const newTournament = ref({
  types: [] as TournamentType[],
  year: null
} as Tournament)

const showComputed = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value) 
})

const loaderBtn = ref(false)

const handleCloseDialog = () => emit('update:show', false)

const handleCreateTournament = async () => {
  loaderBtn.value = true
  await tournamentStore.store(newTournament.value)
  newTournament.value = {
    types: [],
    year: null
  }
  handleCloseDialog()
  loaderBtn.value = false
}

const optionsYears = [
  2023,
  2024,
  2025,
  2026,
  2027,
  2028,
  2029,
  2030,
]

const optionsTournamentTypesLabel = optionsTournamentTypes.map((opt) => opt.label = t(`tournament.type.${opt.value}`))
</script>