<template>
  <HeaderC :title="t('admin.page.title')">
    <template v-slot:default>
      <div class="flex justify-around mt-8">
        <q-btn color="primary" @click="handleAdminPlayer">{{ t('admin.index.btn.players') }}</q-btn>
        <q-btn color="primary" @click="handleAdminMatch">{{ t('admin.index.btn.tournaments') }}</q-btn>
      </div>
    </template>
  </HeaderC>
  <ListUser></ListUser>
</template>
<script lang="ts">
import HeaderC from '../shared/Header.vue'
import ListUser from './components/ListUser.vue'
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router'
export default {
  components: { HeaderC, ListUser },
  name: 'IndexPageAdmin'
}
</script>
<script setup lang="ts">
const { t } = useI18n()
const router = useRouter()

const handleAdminMatch = () => router.push({ name: 'admin-tournaments'})
const handleAdminPlayer = () => router.push({ name: 'admin-players'})
</script>