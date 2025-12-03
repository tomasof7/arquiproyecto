import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Layout: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { customer } = useAuth();
  return (
    <div className="min-h-screen bg-slate-50 text-slate-900">
      <header className="border-b bg-white">
        <div className="mx-auto flex max-w-6xl items-center justify-between px-4 py-3">
          <Link to="/" className="font-semibold text-lg">
            TVP Turismo
          </Link>
          <nav className="flex gap-4 text-sm">
            <Link to="/paquetes">Ver paquetes</Link>
            <Link to="/carrito">Carrito</Link>
            {customer ? (
              <span className="text-slate-600">Hola, {customer.nombre}</span>
            ) : (
              <Link to="/registro">Registrarse</Link>
            )}
          </nav>
        </div>
      </header>
      <main className="mx-auto max-w-6xl px-4 py-8">{children}</main>
    </div>
  );
};

export default Layout;
