import type { Card, TaskList } from '../types';
import CardItem from './CardItem';

type ListColumnProps = {
  list: TaskList;
  cards: Card[];
};

function ListColumn({ list, cards }: ListColumnProps) {
  return (
    <div className="list-column">
      <div className="list-title">{list.title}</div>
      <div className="list-cards">
        {cards.map((card) => (
          <CardItem key={card.id} card={card} />
        ))}
      </div>
    </div>
  );
}

export default ListColumn;
