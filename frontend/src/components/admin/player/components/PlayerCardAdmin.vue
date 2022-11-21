<template>
  <div class="grid grid-cols-[auto_repeat(4,_1fr)_auto] items-center border-2 rounded-lg">
    <img :src="player.picture" class="w-24">
    <div class="text-center">{{ player.firstname }}</div>
    <div class="text-center">{{ player.lastname }}</div>
    <div class="text-center">{{ player.nationality.name }}</div>
    <div class="text-center">{{ player.birthDate }}</div>
    <div class="flex flex-nowrap">
      <q-btn class="w-fit" flat icon="visibility" @click="handleShow"></q-btn>
      <q-btn class="w-fit" flat icon="edit" @click="handleEdit"></q-btn>
      <q-btn class="w-fit text-red-600" flat @click="handleDelete" icon="delete"></q-btn>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { Person } from 'src/models/person';

const emit = defineEmits<{
  (name: 'handle-delete', value: Person): Person,
  (name: 'handle-edit', value: Person): Person,
  (name: 'handle-show', value: Person): Person,
}>()

const props = defineProps<{
  player: Person
}>()

const handleDelete = () => emit('handle-delete', props.player)

const handleEdit = () => emit('handle-edit', props.player)

const handleShow = () => emit('handle-show', props.player)
</script>
