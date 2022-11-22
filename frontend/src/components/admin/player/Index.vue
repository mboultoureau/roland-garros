<template>
  <div class="administration">
    <HeaderComponent :title="t('admin.player.index.title')">
      <template v-slot:default>
        <div class="flex mt-8">
          <q-select class="w-fit rounded-lg" outlined v-model="filters.gender" map-options :options="optionsTab"></q-select>
          <div class="flex flex-nowrap gap-2 ml-auto">
            <q-select label="Filtre" class="rounded-lg w-48" outlined v-model="filters.filter" :options="optionsFilter" map-options>
              <template v-slot:prepend>
                <q-icon name="filter_list" />
              </template>
            </q-select>
            <q-btn class="h-full rounded-md bg-primary text-white" unelevated :icon="iconComputed" @click="handleSort">Trie</q-btn>
          </div>
        </div>
      </template>
    </HeaderComponent>
    <div class="list-player">
      <ListPlayer v-model:filters="filters"></ListPlayer>
    </div>
    <ButtonAddPlayer></ButtonAddPlayer>
  </div>
</template>
<script lang="ts">
export default {
    name: 'IndexAdmin',
    components: { ButtonAddPlayer, HeaderComponent }
}
</script>
<script lang="ts" setup>
import ListPlayer from './components/ListPlayers.vue'
import ButtonAddPlayer from './components/ButtonAddPlayer.vue';
import { onMounted, ref, computed, watch } from 'vue';
import { usePlayerStore } from 'src/stores/player';
import { FilterPlayer, Gender } from 'src/models/person';
import HeaderComponent from 'src/components/shared/Header.vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const playerStore = usePlayerStore()

const filters = ref({
  gender: Gender.MEN,
  filter: null,
  sort: null,
} as FilterPlayer)

const loaderShow = ref(false)

onMounted(async () => await playerStore.fetch(filters.value))

const optionsTab = [
  { label: 'Hommes', value: Gender.MEN },
  { label: 'Femmes', value: Gender.WOMAN },
]

const optionsFilter = [
  { label: 'Nom', value: 'lastname' },
  { label: 'Prénom', value: 'firstname' },
  { label: 'Age', value: 'birthday' },
  { label: 'Nationnalitée', value: 'nationnality' },
]

const iconComputed = computed(() => {
  let sortIcon = ''
  switch(filters.value.sort) {
    case null: sortIcon = 'swap_vert';
    break;
    case 'asc': sortIcon = 'arrow_upward';
    break
    case 'desc': sortIcon = 'arrow_downward'
    break
  }
  return sortIcon
})

const handleSort = () => {
  if(filters.value.sort === null) filters.value.sort = 'asc'
  else if(filters.value.sort === 'asc') filters.value.sort  = 'desc'
  else filters.value.sort = null
}
watch(
  filters.value,
  async () => { 
  loaderShow.value = true
  await playerStore.fetch({
    gender: filters.value.gender?.value,
    filter: filters.value.filter?.value,
    sort: filters.value.sort,
  })
  loaderShow.value = false
})
</script>
