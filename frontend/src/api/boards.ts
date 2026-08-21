import { apiGet } from './client';
import type { Board, TaskList } from '../types';

export function getBoards(): Promise<Board[]> {
  return apiGet<Board[]>('/api/boards');
}

export function getLists(boardId: string): Promise<TaskList[]> {
  return apiGet<TaskList[]>(`/api/boards/${boardId}/lists`);
}
