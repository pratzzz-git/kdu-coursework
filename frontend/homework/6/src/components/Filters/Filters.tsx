import "./Filters.scss";
import { GENRES, RATING, TEXT } from "../../constants/config";

interface FiltersProps {
  searchQuery: string;
  selectedGenre: string;
  minRating: number;
  onSearchChange: (value: string) => void;
  onGenreChange: (value: string) => void;
  onRatingChange: (value: number) => void;
}

function Filters({
  searchQuery,
  selectedGenre,
  minRating,
  onSearchChange,
  onGenreChange,
  onRatingChange,
}: FiltersProps) {
  return (
    <div className="filters">
      <input
        type="text"
        placeholder={TEXT.SEARCH_PLACEHOLDER}
        value={searchQuery}
        onChange={(e) => onSearchChange(e.target.value)}
      />

      <select
        value={selectedGenre}
        onChange={(e) => onGenreChange(e.target.value)}
      >
        <option value="">All Genres</option>
        {GENRES.map((genre) => (
          <option key={genre} value={genre}>
            {genre}
          </option>
        ))}
      </select>

      <input
        type="number"
        min={RATING.MIN}
        max={RATING.MAX}
        step="0.1"
        value={minRating}
        onChange={(e) => onRatingChange(Number(e.target.value))}
      />
    </div>
  );
}

export default Filters;
