<template>
  <div class="administration">
    <HeaderComponent :title="t('admin.player.index.title')"></HeaderComponent>
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
import { onMounted, ref, watch } from 'vue';
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
