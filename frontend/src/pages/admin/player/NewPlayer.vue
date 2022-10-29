<template>
  <PlayerForm v-model:player="formCopy" @submit="submit"></PlayerForm>
</template>
<script lang="ts" setup>
import PlayerForm from 'src/components/admin/player/components/PlayerForm.vue';
import { ref } from 'vue'
import { Player } from 'src/models/person';

const formCopy = ref({
  firstname: 'First name',
  lastname: 'Last name',
  birthday: '',
  birthplace: '',
  hand: 'right',
  height: 0,
  weight: 0,
  'career-start': '',
  coach: {} as Player,
  nationality: '',
} as Player)

const submit = () => console.log(formCopy.value)
</script>
