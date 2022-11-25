<template>
  <Header :title="t('tournament.edit.match.title')">
    <template v-slot:btn>
      <q-btn color="primary" :loading="showLoaderBtn" @click="submit">{{ t('btn.save') }}</q-btn>
    </template>
  </Header>
  <div class="formulaire mt-8">
    <div class="team flex gap-8">
      <div class="team-a flex-1">
        <q-select 
          :multiple="double" 
          max-values="2"
          filled 
          :label="t('tournament.edit.match.select.team')" 
          v-model="teamA" 
          :options="optionsPlayers" 
          emit-value 
          map-options 
          option-value="id" 
          :option-label="opt => Object(opt) === opt && 'firstname' in opt ? reduceNamePlayer(opt.firstname, opt.lastname) : ''"
        >
          <template v-slot:option="{ itemProps, opt, selected, toggleOption }">
            <q-item v-bind="itemProps">
              <q-item-section side>
                <q-checkbox :model-value="selected" @update:model-value="toggleOption(opt)"></q-checkbox>
              </q-item-section>
              <q-item-section>
                <q-item-label>{{ reduceNamePlayer(opt.firstname, opt.lastname) }}</q-item-label>
              </q-item-section>
            </q-item>
          </template>
      </q-select>
      </div>
      <div class="team-b flex-1">
        <q-select 
          :multiple="double" 
          max-values="2"
          filled 
          :label="t('tournament.edit.match.select.team')" 
          v-model="teamB"
          :options="optionsPlayers" 
          emit-value 
          map-options 
          option-value="id" 
          :option-label="opt => Object(opt) === opt && 'firstname' in opt ? reduceNamePlayer(opt.firstname, opt.lastname) : ''"
        >
          <template v-slot:option="{ itemProps, opt, selected, toggleOption }">
            <q-item v-bind="itemProps">
              <q-item-section side>
                <q-checkbox :model-value="selected" @update:model-value="toggleOption(opt)"></q-checkbox>
              </q-item-section>
              <q-item-section>
                <q-item-label>{{ reduceNamePlayer(opt.firstname, opt.lastname) }}</q-item-label>
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
    </div>
    <div class="date-court my-8">
      <div class="flex gap-8">
        <InputDate class="flex-1" v-model="date" :label="t('tournament.edit.match.select.date')"></InputDate>
        <InputDate class="flex-1" v-model="time" :is-time-input="true" :label="t('tournament.edit.match.select.time')"></InputDate>
      </div>
      <q-select 
        class="my-4"
        filled 
        :label="t('tournament.edit.match.select.court')" 
        v-model="court" 
        :options="optionsCourt" 
        option-value="id"
        option-label="name" 
        map-options 
        emit-value
      >
      </q-select>

      <q-input type="number" in filled v-model="duration" label="Nombre d'heure de réservation"></q-input>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useMatchCard } from 'src/components/match/functions/match';
import Header from 'src/components/shared/Header.vue';
import InputDate from 'src/components/shared/InputDate.vue';
import { Person } from 'src/models/person';
import { useCourtStore } from 'src/stores/court';
import { ref, computed } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const courtStore = useCourtStore()

const props = defineProps<{
  double: boolean,
  teamA: number | number[] | null,
  teamB: number | number[] | null,
  date: string | null,
  time: string | null,
  court: number | null,
  duration: number | null,
  showLoaderBtn: boolean,
  optionsPlayers: Person[] 
}>()

const emit = defineEmits<{
  (name: 'update:teamA', value: number | number[]): number|number[]
  (name: 'update:teamB', value: number|number[]): number|number[]
  (name: 'update:date', value: string): string
  (name: 'update:court', value: number): number
  (name: 'update:duration', value: number): number
  (name: 'update:time', value: number | string): number
  (name: 'submit'): void
}>()

const { reduceNamePlayer } = useMatchCard()

const optionsCourt = computed(() => courtStore.listCourt)

const teamA = computed({
  get: () => props.teamA,
  set: (value) => { 
    emit('update:teamA', value)
  }
})
const teamB = computed({
  get: () => props.teamB,
  set: (value) => emit('update:teamB', value)
}) 
const court = computed({
  get: () => props.court,
  set: (value) => emit('update:court', value)
})
const date = computed({
  get: () => props.date,
  set: (value) => emit('update:date', value)
})
const duration = computed({
  get: () => props.duration,
  set: (value) => emit('update:duration', value) 
})
const time = computed({
  get: () => props.time,
  set: (value) => emit('update:time', value)
})

const loadingBtn = ref(false)

const submit = async () => {
  loadingBtn.value = true
  emit('submit')
  loadingBtn.value = false
}
</script>