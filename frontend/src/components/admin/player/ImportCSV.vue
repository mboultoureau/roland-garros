<template>
  <q-file filled v-model="file" accept=".csv" :hint="t('input.upload.hint.format')" :label="t('input.upload.player')">
    <template v-slot:prepend>
      <q-icon name="cloud_upload" />
    </template>
  </q-file>
</template>
<script setup lang="ts">
import { ref, watch } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const emit = defineEmits<{
  (name: 'import-file', value: File): File
}>()

const file = ref<File>()

watch(
  file,
  () => console.log(file.value) // emit('import-file', file.value)
)
</script>
