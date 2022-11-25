<template>
    <HeaderC title="Statistiques">
    </HeaderC>

    <!-- BEST PLAYERS -->
    <div class="w-full mx-auto bg-white rounded-sm border border-gray-200">
        <header class="px-5 py-4 border-b border-gray-100">
            <h2 class="font-semibold text-gray-800">Meilleurs joueurs</h2>
        </header>
        <div class="p-3">
            <div class="overflow-x-auto">
                <table class="table-auto w-full">
                    <thead class="text-xs font-semibold uppercase text-gray-400 bg-gray-50">
                        <tr>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Nom</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Nombre de victoires</div>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="text-sm divide-y divide-gray-100">
                        <tr v-for="bestPlayer of bestPlayers" :key="bestPlayer.id">
                            <td class="p-2 whitespace-nowrap">
                                <div class="flex items-center">
                                    <div class="w-10 h-10 flex-shrink-0 mr-2 sm:mr-3"><img class="rounded-full"
                                            :src="bestPlayer.picture"
                                            width="40" height="40" alt="{{ bestPlayer.firstname }} {{ bestPlayer.lastname }}"></div>
                                    <div class="font-medium text-gray-800">{{ bestPlayer.firstname }} {{ bestPlayer.lastname }}</div>
                                </div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-left font-medium text-green-500">{{ bestPlayer.victories }} victoires</div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- BEST DURATIONS -->
    <div class="w-full mx-auto bg-white rounded-sm border border-gray-200 mt-12">
        <header class="px-5 py-4 border-b border-gray-100">
            <h2 class="font-semibold text-gray-800">Joueurs avec la plus grande durée de jeu</h2>
        </header>
        <div class="p-3">
            <div class="overflow-x-auto">
                <table class="table-auto w-full">
                    <thead class="text-xs font-semibold uppercase text-gray-400 bg-gray-50">
                        <tr>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Nom</div>
                            </th>
                            <th class="p-2 whitespace-nowrap">
                                <div class="font-semibold text-left">Nombre d'heures</div>
                            </th>
                        </tr>
                    </thead>
                    <tbody class="text-sm divide-y divide-gray-100">
                        <tr v-for="bestDuration of bestDurations" :key="bestDuration.id">
                            <td class="p-2 whitespace-nowrap">
                                <div class="flex items-center">
                                    <div class="w-10 h-10 flex-shrink-0 mr-2 sm:mr-3"><img class="rounded-full"
                                            :src="bestDuration.picture"
                                            width="40" height="40" alt="{{ bestDuration.firstname }} {{ bestDuration.lastname }}"></div>
                                    <div class="font-medium text-gray-800">{{ bestDuration.firstname }} {{ bestDuration.lastname }}</div>
                                </div>
                            </td>
                            <td class="p-2 whitespace-nowrap">
                                <div class="text-left font-medium text-green-500">{{ bestDuration.duration }} heures</div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
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