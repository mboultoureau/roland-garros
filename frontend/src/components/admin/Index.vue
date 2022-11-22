<template>
  <HeaderC :title="t('admin.page.title')"></HeaderC>
  <ListUser></ListUser>
</template>
<script lang="ts">
import HeaderC from '../shared/Header.vue'
import ListUser from './components/ListUser.vue'
import { useI18n } from 'vue-i18n'
export default {
  components: { HeaderC, ListUser },
  name: 'IndexPageAdmin'
}
</script>
<script setup lang="ts">
const { t } = useI18n()
</script>