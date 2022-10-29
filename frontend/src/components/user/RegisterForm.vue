<template>
  <div class="register flex justify-center items-center min-h-screen">
    <q-card class="p-8">
      <q-form
        @submit="onSubmit"
        @reset="onReset"
        class="q-gutter-md"
      >
        <div class="flex gap-4">
          <q-input
            filled
            v-model="lastName"
            :label="t('input.lastname')"
            lazy-rules
            :rules="[ val => val && val.length > 0 || t('field.required')]"
          />

          <q-input
            filled
            v-model="firstName"
            :label="t('input.firstname')"
            lazy-rules
            :rules="[ val => val && val.length > 0 || t('field.required')]"
          />
        </div>
        <q-input
          filled
          v-model="password"
          type="password"
          :label="t('auth.input.password')"
          lazy-rules
          :rules="[ val => val && val.length > 0 || t('field.required')]"
        />

        <q-input
          filled
          v-model="passwordConfirm"
          type="password"
          :label="t('auth.input.confirm.password')"
          lazy-rules
          :rules="[ 
            val => val && val.length > 0 || t('field.required'),
            val => val && val === password || t('auth.input.enter.same.password')
          ]"
        />
        <div class="flex justify-center mt-8">
          <q-btn :label="t('btn.register')" type="submit" color="primary"/>
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

const lastName = ref('')
const firstName = ref('')
const password = ref('')
const passwordConfirm = ref('')

const onSubmit = async () => {
  await authStore.register({
    lastname: lastName.value,
    firstname: firstName.value,
    password: password.value
  })
}

const onReset = () => {
  firstName.value = ''
  lastName.value = ''
  password.value = ''
  passwordConfirm.value = ''
}
</script>

