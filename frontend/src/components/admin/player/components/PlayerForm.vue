<template>
  <q-form
    greedy
    @submit="onSubmit"
    @reset="onReset"
    class="w-full"
  >
    <div class="container">
      <div class="lastname">
        <q-input filled v-model="formComputed.lastname" lazy-rules :label="t('input.lastname')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="firstname">
        <q-input filled v-model="formComputed.firstname" lazy-rules :label="t('input.firstname')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="birthday">
        <InputDate v-model="formComputed.birthDate" :label="t('input.birthday')"></InputDate>
      </div>
      <div class="nationality">
        <q-select :options="optionsNationality" option-label="name" option-value="id" emit-value map-options clearable filled v-model="formComputed.nationality" :label="t('input.nationality')" :rules="[ val => val || t('field.required')]"></q-select>
      </div>
      <div class="birthplace">
        <q-select :options="optionsNationality" option-label="name" option-value="name" emit-value map-options clearable filled v-model="formComputed.birthPlace" lazy-rules autocapitalize :label="t('input.birthplace')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-select>
      </div>
      <div class="height">
        <q-input filled v-model="formComputed.height" lazy-rules suffix="cm" :label="t('input.height')" :rules="[ val => val && val > 0 || t('field.required')]"></q-input>
      </div>
      <div class="weight">
        <q-input filled v-model="formComputed.weight" lazy-rules suffix="kg" :label="t('input.weight')" :rules="[ val => val && val > 0 || t('field.required')]"></q-input>
      </div>
      <div class="ranking">
        <q-select filled v-model="formComputed.ranking" lazy-rules :options="optionsRanking" :label="t('input.ranking')" :rules="[ val => val && val > 0 || t('field.required')]"></q-select>
      </div>
      <div class="hand">
        <q-radio class="right" v-model="formComputed.hand" lazy-rules val="RIGHT" label="Droite" />
        <q-radio class="left" v-model="formComputed.hand" lazy-rules val="LEFT" label="Gauche" />
      </div>
      <div class="start-career">
        <InputDate v-model="formComputed.earlyCareer" :label="t('input.career.start')"></InputDate>
      </div>
      <div class="coach">
        <q-select filled v-model="formComputed.coach" :options="optionsCoach" :label="t('input.coach')" emit-value option-label="lastname" map-options>
          <template v-slot:no-option>
            <div class="p-4 text-gray-500">
              {{ t('field.empty', { object: 'coach'}) }}
            </div>
          </template>
        </q-select>
      </div>
      <div class="url">
        <q-input filled v-model="formComputed.picture" lazy-rules :label="t('input.url')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="gender">
        <q-select filled v-model="formComputed.gender" :options="optionsGender" map-options emit-value :label="t('select.gender')" lazy-rules :rules="[ val => val && val.length > 0 || t('field.required')]"></q-select>
      </div>
      <div class="isPlayer flex justify-around">
        <q-checkbox v-model="formComputed.isCoach" label="Coach" />
        <q-checkbox v-model="formComputed.isPlayer" label="Joueur" />
      </div>
    </div>
    <div class="flex justify-center">
      <q-btn :label="t('btn.save')" type="submit" color="primary"/>
      <q-btn :label="t('btn.back')" type="reset" color="primary" flat />
    </div>
  </q-form>
</template>

<script setup lang="ts">
import { Gender, Person } from 'src/models/person';
import { ref, computed, onMounted } from 'vue';
import { useI18n } from 'vue-i18n';
import InputDate from 'src/components/shared/InputDate.vue';
import { LocalStorage } from 'quasar';
import { usePlayerStore } from 'src/stores/player';
import { useCountryStore } from 'src/stores/country';

const { t } = useI18n()
const playerStore = usePlayerStore()
const countryStore = useCountryStore()

const emit = defineEmits<{
  (name: 'update:player', value: Person): Person
  (name: 'submit'): void
  (name: 'cancel', value: Person): Person
}>()

const props = defineProps<{
  player: Person
}>()

const formComputed = computed({
  get: () => props.player,
  set: (player: Person) => emit('update:player', player)
})

const formCopy = ref({
  firstname: '',
  lastname: '',
  birthDate: '',
  birthPlace: '',
  hand: 'RIGHT',
  height: 0,
  weight: 0,
  earlyCareer: '',
  coach: null,
  nationality: null,
  picture: '',
  isPlayer: true,
  isCoach: false,
  ranking: 0
})

const optionsCoach = computed(() => playerStore.listCoach)

const optionsNationality = computed(() => countryStore.listNationality)
const optionsGender = [
  {
    label: t('admin.player.create.gender.MALE'),
    value: Gender.MEN
  },
  {
    label: t('admin.player.create.gender.FEMALE'),
    value: Gender.WOMAN
  },
  {
    label: t('admin.player.create.gender.AT_AT'),
    value: Gender.AT_AT
  },
  {
    label: t('admin.player.create.gender.OTHER'),
    value: Gender.OTHER
  }
]

const onSubmit = () => emit('submit')

const onReset = () => {
  formComputed.value = formCopy.value
  emit('cancel', formCopy.value)
}

let optionsRanking = [] as []
for(let i=1; i<100; i++) {
  optionsRanking.push(i)
}

onMounted(async () => await playerStore.fetchCoach())
</script>
<style lang="scss">
.container {
  height: 100%;
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
  grid-auto-rows: 1fr;
  gap: 2rem;
  grid-auto-flow: row;
  grid-template-areas:
    "lastname firstname"
    "birthday birthday"
    "birthplace birthplace"
    "nationality nationality"
    "height weight"
    "ranking hand"
    "start-career start-career"
    "coach coach"
    "url url"
    "gender isPlayer";
}

.lastname { grid-area: lastname; }

.url { grid-area: url; }

.gender { grid-area: gender; }

.isPlayer { grid-area: isPlayer; }

.firstname { grid-area: firstname; }

.birthday { grid-area: birthday; }

.birthplace { grid-area: birthplace; }

.nationality { grid-area: nationality; }

.height { grid-area: height; }

.weight { grid-area: weight; }

.ranking { grid-area: ranking; }

.hand {  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr;
  gap: 1rem;
  grid-auto-flow: row;
  grid-template-areas:
    "right left";
  grid-area: hand;
}

.right { grid-area: right; justify-self: center; }

.left { grid-area: left; justify-self: center;}

.start-career { grid-area: start-career; }

.coach { grid-area: coach; }


</style>
