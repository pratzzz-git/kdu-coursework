import {
  fetchBooksFromData,
  searchBooks,
  getAvailableBooks,
  getBooksByYearRange,
} from "../services/bookService";

async function runTests() {
  const allBooks = await fetchBooksFromData();

  console.log("All books:", allBooks);

  const searchResult = searchBooks(allBooks, "code");
  console.log("Search 'code':", searchResult);

  const availableBooks = getAvailableBooks(allBooks);
  console.log("Available books:", availableBooks);

  const booksInRange = getBooksByYearRange(allBooks, 1990, 2010);
  console.log("Books from 1990 to 2010:", booksInRange);
}

runTests().catch((err) => {
  console.error("Test runner failed:", err);
});
