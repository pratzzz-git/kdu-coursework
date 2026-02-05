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
  const [error, setError] = useState<string | null>(null);

  const [searchText, setSearchText] = useState<string>("");
  const [genre, setGenre] = useState<string>("");
  const [rating, setRating] = useState<number>(0);

  useEffect(() => {
    async function loadData() {
      try {
        const data = await fetchBooksFromData();
        setBooks(data);
      } catch {
        setError("Failed to load books");
      }
    }

    loadData();
  }, []);

  const filteredBooks = useMemo(() => {
    let result = [...books];

    result = searchBooks(result, searchText);

    if (genre) {
      result = result.filter((book) => book.genre === genre);
    }

    result = getBooksByMinRating(result, rating);

    return result;
  }, [books, searchText, genre, rating]);

  const totalBooks = books.length;
  const availableBooks = books.filter((b) => b.available).length;
  const unavailableBooks = totalBooks - availableBooks;

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="app">
      <h1>Book Library</h1>

      <Filters
        searchQuery={searchText}
        selectedGenre={genre}
        minRating={rating}
        onSearchChange={setSearchText}
        onGenreChange={setGenre}
        onRatingChange={setRating}
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
