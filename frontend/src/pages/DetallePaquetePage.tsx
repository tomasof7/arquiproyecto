import { useParams } from "react-router-dom";
import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import { obtenerPaquete } from "../api/paquetes";
import { agregarAlCarrito } from "../api/carrito";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";

export default function DetallePaquetePage() {
  const { id } = useParams<{ id: string }>();
  const { customer } = useAuth();
  const [msg, setMsg] = useState("");
  const queryClient = useQueryClient();

  const { data, isLoading, error } = useQuery({
    queryKey: ["paquete", id],
    queryFn: () => obtenerPaquete(id!),
    enabled: !!id
  });

  const addMutation = useMutation({
    mutationFn: (paqueteId: number) => agregarAlCarrito(customer!.correo, paqueteId),
    onSuccess: () => {
      queryClient.invalidateQueries(["carrito"]);
      setMsg("Agregado al carrito");
    },
    onError: () => setMsg("No se pudo agregar (¿sesión o registro faltante?)")
  });

  if (isLoading) return <p>Cargando...</p>;
  if (error || !data) return <p>Error al cargar</p>;

  return (
    <div className="space-y-3">
      <h2 className="text-2xl font-semibold">{data.nombre}</h2>
      <p>{data.descripcion}</p>
      <p className="font-semibold">Destinos:</p>
      <ul className="ml-5 list-disc text-sm text-slate-700">
        {data.destinos.map(d => (
          <li key={d.id}>
            {d.nombre} — {d.ciudad} ({d.region}) ${d.precioBase}
          </li>
        ))}
      </ul>
      <p className="text-xl font-bold">Total ${data.precioTotal}</p>
      <button
        className="btn-primary"
        disabled={!customer || addMutation.isLoading}
        onClick={() => addMutation.mutate(data.id)}
      >
        {!customer
          ? "Regístrate para comprar"
          : addMutation.isLoading
          ? "Agregando..."
          : "Agregar al carrito"}
      </button>
      {msg && <p className="text-sm text-slate-600">{msg}</p>}
    </div>
  );
}
