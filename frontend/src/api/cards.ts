import { apiGet } from './client';
import type { Card } from '../types';

export function searchCards(keyword: string): Promise<Card[]> {
  const query = keyword.trim() ? `?keyword=${encodeURIComponent(keyword.trim())}` : '';
  return apiGet<Card[]>(`/api/cards${query}`);
}
