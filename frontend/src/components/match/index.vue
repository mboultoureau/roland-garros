<template>
  <MatchResult class="rounded-md" :match="matchComputed" />
  <Loader v-model:show="showLoader" hide-back></Loader>
</template>
<script lang="ts">
import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router'
import { useMatchStore } from 'src/stores/match';
import Loader from '../shared/Loader.vue';
export default {
    name: 'IndexComponentMatch',
    components: { Loader }
}
</script>
<script setup lang="ts">
import MatchResult from './components/MatchResult.vue';

const route = useRoute()
const matchStore = useMatchStore()

const matchComputed = computed(() => matchStore.match)
const showLoader = ref(false)
onMounted(async () => {
  showLoader.value = true
  await matchStore.show(route.params.idM as string)
  showLoader.value = false
})
</script>