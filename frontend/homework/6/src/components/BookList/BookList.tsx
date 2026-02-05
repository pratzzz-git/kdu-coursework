import type { Book } from "../../types/books";
import BookCard from "../BookCard/BookCard";
import "./BookList.scss";

interface BookListProps {
  books: Book[];
}

function BookList({ books }: BookListProps) {
  if (books.length === 0) {
    return <p>No books to display</p>;
  }

  return (
    <div className="book-list">
      {books.map((book) => (
        <BookCard key={book.id} book={book} />
      ))}
    </div>
  );
}

export default BookList;
