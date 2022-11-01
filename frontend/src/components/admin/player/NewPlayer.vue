<template>
  <div class="bg-gray-200 p-8 rounded-lg w-fit mx-auto m-16">
    <PlayerForm v-model:player="formCopy" @submit="submit"></PlayerForm>
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
</script>
