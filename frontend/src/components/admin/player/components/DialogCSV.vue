<template>
  <q-dialog v-model="showComputed" persistent>
    <q-card class="p-4">
      <q-card-section class="row items-center">
          <div class="text-h6">{{ t('tournament.dialog.create.title') }}</div>
      </q-card-section>

      <q-card-section>
        <q-file color="teal" filled accept=".csv" v-model="fileUpload" label="Label" class="w-96">
          <template v-slot:prepend>
            <q-icon name="cloud_upload" />
          </template>
        </q-file>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat :label="t('btn.cancel')" color="primary" v-close-popup />
        <q-btn flat :label="t('btn.confirm')" color="primary" @click="handleConfirm" :loading="loadingBtn"/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script lang="ts" setup>
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n'
const { t } = useI18n()
const emit = defineEmits<{
  (name: 'update:show', value: boolean): boolean
  (name: 'handle-confirm'): void
}>()
const props = defineProps<{
  show: boolean,
  loadingBtn: boolean
}>()

const showComputed = computed({
  get: () => props.show,
  set: () => emit('update:show', false)
})

const fileUpload = ref()

const handleConfirm = () => emit('handle-confirm')
</script>

