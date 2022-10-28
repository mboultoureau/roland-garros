<template>
  <NewPlayerForm></NewPlayerForm>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import NewPlayerForm from '../../../components/admin/player/NewPlayerForm.vue';
export default defineComponent({
    components: { NewPlayerForm }
})
</script>
