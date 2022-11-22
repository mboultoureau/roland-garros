<template>
  <div class="h-screen">
    <HeaderC :title="t('court.index.title')" class="mb-8">
      <template v-slot:btn v-if="hasRole">
        <q-btn color="primary">Réserver un entrainement</q-btn>
      </template>
    </HeaderC>
    <Calendar></Calendar>
  </div>
</template>
<script setup lang="ts">
import HeaderC from '../shared/Header.vue'
import Calendar from './components/CalendarCourt.vue'
import { useI18n } from 'vue-i18n'
import { useUserStore } from 'src/stores/user'
import { computed } from 'vue'

const { t } = useI18n()
const userStore = useUserStore()

const hasRole = computed(() => userStore.isAuthenticated)

</script>
<script lang="ts">
export default {
  components: { HeaderC },
  name: 'IndexComponentCourt'
}
</script>