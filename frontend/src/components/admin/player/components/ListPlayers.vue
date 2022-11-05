<template>
  <div class=" p-8 xl:mx-96 lg:mx-68">
    <div class="flex">
      <q-select class="w-fit rounded-lg" outlined v-model="filtersComputed.sexe" map-options :options="optionsTab"></q-select>
      <div class="flex flex-nowrap gap-2 ml-auto">
        <q-select label="Filtre" class="rounded-lg w-48" outlined v-model="filtersComputed.filter" :options="optionsFilter" map-options>
          <template v-slot:prepend>
            <q-icon name="filter_list" />
          </template>
        </q-select>
        <q-btn class="h-full rounded-md bg-primary text-white" unelevated :icon="iconComputed" @click="handleSort">Trie</q-btn>
      </div>
    </div>
    <div class="card-list">
      <CardPlayer class="cursor-pointer my-4 w-full" v-for="player in listComputed" :key="player.id" :player="player" @handle-edit="handleEdit" @handle-delete="handleDelete" @handle-show="handleShow"></CardPlayer>
      <SkeletonCard v-if="showSkeleton"></SkeletonCard>
      <SkeletonCard v-if="showSkeleton"></SkeletonCard>
      <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    </div>
  </div>
  <DialogDeletePlayer v-model:show="showDialog" :loading-btn="loadingBtn" @handle-confirm="confirmDelete"></DialogDeletePlayer>
</template>
<script lang="ts" setup>
import { FilterPlayer, Player } from 'src/models/person';
import { usePlayerStore } from 'src/stores/player';
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import CardPlayer from './PlayerCardAdmin.vue';
import SkeletonCard from './SkeletonCard.vue';
import DialogDeletePlayer from './DialogDeletePlayer.vue';
import { route } from 'quasar/wrappers';

const props = defineProps<{
  filters: FilterPlayer
}>()

const emit = defineEmits<{
  (name: 'update:filters', value: FilterPlayer): FilterPlayer
}>()

const filtersComputed = computed({
  get: () => props.filters,
  set: (filters) => emit('update:filters', filters )
})

const router = useRouter()
const playerStore = usePlayerStore()

const listComputed = computed(() => playerStore.listPlayer)

const showSkeleton = computed(() => listComputed.value?.length === 0)

const showDialog = ref(false)
const deletePlayer = ref({} as Player)
const loadingBtn = ref(false)

const handleDelete = (player: Player) => {
  showDialog.value = true
  deletePlayer.value = player
}

const confirmDelete = async () => {
  loadingBtn.value = true
  await playerStore.delete(deletePlayer.value.id)
  showDialog.value = false
  loadingBtn.value = false
}

const handleShow = (player: Player) => {
  router.push({ name: 'show-player', params: { id: player.id }})
}

const handleEdit = (player: Player) => router.push({ name: 'edit', params: { id: player.id }})

const optionsTab = [
  { label: 'Hommes', value: 'men' },
  { label: 'Femmes', value: 'woman' },
]

const optionsFilter = [
  { label: 'Nom', value: 'lastname' },
  { label: 'Prénom', value: 'firstname' },
  { label: 'Age', value: 'birthday' },
  { label: 'Nationnalitée', value: 'nationnality' },
]

const iconComputed = computed(() => {
  let sortIcon = ''
  switch(filtersComputed.value.sort) {
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
  if(filtersComputed.value.sort === null) filtersComputed.value.sort = 'asc'
  else if(filtersComputed.value.sort === 'asc') filtersComputed.value.sort  = 'desc'
  else filtersComputed.value.sort = null
}

</script>
