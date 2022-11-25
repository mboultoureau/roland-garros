<template>
  <q-dialog v-model="showComputed">
    <q-card>
      <q-card-section>
        <div class="text-h6">Ajouter un score</div>
      </q-card-section>

      <q-card-section class="flex gap-4">
        <q-input class="w-full" filled v-model="round" label="Entrez le numéro du SET"></q-input>
        <div class="flex gap-4 w-full">
          <q-input class="flex-1" filled v-model="scoreTeamA" label="Score équipe A"></q-input>
          <q-input class="flex-1" filled v-model="scoreTeamB" label="Score équipe B"></q-input>
        </div>
      </q-card-section>

      <q-card-actions align="right" class="text-primary my-4">
        <q-btn color="primary" :loading="loaderBtn" :label="t('btn.confirm')" @click="handleCreateTournament"/>
        <q-btn flat :label="t('btn.cancel')" v-close-popup />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>
<script setup lang="ts">
import { Match } from 'src/models/match';
import { useMatchStore } from 'src/stores/match';
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';
import ScoreMatch from './ScoreMatch.vue';

const { t } = useI18n()
const matchStore = useMatchStore()

const props = defineProps<{
  show: boolean,
  match: Match
}>()

const emit = defineEmits<{
  (name: 'update:show', value: boolean):boolean
}>()

const round = ref()
const scoreTeamA = ref()
const scoreTeamB = ref()

const showComputed = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value) 
})

const loaderBtn = ref(false)

const handleCloseDialog = () => emit('update:show', false)

const handleCreateTournament = async () => {
  loaderBtn.value = true
  const matchCopy = props.match
  matchCopy.scores?.push({
    round: round.value,
    scoreTeamA: scoreTeamA.value,
    scoreTeamB: scoreTeamB.value
  })
  await matchStore.edit(matchCopy)
  handleCloseDialog()
  round.value = null
  scoreTeamA.value = null
  scoreTeamB.value = null
  loaderBtn.value = false
}

</script>