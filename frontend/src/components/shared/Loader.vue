<template>
  <q-inner-loading :showing="showComp" :class="{'hide-back': hideBack}">
    <q-spinner size="50px" color="primary" />
  </q-inner-loading>
</template>
<script lang="ts">
export default{
  name: 'LoaderComponent',
}
</script>
<script setup lang="ts">
import { computed } from 'vue';

const props = withDefaults(
  defineProps<{
    show: boolean,
    hideBack?: boolean
  }>(),
  {
    hideBack: false
  }
)
const emit = defineEmits<{
  (name: 'update:show', value: boolean):boolean
}>()

const showComp = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value as boolean)
})
</script>
<style lang="scss">
.q-inner-loading {
  &.hide-back {
    background: white;
  }
}
</style>