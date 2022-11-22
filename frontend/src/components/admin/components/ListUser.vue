<template>
  <div v-for="user in listUser" :key="user.username">
    <UserAdminCard :user="user"></UserAdminCard>
  </div>
</template>
<script setup lang="ts">
import { useUserStore } from 'src/stores/user';
import { computed, onMounted } from 'vue';
import UserAdminCard from './UserAdminCard.vue';

const userStore = useUserStore()

const listUser = computed(() => userStore.listUser)

onMounted(async () => await userStore.fetch())
</script>