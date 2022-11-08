<template>
  <div class="grid grid-cols-[auto_repeat(4,_1fr)_auto] items-center bg-gray-200 rounded-lg">
    <img :src="player.url" class="w-24">
    <div class="text-center">{{ player.firstname }}</div>
    <div class="text-center">{{ player.lastname }}</div>
    <div class="text-center">{{ player.nationality }}</div>
    <div class="text-center">{{ player.birthday }}</div>
    <div class="flex flex-nowrap">
      <q-btn class="w-fit" flat icon="visibility" @click="handleShow"></q-btn>
      <q-btn class="w-fit" flat icon="edit" @click="handleEdit"></q-btn>
      <q-btn class="w-fit text-red-600" flat @click="handleDelete" icon="delete"></q-btn>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { Player } from 'src/models/person';

const emit = defineEmits<{
  (name: 'handle-delete', value: Player): Player,
  (name: 'handle-edit', value: Player): Player,
  (name: 'handle-show', value: Player): Player,
}>()

const props = defineProps<{
  player: Player
}>()

const handleDelete = () => emit('handle-delete', props.player)

const handleEdit = () => emit('handle-edit', props.player)

const handleShow = () => emit('handle-show', props.player)
</script>
