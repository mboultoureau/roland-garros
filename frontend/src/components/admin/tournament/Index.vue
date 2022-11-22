<template>
  <HeaderFilters />
  <ListTournament />
</template>
<script lang="ts">
import HeaderFilters from './components/HeaderFilters.vue';
import ListTournament from './components/ListTournament.vue';

export default {
    name: 'IndexComponentTournament',
    components: { HeaderFilters, ListTournament }
}
</script>