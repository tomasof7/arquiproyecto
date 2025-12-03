import { useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { pagar } from "../api/compras";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function CheckoutPage() {
  const { customer } = useAuth();
  const [celular, setCelular] = useState(customer?.telefono || "");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const payMutation = useMutation({
    mutationFn: () => pagar(customer!.correo, celular),
    onSuccess: compra => navigate(`/compras/${compra.id}`),
    onError: (err: any) => setError(err.response?.data?.message || "No se pudo iniciar el pago")
  });

  if (!customer) return <p>Regístrate para pagar.</p>;

  return (
    <div className="max-w-md space-y-3">
      <h2 className="text-2xl font-semibold">Checkout</h2>
      <label className="block text-sm text-slate-600">Celular de contacto</label>
      <input className="input" value={celular} onChange={e => setCelular(e.target.value)} />
      {error && <p className="text-sm text-red-600">{error}</p>}
      <button
        className="btn-primary w-full"
        onClick={() => payMutation.mutate()}
        disabled={payMutation.isLoading}
      >
        {payMutation.isLoading ? "Procesando..." : "Pagar"}
      </button>
    </div>
  );
}
