<template>
  <div class="lg:mx-80 md:mx-52 mx-8">
    <ScoreboardNavigation />
    <div class="flex justify-center gap-16">
      <div class="flex flex-col justify-center items-center">
        <!--<MatchCard v-for="(match, index) in nbMatch" :key="index" :class="{ 'mb-16': ((index+1) % 2 === 0 && (index+1) != nbMatch) }" />-->
        <MatchDoubleCard />
      </div>
      <div class="flex flex-col justify-around mobile-match-card">
        <MatchCard v-for="(match, index) in nbMatch2" :key="index" />
      </div>
    </div>

  </div>
</template>
<script lang="ts">
import MatchCard from './components/MatchCard.vue';
import MatchDoubleCard from './components/MatchDoubleCard.vue';
import ScoreboardNavigation from './components/ScoreboardNavigation.vue';

export default {
    name: 'IndexMatch',
    components: { ScoreboardNavigation, MatchCard, MatchDoubleCard }
}
</script>
<script setup lang="ts">

const nbMatch = 8
const nbMatch2 = 4
</script>
<style lang="scss" scoped>
.mobile-match-card  {
  display: block;
}
@media screen and (max-width: 1280px) {
  .mobile-match-card  {
    display: none;
  } 
}

</style>