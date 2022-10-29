<template>
  <q-card class="w-64 z-50 transition ease-in-out delay-250 hover:-translate-y-1 hover:scale-105 duration-200">
    <img :src="`https://cdn.quasar.dev/img/avatar${(player.id) % 6 + 1}.jpg`">

    <q-card-section>
      <div class="text-h6">{{ player.firstname }} </div>
      <div class="text-subtitle2">{{ player.lastname }}</div>
    </q-card-section>

    <q-card-actions align="around">
      <q-btn flat icon="visibility"></q-btn>
      <q-btn flat icon="edit"></q-btn>
      <q-btn flat class="text-red-600" icon="delete" @click="handleDelete"></q-btn>
    </q-card-actions>
  </q-card>
</template>
<script lang="ts" setup>
import { Player } from 'src/models/person';

const emit = defineEmits<{
  (name: 'handle-delete', value: Player): Player
}>()

const props = defineProps<{
  player: Player
}>()

const handleDelete = () => emit('handle-delete', props.player)
</script>
