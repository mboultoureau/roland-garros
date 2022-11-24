<template>
  <div class="administration">
    <HeaderComponent :title="t('admin.player.index.title')">
      <template v-slot:default>
        <div class="flex mt-8">
          <q-select class="w-fit rounded-lg" outlined v-model="filters.gender" emit-value map-options :options="optionsTab"></q-select>
          <div class="flex flex-nowrap gap-2 ml-auto">
            <q-select label="Filtre" class="rounded-lg w-48" outlined v-model="filters.sort" emit-value :options="optionsFilter" map-options>
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
  sortBy: 'asc',
  sort: 'ranking',
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
  { label: 'Classement', value: 'ranking' },
]

const iconComputed = computed(() => {
  let sortIcon = ''
  switch(filters.value.sortBy) {
    case 'asc': sortIcon = 'arrow_upward';
    break
    case 'desc': sortIcon = 'arrow_downward'
    break
  }
  return sortIcon
})

const handleSort = () => {
  if(filters.value.sortBy === null) filters.value.sortBy = 'asc'
  else if(filters.value.sortBy === 'asc') filters.value.sortBy  = 'desc'
  else if(filters.value.sortBy === 'desc') filters.value.sortBy  = 'asc'
}
watch(
  filters.value,
  async () => { 
  loaderShow.value = true
  await playerStore.fetch({
    gender: filters.value.gender,
    sort: filters.value.sort,
    sortBy: filters.value.sortBy,
  })
  loaderShow.value = false
})
</script>
