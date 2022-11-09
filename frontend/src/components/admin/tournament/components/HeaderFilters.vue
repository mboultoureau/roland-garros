<template>
  <div class="bg-gray-100 p-8 rounded-md">
    <div class="flex items-center"><FourCercle />
      <div class="m-auto text-2xl font-bold">{{ t('tournament.index.title') }}</div></div>
  </div>
</template>
<script setup lang="ts">
import FourCercle from 'src/components/shared/FourCercle.vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()
</script>