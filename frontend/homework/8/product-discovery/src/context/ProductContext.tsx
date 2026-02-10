import { createContext, useState, type ReactNode } from "react";

export type Product = {
  id: number;
  title: string;
  description: string;
  price: number;
  discountPercentage: number;
  rating: number;
  stock: number;
  brand: string;
  category: string;
  thumbnail: string;
  images: string[];
};

export type ProductContextType = {
  products: Product[];
  selectedProduct: Product | null;
  loading: boolean;
  searchLoading: boolean;
  error: string | null;
  searchQuery: string;
  fetchAllProducts: () => Promise<void>;
  fetchProductById: (id: string) => Promise<void>;
  searchProducts: (query: string) => Promise<void>;
  setSearchQuery: (query: string) => void;
  clearSearch: () => void;
};

// eslint-disable-next-line react-refresh/only-export-components
export const ProductContext = createContext<ProductContextType | undefined>(
  undefined
);

export function ProductProvider({ children }: { children: ReactNode }) {
  const [products, setProducts] = useState<Product[]>([]);
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const [searchLoading, setSearchLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);
  const [searchQuery, setSearchQueryState] = useState<string>("");

  const fetchAllProducts = async () => {
    try {
      setLoading(true);
      setError(null);
      const res = await fetch("https://dummyjson.com/products");
      const data = await res.json();
      setProducts(data.products);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    } catch (err) {
      setError("Failed to fetch products");
    } finally {
      setLoading(false);
    }
  };

  const fetchProductById = async (id: string) => {
    try {
      setLoading(true);
      setError(null);
      const res = await fetch(`https://dummyjson.com/products/${id}`);
      const data = await res.json();
      setSelectedProduct(data);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    } catch (err) {
      setError("Failed to fetch product");
    } finally {
      setLoading(false);
    }
  };

  const searchProducts = async (query: string) => {
    try {
      setSearchLoading(true);
      setError(null);
      const res = await fetch(
        `https://dummyjson.com/products/search?q=${query}`
      );
      const data = await res.json();
      setProducts(data.products);
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    } catch (err) {
      setError("Failed to search products");
    } finally {
      setSearchLoading(false);
    }
  };

  const setSearchQuery = (query: string) => {
    setSearchQueryState(query);
  };

  const clearSearch = () => {
    setSearchQueryState("");
    fetchAllProducts();
  };

  return (
    <ProductContext.Provider
      value={{
        products,
        selectedProduct,
        loading,
        searchLoading,
        error,
        searchQuery,
        fetchAllProducts,
        fetchProductById,
        searchProducts,
        setSearchQuery,
        clearSearch,
      }}
    >
      {children}
    </ProductContext.Provider>
  );
}
