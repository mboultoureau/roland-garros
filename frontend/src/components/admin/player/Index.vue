<template>
  <div class="administration">
    <div class="text-2xl text-center m-8">
      Administration Joueur
    </div>
    <div class="list-player">
      <ListPlayer v-model:filters="filters"></ListPlayer>
    </div>
    <ButtonAddPlayer></ButtonAddPlayer>
  </div>
</template>
<script lang="ts">
export default {
    name: 'IndexAdmin',
    components: { ButtonAddPlayer }
}
</script>
<script lang="ts" setup>
import ListPlayer from './components/ListPlayers.vue'
import ButtonAddPlayer from './components/ButtonAddPlayer.vue';
import { onMounted, ref, watch } from 'vue';
import { usePlayerStore } from 'src/stores/player';
import { FilterPlayer } from 'src/models/person';

const playerStore = usePlayerStore()

const filters = ref({
  sexe: 'men',
  filter: null,
  sort: null,
} as FilterPlayer)

onMounted(async () => await playerStore.fetch(filters.value))

watch(
  filters.value,
  async () => await playerStore.fetch({
    sexe: filters.value.sexe?.value,
    filter: filters.value.filter?.value,
    sort: filters.value.sort,
  })
)
</script>
