<template>
  <div class="border-2 border-gray-200 p-4 rounded-md my-4 items-center  grid grid-cols-4">
    <div>{{ user.username }}</div>
    <div>{{ user.email }}</div>
    <div>
      <div v-for="role in user.roles" :key="role" class="flex gap-4 justify-center">
        <q-badge class="h-fit">{{ t(`admin.role.${role.name}`) }}</q-badge>
      </div>
    </div>
   <div class="flex gap-1 m-auto">
      <q-btn flat icon="edit" @click="handleEditUser" />
      <q-btn flat icon="delete" color="red" />
    </div>
  </div>
  <DialogEditUser v-model:show="showDialog" :user="user"></DialogEditUser>
</template>
<script setup lang="ts">
import { User } from 'src/models/user'
import { useI18n } from 'vue-i18n'
import DialogEditUser from './DialogEditUser.vue'
import { ref } from 'vue'

const { t } = useI18n()

const showDialog = ref(false)

const props = defineProps<{
  user: User
}>()

const handleEditUser = () => showDialog.value = true
</script>