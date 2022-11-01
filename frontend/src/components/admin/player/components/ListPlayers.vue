<template>
  <div class="card-list bg-gray-100 rounded-xl p-8 m-8 w-fit grid gap-4 lg:grid-cols-3 md:grid-cols-2 xs:grid-cols-1">
    <div class="player m-2 cursor-pointer" v-for="player in listComputed" :key="player.id">
      <CardPlayer :player="player" @handle-delete="handleDelete"></CardPlayer>
    </div>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
  </div>
  <dialogDeletePlayer v-model:show="showDialog" :loading-btn="loadingBtn" @handle-confirm="confirmDelete"></dialogDeletePlayer>
</template>
<script lang="ts" setup>
import { usePlayerStore } from 'src/stores/player';
import { computed, ref, onMounted } from 'vue';
import CardPlayer from './CardPlayer.vue';
import SkeletonCard from './SkeletonCard.vue';
import DialogDeletePlayer from './DialogDeletePlayer.vue';
import { Player } from 'src/models/person';


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

onMounted(async () => await playerStore.fetch())
</script>
