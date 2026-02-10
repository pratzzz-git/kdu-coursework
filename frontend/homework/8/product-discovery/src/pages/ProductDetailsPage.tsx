import { useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useProductContext } from "../context/useProductContext";

function ProductDetailsPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { selectedProduct, loading, error, fetchProductById } = useProductContext();

  useEffect(() => {
    if (id) fetchProductById(id);
  }, [id]);

  if (loading) return <p>Loading product...</p>;
  if (error) return <p>{error}</p>;
  if (!selectedProduct) return <p>No product found</p>;

  const discountedPrice =
    selectedProduct.price -
    (selectedProduct.price * selectedProduct.discountPercentage) / 100;

  return (
    <div style={{ padding: "16px" }}>
      <button onClick={() => navigate(-1)}>Back</button>

      <h2>{selectedProduct.title}</h2>
      <img src={selectedProduct.thumbnail} alt={selectedProduct.title} width="300" />

      <p>{selectedProduct.description}</p>

      <p>
        Price: ₹{selectedProduct.price} <br />
        Discount: {selectedProduct.discountPercentage}% <br />
        <strong>Discounted Price: ₹{discountedPrice.toFixed(2)}</strong>
      </p>

      <p>Rating: {selectedProduct.rating}</p>
      <p>Stock: {selectedProduct.stock}</p>
      <p>Brand: {selectedProduct.brand}</p>
      <p>Category: {selectedProduct.category}</p>

      <h4>Images</h4>
      <div style={{ display: "flex", gap: "8px", flexWrap: "wrap" }}>
        {selectedProduct.images.map((img, index) => (
          <img key={index} src={img} alt="" width="100" />
        ))}
      </div>
    </div>
  );
}

export default ProductDetailsPage;
