<template>
  <div class="card-list mt-8">
    <CardPlayer class="cursor-pointer my-4 w-full" v-for="player in listComputed" :key="player.id" :player="player" @handle-edit="handleEdit" @handle-delete="handleDelete" @handle-show="handleShow"></CardPlayer>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
  </div>
  <DialogDeletePlayer v-model:show="showDialog" :loading-btn="loadingBtn" @handle-confirm="confirmDelete"></DialogDeletePlayer>
</template>
<script lang="ts" setup>
import { FilterPlayer, Person } from 'src/models/person';
import { usePlayerStore } from 'src/stores/player';
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import CardPlayer from './PlayerCardAdmin.vue';
import SkeletonCard from './SkeletonCard.vue';
import DialogDeletePlayer from './DialogDeletePlayer.vue';

const router = useRouter()
const playerStore = usePlayerStore()

const props = defineProps<{
  filters: FilterPlayer
}>()

const listComputed = computed(() => playerStore.listPlayer)

const showSkeleton = computed(() => listComputed.value?.length === 0)

const showDialog = ref(false)
const deletePlayer = ref({} as Person)
const loadingBtn = ref(false)

const handleDelete = (player: Person) => {
  showDialog.value = true
  deletePlayer.value = player
}

const confirmDelete = async () => {
  loadingBtn.value = true
  await playerStore.delete(deletePlayer.value.id)
  await playerStore.fetch(props.filters)
  showDialog.value = false
  loadingBtn.value = false
}

const handleShow = (player: Person) => {
  router.push({ name: 'show-player', params: { id: player.id }})
}

const handleEdit = (player: Person) => router.push({ name: 'edit', params: { id: player.id }})
</script>
