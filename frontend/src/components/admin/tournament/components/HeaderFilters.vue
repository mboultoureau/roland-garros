<template>
  <div class="bg-gray-100 p-8 rounded-md">
    <div class="flex items-center"><FourCercle />
      <div class="m-auto text-2xl font-bold">{{ t('tournament.index.title') }}</div>
      <div><q-btn color="primary" @click="openDialog = !openDialog">{{ t('tournament.index.btn.create') }}</q-btn></div>
    </div>
  </div>
  <DialogCreate v-model:show="openDialog"/>
</template>

<script setup lang="ts">
import FourCercle from 'src/components/shared/FourCercle.vue';
import DialogCreate from './DialogCreate.vue';
import { useI18n } from 'vue-i18n';
import { ref } from 'vue';

const { t } = useI18n()

const openDialog = ref(false)
</script>