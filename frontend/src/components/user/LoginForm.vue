<template>
  <div class="login flex justify-center items-center min-h-screen">
    <q-card class="p-8">
      <q-form
        @submit="onSubmit"
        @reset="onReset"
        class="q-gutter-md"
      >
        <q-input
          filled
          v-model="username"
          :label="t('auth.input.username')"
          lazy-rules
          :rules="[ val => val && val.length > 0 || t('field.required')]"
        />

        <q-input
          filled
          v-model="password"
          type="password"
          :label="t('auth.input.password')"
          lazy-rules
          :rules="[ val => val && val.length > 0 || t('field.required')]"
        />

        <div class="flex justify-center mt-8">
          <q-btn :label="t('btn.connection')" type="submit" color="primary"/>
          <q-btn :label="t('btn.back')" type="reset" color="primary" flat class="q-ml-sm" />
        </div>
      </q-form>
    </q-card>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from 'src/stores/auth';
import { ref } from 'vue'
import { useI18n } from 'vue-i18n';

const authStore = useAuthStore()
const { t } = useI18n()

const username = ref('')
const password = ref('')

const onSubmit = async () => {
  await authStore.login({
    username: username.value,
    password: password.value
  })
}

const onReset = () => {
  username.value = ''
  password.value = ''
}
</script>
