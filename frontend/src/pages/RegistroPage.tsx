import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registrarCliente } from "../api/clientes";
import { useAuth } from "../context/AuthContext";

export default function RegistroPage() {
  const [form, setForm] = useState({ nombre: "", correo: "", telefono: "" });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const { setCustomer } = useAuth();
  const navigate = useNavigate();

  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const c = await registrarCliente(form);
      setCustomer(c);
      localStorage.setItem("customerEmail", c.correo);
      navigate("/paquetes");
    } catch (err: any) {
      setError(err.response?.data?.message || "No se pudo registrar");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md">
      <h2 className="mb-4 text-2xl font-semibold">Registrarse</h2>
      <form onSubmit={submit} className="space-y-3">
        <input
          className="input"
          placeholder="Nombre"
          required
          value={form.nombre}
          onChange={e => setForm({ ...form, nombre: e.target.value })}
        />
        <input
          className="input"
          type="email"
          placeholder="Correo"
          required
          value={form.correo}
          onChange={e => setForm({ ...form, correo: e.target.value })}
        />
        <input
          className="input"
          placeholder="Teléfono"
          required
          value={form.telefono}
          onChange={e => setForm({ ...form, telefono: e.target.value })}
        />
        {error && <p className="text-sm text-red-600">{error}</p>}
        <button className="btn-primary w-full" disabled={loading}>
          {loading ? "Registrando..." : "Crear cuenta"}
        </button>
      </form>
    </div>
  );
}
