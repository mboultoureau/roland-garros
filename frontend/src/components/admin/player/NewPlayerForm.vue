<template>
  <q-form 
    greedy
    @submit="onSubmit"
    @reset="onReset"
    class="m-16"
  >
    <div class="container">
      <div class="lastname">
        <q-input filled v-model="form.lastname" lazy-rules :label="t('input.lastname')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="firstname">
        <q-input filled v-model="form.firstname" lazy-rules :label="t('input.firstname')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="birthday">
        <q-input filled v-model="form.birthday" lazy-rules :label="t('input.birthday')" :rules="[ val => val && val.length > 0 || t('field.required')]">
          <template v-slot:append>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy>
                <q-date 
                v-model="form.birthday" 
                :locale="localeDatePicker"
                mask="DD/MM/YYYY"></q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
      <div class="nationality">
        <q-input filled v-model="form.nationality" lazy-rules autocapitalize :label="t('input.nationality')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="birthplace">
        <q-input filled v-model="form.birthplace" lazy-rules autocapitalize :label="t('input.birthplace')" :rules="[ val => val && val.length > 0 || t('field.required')]"></q-input>
      </div>
      <div class="height">
        <q-input filled v-model="form.height" lazy-rules suffix="cm" :label="t('input.height')" :rules="[ val => val && val > 0 || t('field.required')]"></q-input>
      </div>
      <div class="weight">
        <q-input filled v-model="form.weight" lazy-rules suffix="kg" :label="t('input.weight')" :rules="[ val => val && val > 0 || t('field.required')]"></q-input>
      </div>
      <div class="ranking">
        <q-select filled v-model="form.ranking" lazy-rules :options="optionsRanking" :label="t('input.ranking')" :rules="[ val => val && val > 0 || t('field.required')]"></q-select>
      </div>
      <div class="hand">
        <q-radio class="right" v-model="form.hand" lazy-rules val="right" label="Droite" />
        <q-radio class="left" v-model="form.hand" lazy-rules val="left" label="Gauche" />
      </div>
      <div class="start-career">
        <q-input filled v-model="form['career-start']" lazy-rules :label="t('input.career.start')" :rules="[ val => val && val.length > 0 || t('field.required')]">
          <template v-slot:append>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy>
                <q-date 
                  v-model="form['career-start']"
                  mask="DD/MM/YYYY"
                  :locale="localeDatePicker"
                ></q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
      <div class="coach">
        <q-select filled v-model="form.coach" lazy-rules :options="[]" :label="t('input.coach')" :rules="[ val => val && val.length > 0|| t('field.required')]">
          <template v-slot:no-option>
            <div class="p-4 text-gray-500">
              {{ t('field.empty', { object: 'coach'}) }}
            </div>
          </template>
        </q-select>
      </div>
    </div>
    <div class="flex justify-center">
      <q-btn :label="t('btn.save')" type="submit" color="primary"/>
      <q-btn :label="t('btn.back')" type="reset" color="primary" flat />
    </div>
  </q-form>
</template>

<script setup lang="ts">
import { Player } from 'src/models/person';
import { ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()

const emit = defineEmits<{
  (name: 'submit', value: Player): Player
  (name: 'cancel'): void
}>()

const form = ref({
  firstname: '',
  lastname: '',
  birthday: '',
  birthplace: '',
  hand: 'right',
  height: 0,
  weight: 0,
  'career-start': '',
  coach: null as Player,
  nationality: '',
} as Player)


const formCopy = {...form.value}

const onSubmit = () => console.log(form.value); emit('submit', form.value)

const onReset = () => {
  form.value = formCopy
  emit('cancel')
}

let optionsRanking = [] as []
for(let i=0; i<100; i++) {
  optionsRanking.push(i)
}

const localeDatePicker = ref({
  days: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
  daysShort: ['Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam', 'Dim'],
  months: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août', 'Septembre', 'Octobre', 'Novembre', 'Décembre'],
  monthsShort: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin', 'Juil', 'Aoû', 'Sep', 'Oct', 'Nov', 'Déc'],
})

</script>
<style lang="scss">
.container {
  height: 100%;
  width: 100%;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;
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
    "coach coach";
}

.lastname { grid-area: lastname; }

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
