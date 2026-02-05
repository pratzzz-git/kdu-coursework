
export const RATING = {
  MIN: 0,
  MAX: 5,
} as const;

export const UI_LIMITS = {
  MAX_VISIBLE_BOOKS: 50,
} as const;

export const GENRES = [
  "Fiction",
  "Non-Fiction",
  "Science",
  "History",
  "Biography",
  "Fantasy",
] as const;

export const TEXT = {
  SEARCH_PLACEHOLDER: "Search by title or author",
  NO_RESULTS: "No books found",
  STATS_TITLE: "Library Statistics",
} as const;
