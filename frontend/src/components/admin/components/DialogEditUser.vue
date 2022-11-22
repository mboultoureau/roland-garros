<template>
  <q-dialog v-model="showComputed">
    <q-card>
        <q-card-section>
          <div class="text-h6">{{ t('tournament.dialog.create.title') }}</div>
        </q-card-section>

        <q-card-section class="flex gap-4">
          <q-input :label="t('auth.input.username')" class="flex-1 w-56" filled v-model="userEdit.username"></q-input>
          <q-select :label="t('tournament.dialog.select.type')" map-options option-label="name" emit-value option-value="id" class="flex-1 w-72" filled use-chips v-model="userEdit.roles" multiple :options="optionsRoles">
            <template v-slot:option="scope">
              <q-item v-bind="scope.itemProps">
                <q-item-label>{{ t(`admin.role.${scope.opt.name}`) }}</q-item-label>
              </q-item>
            </template>
            <template v-slot:selected-item="scope">
              <q-chip
                removable
                dense
                @remove="scope.removeAtIndex(scope.index)"
                :tabindex="scope.tabindex"
              >
                {{ t(`admin.role.${scope.opt.name}`) }}
              </q-chip>
            </template>
          </q-select>
        </q-card-section>

        <q-card-actions align="right" class="text-primary my-4">
          <q-btn color="primary" :loading="loaderBtn" :label="t('btn.confirm')"/>
          <q-btn flat :label="t('btn.cancel')" @click="handleCancel" />
        </q-card-actions>
      </q-card>
  </q-dialog>
</template>
<script setup lang="ts">
import { User } from 'src/models/user';
import { useUserStore } from 'src/stores/user';
import { computed, ref } from 'vue';
import { useI18n } from 'vue-i18n';

const { t } = useI18n()
const userStore = useUserStore()

const props = defineProps<{
  show: boolean,
  user: User
}>()

const userEdit = ref({
  username: props.user.username,
  roles: props.user.roles
})

const emit = defineEmits<{
  (name: 'update:show', value: boolean):boolean,
}>()

const showComputed = computed({
  get: () => props.show,
  set: (value) => emit('update:show', value) 
})

const optionsRoles = computed(() => userStore.listRole)

const loaderBtn = ref(false)

const handleCloseDialog = () => emit('update:show', false)

const handleEditUser = async () => {
  loaderBtn.value = true
  await userStore.edit(props.user.id, userEdit.value.username, userEdit.value.roles)
  handleCloseDialog()
  loaderBtn.value = false
}

const handleCancel = () => {
  userEdit.value = {
    username: props.user.username,
    roles: props.user.roles
  } 
  emit('update:show', false)
}

</script>