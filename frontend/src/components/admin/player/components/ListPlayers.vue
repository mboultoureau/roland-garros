<template>
  <div class="card-list bg-gray-100 rounded-xl p-8 m-8 w-fit grid gap-4 lg:grid-cols-3 md:grid-cols-2 xs:grid-cols-1">
    <div class="player m-2 cursor-pointer" v-for="player in listComputed" :key="player.id">
      <CardPlayer :player="player" @handle-edit="handleEdit"></CardPlayer>
    </div>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
    <SkeletonCard v-if="showSkeleton"></SkeletonCard>
  </div>
</template>
<script lang="ts" setup>
import { Player } from 'src/models/person';
import { usePlayerStore } from 'src/stores/player';
import { computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import CardPlayer from './CardPlayer.vue';
import SkeletonCard from './SkeletonCard.vue';

const router = useRouter()
const playerStore = usePlayerStore()

const listComputed = computed(() => playerStore.listPlayer)

const showSkeleton = computed(() => listComputed.value?.length === 0)

const handleEdit = (player: Player) => router.push({ name: 'edit', params: { id: player.id }})

onMounted(async () => await playerStore.fetch())
</script>
