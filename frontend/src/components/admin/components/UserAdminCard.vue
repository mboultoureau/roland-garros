<template>
  <div class="border-2 border-gray-200 p-4 rounded-md my-4 items-center grid grid-cols-4">
    <div>{{ user.username }}</div>
    <div>{{ user.email }}</div>
    <div class="flex flex-col gap-1 justify-center">
      <div v-for="role in user.roles" :key="role" class=" justify-center">
        <q-badge class="h-fit w-fit">{{ t(`admin.role.${role.name}`) }}</q-badge>
      </div>
    </div>
   <div class="flex gap-1 m-auto">
      <q-btn flat icon="edit" @click="handleEditUser" />
      <q-btn flat icon="delete" color="red" @click="handleDeleteUser" />
    </div>
  </div>
  <DialogEditUser v-model:show="showDialog" :user="user"></DialogEditUser>
</template>
<script setup lang="ts">
import { User } from 'src/models/user'
import { useI18n } from 'vue-i18n'
import DialogEditUser from './DialogEditUser.vue'
import { ref } from 'vue'
import { useUserStore } from 'src/stores/user'

const { t } = useI18n()
const userStore = useUserStore()

const showDialog = ref(false)

const props = defineProps<{
  user: User
}>()

const handleEditUser = () => showDialog.value = true

const handleDeleteUser = async () => {
  await userStore.delete(props.user.id)
  await userStore.fetch()
}
</script>