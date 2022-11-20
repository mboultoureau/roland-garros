<template>
  <Header :title="t('tournament.edit.match.title')">
    <template v-slot:btn>
      <q-btn color="primary" :loading="showLoaderBtn" @click="submit">Enregistrer</q-btn>
    </template>
  </Header>
  <div class="formulaire mt-8">
    <div class="team flex gap-8">
      <div class="team-a flex-1">
        <q-select 
          :multiple="double" 
          counter
          :max-values="double ? 2:1"
          filled 
          :label="t('tournament.edit.match.select.team')" 
          v-model="teamA" 
          :options="optionsPlayer" 
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
          filled 
          counter
          :max-values="double ? 2:1"
          :label="t('tournament.edit.match.select.team')" 
          v-model="teamB"
          :options="optionsPlayer" 
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
    </div>
  </div>
</template>
<script setup lang="ts">
import { useMatchCard } from 'src/components/match/functions/match';
import Header from 'src/components/shared/Header.vue';
import InputDate from 'src/components/shared/InputDate.vue';
import { Team } from 'src/models/match';
import { useCourtStore } from 'src/stores/court';
import { useMatchStore } from 'src/stores/match';
import { usePlayerStore } from 'src/stores/player';
import { ref, computed } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const matchStore = useMatchStore()
const courtStore = useCourtStore()
const playerStore = usePlayerStore()

const props = defineProps<{
  double: boolean,
  teamA: number | number[] | null,
  teamB: number | number[] | null,
  date: string | null,
  time: string | null,
  court: number | null,
  showLoaderBtn: boolean
}>()

const emit = defineEmits<{
  (name: 'update:teamA', value: number | number[]): number|number[]
  (name: 'update:teamB', value: number|number[]): number|number[]
  (name: 'update:date', value: string): string
  (name: 'update:court', value: number): number
  (name: 'submit'): void
}>()

const { reduceNamePlayer } = useMatchCard()

const optionsCourt = computed(() => courtStore.listCourt)
const optionsPlayer = computed(() => playerStore.listPlayer)

const teamA = computed({
  get: () => props.teamA,
  set: (value) => emit('update:teamA', value)
})
const teamB = computed({
  get: () => props.teamB,
  set: (value) => emit('update:teamB', value)
}) 
const court = computed({
  get: () => props.court,
  set: (value) => emit('update:court', value)
})
const date = ref()
const time = ref()

const loadingBtn = ref(false)

const submit = async () => {
  loadingBtn.value = true
  emit('submit')
  loadingBtn.value = false
}
</script>