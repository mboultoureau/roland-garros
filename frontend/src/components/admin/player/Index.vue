<template>
  <div class="administration">
    <div class="text-2xl text-center m-8">
      Administration Joueur
    </div>
    <div class="list-player flex justify-center">
      <ListPlayer></ListPlayer>
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
</script>
