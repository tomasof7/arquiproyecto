import { Navigate, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import RegistroPage from "./pages/RegistroPage";
import ListaPaquetesPage from "./pages/ListaPaquetesPage";
import DetallePaquetePage from "./pages/DetallePaquetePage";
import CarritoPage from "./pages/CarritoPage";
import CheckoutPage from "./pages/CheckoutPage";
import EstadoCompraPage from "./pages/EstadoCompraPage";
import EnviarCorreoPage from "./pages/EnviarCorreoPage";
import Layout from "./components/Layout";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Layout>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/registro" element={<RegistroPage />} />
        <Route path="/paquetes" element={<ListaPaquetesPage />} />
        <Route path="/paquetes/:id" element={<DetallePaquetePage />} />
        <Route
          path="/carrito"
          element={
            <ProtectedRoute>
              <CarritoPage />
            </ProtectedRoute>
          }
        />
        <Route
          path="/checkout"
          element={
            <ProtectedRoute>
              <CheckoutPage />
            </ProtectedRoute>
          }
        />
        <Route path="/compras/:id" element={<EstadoCompraPage />} />
        <Route path="/correo" element={<EnviarCorreoPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </Layout>
  );
}

export default App;
