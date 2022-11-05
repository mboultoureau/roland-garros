<template>
  <div class="xl:mx-96 lg:mx-68">
    <PlayerForm v-model:player="formCopy" @submit="submit" @cancel="cancel"></PlayerForm>
  </div>
</template>
<script lang="ts" setup>
import PlayerForm from './components/PlayerForm.vue';
import { ref } from 'vue'
import { Player } from 'src/models/person';
import { usePlayerStore } from 'src/stores/player';
import { useRouter } from 'vue-router';

const playerStore = usePlayerStore()
const router = useRouter()

const formCopy = ref({
  firstname: '',
  lastname: '',
  birthday: '',
  birthplace: '',
  hand: 'right',
  height: 0,
  weight: 0,
  'career-start': '',
  coach: {} as Player,
  nationality: '',
} as Player)

const submit = () => {
  playerStore.store(formCopy.value)
  router.push({ name: 'players' })
}

const cancel = () => router.push({ name: 'players' })
</script>
