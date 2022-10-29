<template>
  <q-card class="m-4">
    <q-skeleton height="200px" square />

    <q-item>
      <q-item-section>
        <q-item-label>
          <q-skeleton type="text" />
        </q-item-label>
        <q-item-label caption>
          <q-skeleton type="text" />
        </q-item-label>
      </q-item-section>
    </q-item>

    <q-card-actions align="center">
      <q-skeleton type="QBtn" />
      <q-skeleton class="ml-1" type="QBtn" />
    </q-card-actions>
  </q-card>
</template>
