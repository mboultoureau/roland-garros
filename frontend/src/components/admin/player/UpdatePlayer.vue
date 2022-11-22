<template>
  <PlayerForm v-model:player="editPlayer" @submit="submit" @cancel="goBack"></PlayerForm>
  <dialogConfirm v-model:show="showConfirm" :loading-btn="loadingBtn" :label="t('dialog.confirm.edit', { type: 'ce joueur'})" @handle-confirm="confirmEdit"></dialogConfirm>
</template>
<script lang="ts" setup>
import PlayerForm from './components/PlayerForm.vue';
import DialogConfirm from './components/DialogConfirm.vue';
import { onMounted, ref } from 'vue'
import { usePlayerStore } from 'src/stores/player';
import { useRoute, useRouter } from 'vue-router';
import { Player } from 'src/models/person';
import { useI18n } from 'vue-i18n';

const playerStore = usePlayerStore()
const route = useRoute()
const router = useRouter()
const { t } = useI18n()

const editPlayer =ref({} as Player)

onMounted(() => {
  editPlayer.value = playerStore.getById(parseInt(route.params.id as string) as number) || {} as Player
  if(!Object.entries(editPlayer.value).length) goBack()
})

const showConfirm = ref(false)
const loadingBtn = ref(false)
const submit = () => showConfirm.value = true

const confirmEdit = async () => {
  loadingBtn.value = true
  await playerStore.editP(editPlayer.value)
  showConfirm.value = false
  loadingBtn.value = false
  goBack()
}

const goBack = () => router.go(-1)
</script>
