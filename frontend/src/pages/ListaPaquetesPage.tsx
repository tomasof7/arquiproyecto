import { useQuery } from "@tanstack/react-query";
import { listarPaquetes } from "../api/paquetes";
import { Link } from "react-router-dom";

export default function ListaPaquetesPage() {
  const { data, isLoading, error } = useQuery({
    queryKey: ["paquetes"],
    queryFn: listarPaquetes
  });

  if (isLoading) return <p>Cargando paquetes...</p>;
  if (error) return <p>Error al cargar paquetes</p>;

  return (
    <div className="grid gap-4 md:grid-cols-3">
      {data?.map(pkg => (
        <div key={pkg.id} className="rounded-xl border bg-white p-4 shadow-sm">
          <h3 className="text-lg font-semibold">{pkg.nombre}</h3>
          <p className="text-sm text-slate-600">{pkg.descripcion}</p>
          <p className="mt-2 font-semibold">${pkg.precioTotal}</p>
          <Link className="btn-secondary mt-3 inline-block" to={`/paquetes/${pkg.id}`}>
            Ver detalle
          </Link>
        </div>
      ))}
    </div>
  );
}
