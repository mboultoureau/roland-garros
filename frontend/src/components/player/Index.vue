<template>
  <headerC :title="t('player.index.title')"></headerC>
  <TabListPlayer v-model:current-tab="currentTab"></TabListPlayer> 
  <q-inner-loading :showing="showLoader">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script lang="ts">
export default {
  name: 'IndexComposant',
  components: { TabListPlayer }
}
</script>
<script setup lang="ts">
import { usePlayerStore } from 'src/stores/player';
import { ref, watch, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import { Gender } from 'src/models/person';
import HeaderC from '../shared/Header.vue';
import TabListPlayer from './components/TabListPlayer.vue';

const { t } = useI18n()

const playerStore = usePlayerStore()

const showLoader = ref(false)

onMounted(async () => await playerStore.fetch({ gender: Gender.MEN }))

const currentTab = ref(Gender.MEN)

watch(
  currentTab,
  async () => {
    showLoader.value = true
    await playerStore.fetch({ gender: currentTab.value })
    showLoader.value = false
  }
)
</script>
