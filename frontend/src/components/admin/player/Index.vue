<template>
  <div class="administration">
    <div class="text-2xl text-center m-8">
      Administration
    </div>
    <div class="list-player flex justify-center">
      <ListPlayer></ListPlayer>
    </div>
  </div>
</template>
<script lang="ts">
export default {
  name: 'IndexAdmin'
}
</script>
<script lang="ts" setup>
import ListPlayer from './components/ListPlayers.vue'
</script>
