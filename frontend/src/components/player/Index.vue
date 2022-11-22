<template>
  <headerC :title="t('player.index.title')"></headerC>
  <q-tabs
    v-model="currentTab"
    narrow-indicator
    dense
    align="justify"
    class="text-primary m-8"
  >
    <q-tab :ripple="false" :name="Gender.MEN" icon="face" :label="t('player.index.tabs.label.man')" />
    <q-tab :ripple="false" :name="Gender.WOMAN" icon="face_3" :label="t('player.index.tabs.label.woman')" />
  </q-tabs>
  
  <q-tab-panels v-model="currentTab" animated>
    <q-tab-panel :name="Gender.MEN">
      <div class="flex gap-8">
        <ItemList v-for="player in listPlayer" :key="player.id" :player="player" class="m-auto"></ItemList>
      </div>
    </q-tab-panel>
    <q-tab-panel :name="Gender.WOMAN">
      <div class="grid xl:grid-cols-3 lg:grid-cols-2 grid-cols-1">
        <ItemList v-for="player in listPlayer" :key="player.id" :player="player"></ItemList>
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
}
</script>
<script setup lang="ts">
import { usePlayerStore } from 'src/stores/player';
import { computed,ref, watch, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import ItemList from './components/PlayerItemList.vue';
import { Gender } from 'src/models/person';
import HeaderC from '../shared/Header.vue';

const { t } = useI18n()

const playerStore = usePlayerStore()

const listPlayer = computed(() => playerStore.listPlayer)
const showLoader = computed(() => listPlayer.value?.length === 0)

onMounted(async () => await playerStore.fetch({ gender: Gender.MEN }))

const currentTab = ref(Gender.MEN)

watch(
  currentTab,
  async () => await playerStore.fetch({ gender: currentTab.value })
)
</script>
