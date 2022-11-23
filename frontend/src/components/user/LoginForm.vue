<template>
  <div class="relative h-screen overflow-hidden">
    <div class="h-10">
      <svg class="absolute -top-40" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="var(--orange)" fill-opacity="1" d="M0,192L48,208C96,224,192,256,288,250.7C384,245,480,203,576,186.7C672,171,768,181,864,192C960,203,1056,213,1152,213.3C1248,213,1344,203,1392,197.3L1440,192L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path></svg>
    </div>
    <div class="p-8 h-full flex flex-col items-center justify-center gap-16">
      <div class="font-extrabold text-4xl text-gray-700">
        {{ t('auth.title') }}
      </div>
      <q-form
        class="q-gutter-md z-50"
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
          <q-btn :label="t('btn.connection')" type="submit" color="primary" @click="onSubmit"></q-btn>
          <q-btn :label="t('btn.back')" type="reset" color="primary" flat class="q-ml-sm" @click="onReset"></q-btn>
        </div>
      </q-form>
    </div>
    <svg class="absolute -bottom-10" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="var(--orange)" fill-opacity="1" d="M0,224L48,213.3C96,203,192,181,288,176C384,171,480,181,576,197.3C672,213,768,235,864,224C960,213,1056,171,1152,149.3C1248,128,1344,128,1392,128L1440,128L1440,320L1392,320C1344,320,1248,320,1152,320C1056,320,960,320,864,320C768,320,672,320,576,320C480,320,384,320,288,320C192,320,96,320,48,320L0,320Z"></path></svg>
  </div>
</template>

<script setup lang="ts">
import { useAuthStore } from 'src/stores/auth';
import { useUserStore } from 'src/stores/user';
import { ref } from 'vue'
import { useI18n } from 'vue-i18n';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
const router = useRouter()
const authStore = useAuthStore()
const userStore = useUserStore()
const { t } = useI18n()

const username = ref('')
const password = ref('')

const onSubmit = async () => {
  const user = await authStore.login({
    username: username.value,
    password: password.value
  })
  if(user?.success) {
    userStore.setUser({
      username: user.username,
      roles: user.roles,
    })
  }
  if(route.query.redirect && user?.success) {
    goNext(route.query.redirect as string)
  } else if(user?.success) {
    goBack()
  }
}

const goNext = (path: string) => {
  if(path) router.push({ path:  path})
}

const goBack = () => router.push({ path: '/'})

const onReset = () => {
  username.value = ''
  password.value = ''
  router.back()
}
</script>
