<template>
  <router-view />
</template>

<script setup lang="ts">
import { onMounted } from 'vue';
import axios, { api } from './boot/axios';

  onMounted(async () => {
    await api.get('/welcome')
  })
</script>
