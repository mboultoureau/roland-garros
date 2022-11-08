<template>
  <q-dialog v-model="showComputed" persistent>
    <q-card>
      <q-card-section class="row items-center">
        <q-avatar icon="account_circle" color="primary" text-color="white" />
        <span class="q-ml-sm">{{ label }}</span>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat :label="t('btn.cancel')" color="primary" v-close-popup />
        <q-btn flat :label="t('btn.confirm')" color="primary" @click="handleConfirm" :loading="loadingBtn"/>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script lang="ts" setup>
import { computed } from 'vue';
import { useI18n } from 'vue-i18n'
const { t } = useI18n()
const emit = defineEmits<{
  (name: 'update:show', value: boolean): boolean
  (name: 'handle-confirm'): void
}>()
const props = withDefaults(defineProps<{
  show: boolean,
  label: string,
  loadingBtn?: boolean,
}>(), {
  loadingBtn: false
})
const showComputed = computed({
  get: () => props.show,
  set: () => emit('update:show', false)
})
const handleConfirm = () => emit('handle-confirm')
</script>
