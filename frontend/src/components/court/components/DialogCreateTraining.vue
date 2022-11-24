<template>
  <q-dialog v-model="showComputed">
    <q-card>
      <q-card-section>
        <div class="text-h6">{{ t('court.index.dialog.title') }}</div>
      </q-card-section>

      <q-card-section class="flex flex-col gap-4">
        <q-select label="Joueur *" filled v-model="newTraining.personId" :options="[]" map-options></q-select>
        <div class="flex gap-4">
          <InputDate v-model="newTraining.dateStart" label="Date *"></InputDate>
          <InputDate v-model="newTraining.dateStart" label="Heure *"></InputDate>
        </div>
        <q-input filled v-model="newTraining.duration" type="number"></q-input>
      </q-card-section>

      <q-card-actions align="right" class="text-primary my-4">
        <q-btn color="primary" :loading="loaderBtn" :label="t('btn.confirm')" @click="handleCreateTraining"/>
        <q-btn flat :label="t('btn.cancel')" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script setup lang="ts">
import InputDate from 'src/components/shared/InputDate.vue';
import { useUserStore } from 'src/stores/user'
import { computed, ref } from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps<{
  show: boolean
}>()

const emit = defineEmits<{
  (name: 'update:show', value: boolean): boolean
}>()

const { t } = useI18n()
const userStore = useUserStore()

const loaderBtn = ref(false)

const newTraining = ref({
  personId: null,
  dateStart: null,
  duration: 0,
  courtId: null
})

const showComputed = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value)
})


const handleCreateTraining = async () => {
  // POST training and await back
}

</script>