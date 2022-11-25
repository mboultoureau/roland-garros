<template>
  <q-tabs
    v-model="curTabComp"
    narrow-indicator
    dense
    align="justify"
    class="text-primary m-8"
  >
    <q-tab :ripple="false" :name="Gender.MEN" icon="face" :label="t('player.index.tabs.label.man')" />
    <q-tab :ripple="false" :name="Gender.WOMAN" icon="face_3" :label="t('player.index.tabs.label.woman')" />
  </q-tabs>
  
  <q-tab-panels v-model="curTabComp" animated>
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
</template>
<script setup lang="ts">
import { usePlayerStore } from 'src/stores/player';
import { computed } from 'vue';
import { useI18n } from 'vue-i18n';
import ItemList from './PlayerItemList.vue';
import { Gender } from 'src/models/person';

const { t } = useI18n()

const props = defineProps<{
  currentTab: string
}>()

const emit = defineEmits<{
  (name: 'update:currentTab', value: string): string
}>()

const curTabComp = computed({
  get: () => props.currentTab,
  set: (value) => emit('update:currentTab', value) 
})

const playerStore = usePlayerStore()

const listPlayer = computed(() => playerStore.listPlayer)

</script>