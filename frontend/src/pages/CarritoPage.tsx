import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { verCarrito, limpiarCarrito } from "../api/carrito";
import { useAuth } from "../context/AuthContext";
import { Link } from "react-router-dom";

export default function CarritoPage() {
  const { customer } = useAuth();
  const queryClient = useQueryClient();

  const { data, isLoading, error } = useQuery({
    queryKey: ["carrito"],
    queryFn: () => verCarrito(customer!.correo),
    enabled: !!customer
  });

  const limpiar = useMutation({
    mutationFn: () => limpiarCarrito(customer!.correo),
    onSuccess: () => queryClient.invalidateQueries(["carrito"])
  });

  if (!customer) return <p>Regístrate para ver el carrito.</p>;
  if (isLoading) return <p>Cargando carrito...</p>;
  if (error) return <p>Error al cargar carrito</p>;

  return (
    <div className="space-y-3">
      <h2 className="text-2xl font-semibold">Tu carrito</h2>
      {data && data.paquetes.length > 0 ? (
        <>
          <ul className="divide-y rounded border bg-white">
            {data.paquetes.map(p => (
              <li key={p.id} className="p-3">
                <div className="flex justify-between">
                  <div>
                    <p className="font-medium">{p.nombre}</p>
                    <p className="text-sm text-slate-600">{p.descripcion}</p>
                  </div>
                  <p className="font-semibold">${p.precioTotal}</p>
                </div>
              </li>
            ))}
          </ul>
          <div className="flex items-center justify-between">
            <p className="font-bold">Total: ${data.total}</p>
            <div className="flex gap-2">
              <button
                className="btn-secondary"
                onClick={() => limpiar.mutate()}
                disabled={limpiar.isLoading}
              >
                Vaciar
              </button>
              <Link className="btn-primary" to="/checkout">
                Ir a pagar
              </Link>
            </div>
          </div>
        </>
      ) : (
        <p>Carrito vacío.</p>
      )}
    </div>
  );
}
