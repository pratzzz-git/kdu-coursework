import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage";
import ProductDetailsPage from "./pages/ProductDetailsPage";
import Navbar from "./components/Navbar";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/product/:id" element={<ProductDetailsPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
