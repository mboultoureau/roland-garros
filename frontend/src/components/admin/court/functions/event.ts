import { today, parseDate, parseTimestamp } from '@quasar/quasar-ui-qcalendar';
import { Event } from 'src/models/event';

export function useEvent() {
  const CURRENT_DAY = new Date();

  const getCurrentDay = (day: number) => {
    const newDay = new Date(CURRENT_DAY);
    newDay.setDate(day);
    const tm = parseDate(newDay);
    return tm.date;
  };

  const getEventsByDay = (events: Event[], timestamp): Event[] => {
    console.log(timestamp);
    return events.filter((evt: Event) => evt.date === timestamp);
  };

  return {
    getCurrentDay,
    getEventsByDay,
  };
}
