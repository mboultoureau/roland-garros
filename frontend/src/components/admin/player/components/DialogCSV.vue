<template>
  <q-dialog v-model="showComputed" persistent>
    <q-card class="p-4">
      <q-card-section class="row items-center">
          <div class="text-h6">{{ t('tournament.dialog.create.title') }}</div>
      </q-card-section>

      <q-card-section>
        <q-file color="teal" filled accept=".csv" v-model="fileUpload" :label="t('input.upload.csv')" class="w-96">
          <template v-slot:prepend>
            <q-icon name="cloud_upload" />
          </template>
        </q-file>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat :label="t('btn.cancel')" color="primary" v-close-popup />
        <q-btn :label="t('btn.confirm')" color="primary" @click="handleConfirm" :loading="loadingBtn"/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script lang="ts" setup>
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n'
import { api } from 'src/boot/axios';
import { useRouter } from 'vue-router';

const { t } = useI18n()
const router = useRouter()

const emit = defineEmits<{
  (name: 'update:show', value: boolean): boolean
  (name: 'handle-confirm'): void
}>()
const props = defineProps<{
  show: boolean,
}>()

const showComputed = computed({
  get: () => props.show,
  set: () => emit('update:show', false)
})

const fileUpload = ref<File>()
const loadingBtn = ref(false)

const handleConfirm = async () => {
  loadingBtn.value = true
  const formData = new FormData()
  formData.append('file', fileUpload.value)
  await api.post('/players/upload', formData, {headers: {
    'Content-Type': 'multipart/form-data',
  }})
  loadingBtn.value = false
  router.back()
}
</script>

