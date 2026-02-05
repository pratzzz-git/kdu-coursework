import type { Book } from "../types/books";
import { booksData } from "../data/books";

export async function fetchBooksFromData(): Promise<Book[]> {
  try {
    return [...booksData];
  } catch {
    throw new Error("Failed to fetch books data");
  }
}

export function searchBooks(books: Book[], query: string): Book[] {
  if (!query || query.trim().length === 0) {
    return [...books];
  }

  const q = query.toLowerCase();

  return books.filter(
    (book) =>
      book.title.toLowerCase().includes(q) ||
      book.author.toLowerCase().includes(q)
  );
}

export function getAvailableBooks(books: Book[]): Book[] {
  if (!Array.isArray(books) || books.length === 0) {
    return [];
  }

  return books.filter((book) => book.available === true);
}

export function getBooksByYearRange(
  books: Book[],
  startYear: number,
  endYear: number
): Book[] {
  if (!Array.isArray(books) || books.length === 0) {
    return [];
  }

  if (startYear > endYear) {
    [startYear, endYear] = [endYear, startYear];
  }

  return books.filter(
    (book) => book.year >= startYear && book.year <= endYear
  );
}

export function getBooksByMinRating(books: Book[], minRating: number): Book[] {
  if (!Array.isArray(books) || books.length === 0) {
    return [];
  }

  return books.filter((book) => book.rating >= minRating);
}
