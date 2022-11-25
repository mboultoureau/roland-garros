<template>
  <HeaderC :title="t('court.index.title')" class="mb-8">
    <template v-slot:btn v-if="hasRole">
      <q-btn color="primary" @click="showDialog = !showDialog">{{ t('court.index.btn.reserve') }}</q-btn>
    </template>
    <template v-slot:default>
      <q-select filled class="mt-8" v-model="courtSelected" map-options option-label="name" :label="t('court.index.selected.court')" :options="listCourt"></q-select>
    </template>
  </HeaderC>
  <Calendar :trainings="courtSelected?.trainings"></Calendar>
  <DialogCreateTraining v-model:show="showDialog"></DialogCreateTraining>
</template>
<script setup lang="ts">
import HeaderC from '../shared/Header.vue'
import Calendar from './components/CalendarCourt.vue'
import DialogCreateTraining from './components/DialogCreateTraining.vue'
import { useI18n } from 'vue-i18n'
import { useUserStore } from 'src/stores/user'
import { computed, onMounted, ref } from 'vue'
import { useCourtStore } from 'src/stores/court'
import { firstDayOfWeek } from './functions/calendar'
import { format } from 'date-fns'
import { watch } from 'vue'

const { t } = useI18n()
const userStore = useUserStore()
const courtStore = useCourtStore()

const hasRole = computed(() => userStore.isAuthenticated)

const showDialog = ref(false)
const courtSelected = ref()

watch(
  showDialog,
  async () => await courtStore.fetch()
)

const listCourt = computed(() => courtStore.listCourt)

onMounted(async () => await courtStore.fetch(format(firstDayOfWeek, 'yyyy-MM-dd')))
</script>
<script lang="ts">
export default {
  components: { HeaderC },
  name: 'IndexComponentCourt'
}
</script>