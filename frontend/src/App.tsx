import { useEffect, useState } from 'react';
import type { Board as BoardType, Card, TaskList } from './types';
import { getBoards, getLists } from './api/boards';
import { searchCards } from './api/cards';
import SearchBar from './components/SearchBar';
import Board from './components/Board';
import './App.css';

function App() {
  const [board, setBoard] = useState<BoardType | null>(null);
  const [lists, setLists] = useState<TaskList[]>([]);
  const [cards, setCards] = useState<Card[]>([]);
  const [keyword, setKeyword] = useState('');
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function loadBoard() {
      try {
        const boards = await getBoards();
        const firstBoard = boards[0];
        if (!firstBoard) return;
        setBoard(firstBoard);
        setLists(await getLists(firstBoard.id));
      } catch {
        setError('ボード情報の取得に失敗しました');
      }
    }
    loadBoard();
  }, []);

  useEffect(() => {
    async function loadCards() {
      try {
        setCards(await searchCards(keyword));
        setError(null);
      } catch {
        setError('カード検索に失敗しました');
      }
    }
    loadCards();
  }, [keyword]);

  return (
    <div className="app">
      <SearchBar value={keyword} onChange={setKeyword} />
      {error && <p className="error">{error}</p>}
      {board && <Board boardTitle={board.title} lists={lists} cards={cards} />}
    </div>
  );
}

export default App;
