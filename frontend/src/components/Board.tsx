import type { Card, TaskList } from '../types';
import ListColumn from './ListColumn';

type BoardProps = {
  boardTitle: string;
  lists: TaskList[];
  cards: Card[];
};

function Board({ boardTitle, lists, cards }: BoardProps) {
  return (
    <div className="board">
      <h1 className="board-title">{boardTitle}</h1>
      <div className="list-row">
        {lists.map((list) => (
          <ListColumn key={list.id} list={list} cards={cards.filter((card) => card.listId === list.id)} />
        ))}
      </div>
    </div>
  );
}

export default Board;
