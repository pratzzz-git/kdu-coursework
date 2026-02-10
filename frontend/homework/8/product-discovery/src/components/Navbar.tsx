import { type ChangeEvent, useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import { useProductContext } from "../context/useProductContext";

function Navbar() {
  const {
    searchQuery,
    setSearchQuery,
    searchProducts,
    clearSearch,
    searchLoading,
  } = useProductContext();

  const [inputValue, setInputValue] = useState(searchQuery);

  // Debounce: wait 500ms after user stops typing
  useEffect(() => {
    const timer = setTimeout(() => {
      if (inputValue.trim() === "") {
        clearSearch();
      } else {
        setSearchQuery(inputValue);
        searchProducts(inputValue);
      }
    }, 500);

    return () => clearTimeout(timer);
  }, [inputValue]);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
  };

  const handleClear = () => {
    setInputValue("");
    clearSearch();
  };

  return (
    <div style={{ display: "flex", gap: "16px", padding: "12px", borderBottom: "1px solid #ccc" }}>
      <NavLink
        to="/"
        style={({ isActive }) => ({
          fontWeight: "bold",
          color: isActive ? "blue" : "black",
          textDecoration: "none",
        })}
      >
        Home
      </NavLink>

      <input
        type="text"
        placeholder="Search products..."
        value={inputValue}
        onChange={handleChange}
        style={{ padding: "6px", width: "250px" }}
      />

      {inputValue && (
        <button onClick={handleClear}>
          X
        </button>
      )}

      {searchLoading && <span>Searching...</span>}
    </div>
  );
}

export default Navbar;
