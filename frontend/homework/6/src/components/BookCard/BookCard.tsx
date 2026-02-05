import type { Book } from "../../types/books";
import "./BookCard.scss";

interface BookCardProps {
  book: Book;
}

function BookCard({ book }: BookCardProps) {
  return (
    <div className={`book-card ${book.available ? "available" : "unavailable"}`}>
      <h3 className="book-title">{book.title}</h3>
      <p className="book-author">{book.author}</p>
      <p className="book-genre">Genre: {book.genre}</p>
      <p className="book-rating">Rating: {book.rating}</p>
      <p className="book-status">
        Status: {book.available ? "Available" : "Unavailable"}
      </p>
    </div>
  );
}

export default BookCard;
