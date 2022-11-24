<template>
  <Header :title="t('admin.player.create.title')">
    <template v-slot:btn>
      <q-btn color="primary" @click="handleImportCSV">{{ t('admin.player.new.btn.import') }}</q-btn>
    </template>
  </Header>
  <PlayerForm class="mt-8" v-model:player="formCopy" @submit="submit" @cancel="cancel"></PlayerForm>
  <DialogCSV v-model:show="showDialog" :loading-btn="loadingBtn"></DialogCSV>
</template>
<script lang="ts" setup>
import PlayerForm from './components/PlayerForm.vue';
import Header from 'src/components/shared/Header.vue';
import DialogCSV from './components/DialogCSV.vue';
import { ref } from 'vue'
import { usePlayerStore } from 'src/stores/player';
import { useRouter } from 'vue-router';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()
const playerStore = usePlayerStore()
const router = useRouter()

const showDialog = ref(false)
const loadingBtn = ref(false)

const formCopy = ref({
  firstname: '',
  lastname: '',
  birthDate: '',
  birthPlace: '',
  hand: 'RIGHT',
  height: 0,
  weight: 0,
  earlyCareer: '',
  coach: '',
  nationality: null,
  picture: '',
  isPlayer: true,
  isCoach: false
})

const submit = async () => {
  await playerStore.store(formCopy.value)
  router.push({ name: 'admin-players' })
}

const cancel = () => router.push({ name: 'admin-players' })

const handleImportCSV = () => showDialog.value = true
</script>
