<template>
  <q-tabs
    v-model="currentTab"
    narrow-indicator
    dense
    align="justify"
    class="text-primary m-8"
  >
    <q-tab :ripple="false" name="men" icon="face" :label="t('player.index.tabs.label.man')" />
    <q-tab :ripple="false" name="woman" icon="face_3" :label="t('player.index.tabs.label.woman')" />
  </q-tabs>
  
  <q-tab-panels v-model="currentTab" animated>
    <q-tab-panel name="men">
      <div class="grid gap-x-8 gap-y-4 xl:grid-cols-4 lg:grid-cols-3 md:grid-cols-2 items-center justify-center">
        <ItemList v-for="player in listPlayer" :key="player.id" :player="player" class="m-auto"></ItemList>
      </div>
    </q-tab-panel>
    <q-tab-panel name="woman">
      <div class="grid gap-x-8 xl:grid-cols-4 lg:grid-cols-3 md:grid-cols-2">
        <ItemList v-for="player in listPlayer" :key="player.id" :player="player" class="m-auto"></ItemList>
      </div>
    </q-tab-panel>
  </q-tab-panels>

  <q-inner-loading :showing="showLoader">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script lang="ts">
export default {
    name: 'IndexComposant',
    components: { ItemList }
}
</script>
<script setup lang="ts">
import { usePlayerStore } from 'src/stores/player';
import { computed,ref, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import ItemList from './components/PlayerItemList.vue';

const { t } = useI18n()

const playerStore = usePlayerStore()

const listPlayer = computed(() => playerStore.listPlayer)
const showLoader = computed(() => listPlayer.value?.length === 0)

onMounted(async () => await playerStore.fetch())

const currentTab = ref('men')
</script>
