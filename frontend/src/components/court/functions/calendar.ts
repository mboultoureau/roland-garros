import { startOfWeek, addHours } from 'date-fns';
import { Training } from 'src/models/court';

export const current = new Date();
export const currentNbDay = current.getDate();
export const firstDayOfWeek = startOfWeek(current);

export function allEvents(trainings: Training[]) {
  const events = [];
  trainings.forEach((t) => {
    for (let i = 0; i < t.duration; i++) {
      events.push({
        date: new Date(t.startDate).getDate(),
        horaire: addHours(new Date(t.startDate), i).getHours(),
        player: t.player,
      });
    }
  });
  return events;
}
