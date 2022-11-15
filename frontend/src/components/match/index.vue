<template>
  <MatchCard class="mx-48 my-16 rounded-md" :match="matchComputed" />
</template>
<script lang="ts">
import { computed, onMounted } from 'vue';
import { useRoute } from 'vue-router'
import { useMatchStore } from 'src/stores/match';
export default {
  name: 'IndexComponentMatch'
}
</script>
<script setup lang="ts">
import MatchCard from './components/MatchCard.vue';

const route = useRoute()
const matchStore = useMatchStore()

const matchComputed = computed(() => matchStore.match)

onMounted(async () => await matchStore.show(route.params.idM as string))
</script>