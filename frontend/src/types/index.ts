export type Label = {
  id: string;
  name: string;
  color: string;
};

export type Card = {
  id: string;
  listId: string;
  title: string;
  description: string | null;
  dueDate: string | null;
  order: number;
  createdAt: string;
  updatedAt: string;
  labels: Label[];
};

export type Board = {
  id: string;
  title: string;
  createdAt: string;
};

export type TaskList = {
  id: string;
  boardId: string;
  title: string;
  order: number;
  createdAt: string;
};
