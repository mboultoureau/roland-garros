<template>
    <HeaderC title="Statistiques">
    </HeaderC>

    <!-- BEST PLAYERS -->
    <h2>Meilleurs joueurs</h2>
    <div v-for="bestPlayer of bestPlayers" :key="bestPlayer.id">
        {{ bestPlayer.firstname }} {{ bestPlayer.lastname }} - {{ bestPlayer.victories }} victoires
    </div>

    <!-- BEST DURATIONS -->
    <h2>Plus grandes durées</h2>
    <div v-for="bestDuration of bestDurations" :key="bestDuration.id">
        {{ bestDuration.firstname }} {{ bestDuration.lastname }} - {{ bestDuration.duration }} heures jouées
    </div>
</template>
<script lang="ts">
export default {
    name: 'IndexPageStat'
}
</script>
<script setup lang="ts">
import { api } from 'src/boot/axios';
import HeaderC from 'src/components/shared/Header.vue';
import { onMounted, ref } from 'vue';

const bestPlayers = ref([])
const bestDurations = ref([])

onMounted(async () => {
    const response = await api.get('/statistics')
    bestDurations.value = response.data.bestDurations
    bestPlayers.value = response.data.bestPlayers
})
</script>