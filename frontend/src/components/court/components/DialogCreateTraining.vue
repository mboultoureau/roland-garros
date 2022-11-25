<template>
  <q-dialog v-model="showComputed">
    <q-card>
      <q-card-section>
        <div class="text-h6">{{ t('court.index.dialog.title') }}</div>
      </q-card-section>

      <q-card-section class="flex flex-col gap-4">
        <q-select label="Joueur *" filled v-model="newTraining.playerId" :options="optionsPlayers" option-label="lastname" option-value="id" map-options emit-value></q-select>
        <div class="flex gap-4">
          <InputDate v-model="date" label="Date *"></InputDate>
          <InputDate is-time-input v-model="time" label="Heure *"></InputDate>
        </div>
        <q-input filled v-model="newTraining.duration" type="number"></q-input>
        <q-select filled v-model="newTraining.courtId" label="Sélectionner un court" :options="optionsCourts" map-options option-value="id" option-label="name" emit-value></q-select>
        <q-input filled v-model="newTraining.details"></q-input>
      </q-card-section>

      <q-card-actions align="right" class="text-primary my-4">
        <q-btn color="primary" :loading="loaderBtn" :label="t('btn.confirm')" @click="handleCreateTraining"/>
        <q-btn flat :label="t('btn.cancel')" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script setup lang="ts">
import { useMatchCard } from 'src/components/match/functions/match';
import InputDate from 'src/components/shared/InputDate.vue';
import { Training } from 'src/models/court';
import { useCourtStore } from 'src/stores/court';
import { usePlayerStore } from 'src/stores/player';
import { computed, onMounted, ref } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router';

const props = defineProps<{
  show: boolean,
}>()

const emit = defineEmits<{
  (name: 'update:show', value: boolean): boolean
}>()

const router = useRouter()
const { t } = useI18n()
const courtStore = useCourtStore()
const playerStore = usePlayerStore()

const optionsPlayers = ref([])
const optionsCourts = computed(() => courtStore.listCourt)

onMounted(async () => optionsPlayers.value = await playerStore.fetchAll())

const loaderBtn = ref(false)

const date = ref()
const time = ref()

const newTraining = ref({
  playerId: null,
  duration: 0,
  courtId: null,
  details: null,
} as Training)

const showComputed = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value)
})

const handleCreateTraining = async () => {
  loaderBtn.value = true
  await courtStore.postTraining({ ...newTraining.value, startDate: `${date.value}T${time.value}`})
  loaderBtn.value = false
  showComputed.value = false
}

</script>