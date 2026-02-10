import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useProductContext } from "../context/useProductContext";

function HomePage() {
  const { products, loading, error, fetchAllProducts, searchLoading } =
    useProductContext();
  const navigate = useNavigate();

  useEffect(() => {
    fetchAllProducts();
  }, []);

  if (loading) return <p>Loading products...</p>;
  if (error) return <p>{error}</p>;

  if (!loading && !searchLoading && products.length === 0) {
    return <p>No results found</p>;
  }

  return (
    <div style={{ padding: "16px" }}>
      <h2>All Products</h2>

      <div style={{ display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: "16px" }}>
        {products.map((product) => (
          <div
            key={product.id}
            style={{ border: "1px solid #ccc", padding: "8px", cursor: "pointer" }}
            onClick={() => navigate(`/product/${product.id}`)}
          >
            <img src={product.thumbnail} alt={product.title} width="100%" />
            <h4>{product.title}</h4>
            <p>₹ {product.price}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default HomePage;
