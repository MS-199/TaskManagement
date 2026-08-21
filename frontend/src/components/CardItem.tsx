import type { Card } from '../types';
import { getDueDateBadge } from '../utils/dueDate';

type CardItemProps = {
  card: Card;
};

function CardItem({ card }: CardItemProps) {
  const badge = getDueDateBadge(card.dueDate);

  return (
    <div className="card-item">
      <div className="card-title">{card.title}</div>
      {card.labels.length > 0 && (
        <div className="card-labels">
          {card.labels.map((label) => (
            <span key={label.id} className="card-label" style={{ backgroundColor: label.color }}>
              {label.name}
            </span>
          ))}
        </div>
      )}
      {card.dueDate && (
        <div className="card-due-date">
          {badge} {card.dueDate}
        </div>
      )}
    </div>
  );
}

export default CardItem;
