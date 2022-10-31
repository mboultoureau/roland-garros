<template>

  <QCalendarDay
    v-model="selectDate"

    class="p-8"
    locale="fr"
    view="week"
    date-type="rounded"

    :weekdays="[1,2,3,4,5,6,0]"
    :interval-start="7"
    :interval-count="16"

    hour24-format
    bordered
    hoverable
  >
    <template #day-body="{ scope: { timestamp } }">
      <template
        v-for="event in getEvents(timestamp.date)"
        :key="event.id"
      >
        <div
          v-if="event.time !== undefined"
          class="my-event"
          :class="badgeClasses(event, 'body')"
        >
          <span class="title q-calendar__ellipsis">
            {{ event.title }}
            <q-tooltip>{{ event.details }}</q-tooltip>
          </span>
        </div>
      </template>
    </template>
  </QCalendarDay>
</template>
<script lang="ts">
export default {
  name: 'CalendarComposant'
}
</script>
<script setup lang="ts">
import {
  QCalendarDay,
  addToDate,
  isBetweenDates,
  today,
  parsed,
  parseDate,
  parseTime
} from '@quasar/quasar-ui-qcalendar'
import { useEvent } from 'src/components/admin/court/functions/event';
import { Event } from 'src/models/event';

const selectDate = today()

const { getCurrentDay, getEventsByDay } = useEvent()

const events = [
  {
    id: 4,
    title: 'Lunch',
    details: 'Company is paying!',
    date: getCurrentDay(2),
    time: '11:30',
    duration: 90,
    bgcolor: 'teal',
    icon: 'fas fa-hamburger'
  },
] as Event[]

const badgeClasses = (event: Event, type: string) => {
  const isHeader = type === 'header'
  return {
    [ `text-white bg-${ event.bgcolor }` ]: true,
    'full-width': !isHeader && (!event.side || event.side === 'full'),
    'left-side': !isHeader && event.side === 'left',
    'right-side': !isHeader && event.side === 'right',
    'rounded-border': true
  }
}

const getEvents = (dt) => {
  const eventsByDay = getEventsByDay(events, dt)

  // get all events for the specified date
  if (events.length === 1) {
    events[0].side = 'full'
  }
  else if (events.length === 2) {
    // this example does no more than 2 events per day
    // check if the two events overlap and if so, select
    // left or right side alignment to prevent overlap
    const startTime = addToDate(parsed(events[0].date), { minute: parseTime(events[0].time) })
    const endTime = addToDate(startTime, { minute: events[ 0 ].duration })
    const startTime2 = addToDate(parsed(events[1].date), { minute: parseTime(events[1].time) })
    const endTime2 = addToDate(startTime2, { minute: events[ 1 ].duration })
    if (isBetweenDates(startTime2, startTime, endTime, true) || isBetweenDates(endTime2, startTime, endTime, true)) {
      events[0].side = 'left'
      events[1].side = 'right'
    }
    else {
      events[0].side = 'full'
      events[1].side = 'full'
    }
  }

  return events
}

</script>
