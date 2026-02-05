import { useEffect, useMemo, useState } from "react";
import "./App.css";

import type { Book } from "./types/books";
import {
  fetchBooksFromData,
  searchBooks,
  getBooksByMinRating,
   getAvailableBooks,
  getBooksByYearRange,
} from "./services/bookService";

import BookList from "./components/BookList/BookList";
import Filters from "./components/Filters/Filters";
import Stats from "./components/Stats/Stats";

function App() {
  const [books, setBooks] = useState<Book[]>([]);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  const [searchQuery, setSearchQuery] = useState<string>("");
  const [selectedGenre, setSelectedGenre] = useState<string>("");
  const [minRating, setMinRating] = useState<number>(0);

  useEffect(() => {
    async function loadBooks() {
      try {
        const data = await fetchBooksFromData();
        setBooks(data);
      } catch {
        setErrorMessage("Failed to load books");
      }
    }

    loadBooks();
  }, []);

  const filteredBooks = useMemo(() => {
    let result = [...books];

    result = searchBooks(result, searchQuery);

    if (selectedGenre) {
      result = result.filter((book) => book.genre === selectedGenre);
    }

    result = getBooksByMinRating(result, minRating);

    return result;
  }, [books, searchQuery, selectedGenre, minRating]);

  const totalBooks = books.length;
  const availableBooks = books.filter((b) => b.available).length;
  const unavailableBooks = totalBooks - availableBooks;

  if (errorMessage) {
    return <div>{errorMessage}</div>;
  }

  return (
    <div className="app">
      <h1>Book Library</h1>

      <Filters
        searchQuery={searchQuery}
        selectedGenre={selectedGenre}
        minRating={minRating}
        onSearchChange={setSearchQuery}
        onGenreChange={setSelectedGenre}
        onRatingChange={setMinRating}
      />

      <Stats
        total={totalBooks}
        available={availableBooks}
        unavailable={unavailableBooks}
      />

      <BookList books={filteredBooks} />
    </div>
  );
}
// just for testing section 
fetchBooksFromData().then((allBooks) => {
  console.log("All books:", allBooks);

  console.log("Search 'code':", searchBooks(allBooks, "code"));

  console.log("Available books:", getAvailableBooks(allBooks));

  console.log(
    "Books from 1990 to 2010:",
    getBooksByYearRange(allBooks, 1990, 2010)
  );
});

export default App;
