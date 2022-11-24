<template>
  <q-input filled v-model="modelValueComp" lazy-rules :label="label" :rules="[ val => val && val.length > 0 || t('field.required')]">
    <template v-slot:append>
      <q-icon name="event" class="cursor-pointer">
        <q-popup-proxy>
          <q-date
          v-if="!isTimeInput"
          v-model="modelValueComp"
          :locale="localeDatePicker"
          mask="YYYY-MM-DD"></q-date>
          <q-time v-if="isTimeInput" v-model="modelValueComp" format24h mask="HH:mm" :minute-options="[0]" color="primary" />
        </q-popup-proxy>
      </q-icon>
    </template>
  </q-input>
</template>
<script setup lang="ts">
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const props = withDefaults(
  defineProps<{
    modelValue: string | null,
    label: string,
    isTimeInput?: boolean 
  }>(),
  {
    isTimeInput: false
  }
) 

const emit = defineEmits<{
  (name:'update:modelValue', value: string): string
}>()

const modelValueComp = computed({
  get: () => props.modelValue,
  set: (value) => emit('update:modelValue', value)
})

const localeDatePicker = ref({
  days: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
  daysShort: ['Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam', 'Dim'],
  months: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
  monthsShort: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin', 'Juil', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'],
})
</script>