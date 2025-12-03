import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { obtenerCompra } from "../api/compras";

export default function EstadoCompraPage() {
  const { id } = useParams<{ id: string }>();
  const { data, isLoading, error } = useQuery({
    queryKey: ["compra", id],
    queryFn: () => obtenerCompra(id!),
    enabled: !!id
  });

  if (isLoading) return <p>Cargando compra...</p>;
  if (error || !data) return <p>Error al cargar compra</p>;

  return (
    <div className="space-y-2">
      <h2 className="text-2xl font-semibold">Compra #{data.id}</h2>
      <p>
        Estado: <span className="font-semibold">{data.estado}</span>
      </p>
      <p>Total: ${data.total}</p>
      <h3 className="mt-3 font-semibold">Paquetes</h3>
      <ul className="ml-5 list-disc">
        {data.paquetes.map(p => (
          <li key={p.id}>{p.nombre}</li>
        ))}
      </ul>
    </div>
  );
}
