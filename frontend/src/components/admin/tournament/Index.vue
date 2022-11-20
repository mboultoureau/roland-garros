<template>
  <div class="xl:mx-96 lg:mx-68 md:mx-48 sm:mx-16 mx-8 mt-8">
    <HeaderFilters />
    <ListTournament />
  </div>
</template>
<script lang="ts">
import HeaderFilters from './components/HeaderFilters.vue';
import ListTournament from './components/ListTournament.vue';

export default {
    name: 'IndexComponentTournament',
    components: { HeaderFilters, ListTournament }
}
</script>