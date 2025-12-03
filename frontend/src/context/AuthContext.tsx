import { createContext, useContext, useEffect, useState } from "react";
import { Customer } from "../api/types";
import { obtenerCliente } from "../api/clientes";

interface AuthContextValue {
  customer: Customer | null;
  setCustomer: (c: Customer | null) => void;
  loadCustomer: (correo: string) => Promise<void>;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [customer, setCustomer] = useState<Customer | null>(null);

  useEffect(() => {
    const saved = localStorage.getItem("customerEmail");
    if (saved) {
      loadCustomer(saved).catch(() => {});
    }
  }, []);

  const loadCustomer = async (correo: string) => {
    const c = await obtenerCliente(correo);
    setCustomer(c);
    localStorage.setItem("customerEmail", c.correo);
  };

  return (
    <AuthContext.Provider value={{ customer, setCustomer, loadCustomer }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) {
    throw new Error("useAuth debe usarse dentro de AuthProvider");
  }
  return ctx;
};
