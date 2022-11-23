<template>
  <HeaderC :title="t('court.index.title')" class="mb-8">
    <template v-slot:btn v-if="hasRole">
      <q-btn color="primary" @click="showDialog = !showDialog">Réserver un entrainement</q-btn>
    </template>
  </HeaderC>
  <Calendar></Calendar>
  <DialogCreateTraining v-model:show="showDialog"></DialogCreateTraining>
</template>
<script setup lang="ts">
import HeaderC from '../shared/Header.vue'
import Calendar from './components/CalendarCourt.vue'
import DialogCreateTraining from './components/DialogCreateTraining.vue'
import { useI18n } from 'vue-i18n'
import { useUserStore } from 'src/stores/user'
import { computed, ref } from 'vue'

const { t } = useI18n()
const userStore = useUserStore()

const hasRole = computed(() => userStore.isAuthenticated)

const showDialog = ref(false)
</script>
<script lang="ts">
export default {
  components: { HeaderC },
  name: 'IndexComponentCourt'
}
</script>