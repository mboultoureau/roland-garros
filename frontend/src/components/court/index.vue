<template>
  <div class="container">
    <div class="agenda p-2 border-2 border-gray-100" v-for="agenda in horaire" :key="agenda">
      <div class="match w-full h-full bg-primary">{{ agenda }}</div>
    </div>
  </div>
</template>
<script setup lang="ts">
  const horaire = 54
</script>
<script lang="ts">
export default {
  name: 'IndexComponentCourt'
}
</script>
<style lang="scss" scoped>
.container {  
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-auto-columns: 1fr;
  grid-auto-rows: 1fr;
  gap: 0em 0px;
  grid-auto-flow: row;
  grid-template-areas:
    ". . . . . . . . ."
    ". . . . . . . . ."
    ". . . . . . . . ."
    ". . . . . . . . ."
    ". . . . . . . . ."
    ". . . . . . . . .";

    overflow-x: auto;

    .agenda {
      width: fit;
      height: 4rem;
    } 
}
</style>