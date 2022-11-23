<template>
  <Header :title="t('admin.player.create.title')"></Header>
  <PlayerForm class="mt-8" v-model:player="formCopy" @submit="submit" @cancel="cancel"></PlayerForm>
</template>
<script lang="ts" setup>
import PlayerForm from './components/PlayerForm.vue';
import Header from 'src/components/shared/Header.vue';
import { ref } from 'vue'
import { usePlayerStore } from 'src/stores/player';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()
const playerStore = usePlayerStore()
const router = useRouter()

const formCopy = ref({
  firstname: '',
  lastname: '',
  birthDate: '',
  birthPlace: '',
  hand: 'RIGHT',
  height: 0,
  weight: 0,
  earlyCareer: '',
  coach: '',
  nationality: null,
  picture: '',
  isPlayer: true,
  isCoach: false
})

const submit = async () => {
  await playerStore.store(formCopy.value)
  router.push({ name: 'players' })
}

const cancel = () => router.push({ name: 'players' })
</script>
