import { useContext } from "react";
import { ProductContext, type ProductContextType } from "./ProductContext";

export function useProductContext(): ProductContextType {
  const context = useContext(ProductContext);
  if (!context) {
    throw new Error("useProductContext must be used inside ProductProvider");
  }
  return context;
}
