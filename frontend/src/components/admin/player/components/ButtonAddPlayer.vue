<template>
  <q-btn class="sticky bottom-16 float-right right-16" color="primary" rounded icon="add" size="lg" @click="goNewPlayer"></q-btn>
</template>
<script lang="ts" setup>
import { useRouter } from 'vue-router';

const router = useRouter()

const goNewPlayer = () => router.push({ name: 'new-player' })
</script>
