export type DueDateStatus = 'overdue' | 'soon' | 'ok';

const SOON_THRESHOLD_DAYS = 2;

export function getDueDateStatus(dueDate: string | null): DueDateStatus | null {
  if (!dueDate) return null;

  const today = new Date();
  today.setHours(0, 0, 0, 0);
  const due = new Date(dueDate);
  due.setHours(0, 0, 0, 0);

  const diffDays = Math.floor((due.getTime() - today.getTime()) / (1000 * 60 * 60 * 24));

  if (diffDays < 0) return 'overdue';
  if (diffDays <= SOON_THRESHOLD_DAYS) return 'soon';
  return 'ok';
}

export function getDueDateBadge(dueDate: string | null): string {
  const status = getDueDateStatus(dueDate);
  if (status === 'overdue') return '🔴';
  if (status === 'soon') return '🟡';
  if (status === 'ok') return '🟢';
  return '';
}
