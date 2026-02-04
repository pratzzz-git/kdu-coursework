import { Injectable, NotFoundException } from '@nestjs/common';
import { Todo } from './todo.interface';
import { CreateTodoDto } from './create-todo.dto';

@Injectable()
export class TodosService {
  private todos: Todo[] = [];
  private idCounter = 1;

  getAll(): Todo[] {
    return this.todos;
  }

  getById(id: number): Todo {
    const todo = this.todos.find(t => t.id === id);
    if (!todo) {
      throw new NotFoundException('Todo not found');
    }
    return todo;
  }

  create(createTodoDto: CreateTodoDto): Todo {
    const now = new Date();

    const newTodo: Todo = {
      id: this.idCounter++,
      title: createTodoDto.title,
      description: createTodoDto.description,
      completed: false,
      createdAt: now,
      updatedAt: now,
    };

    this.todos.push(newTodo);
    return newTodo;
  }

  delete(id: number): void {
    const index = this.todos.findIndex(t => t.id === id);
    if (index === -1) {
      throw new NotFoundException('Todo not found');
    }
    this.todos.splice(index, 1);
  }
}
