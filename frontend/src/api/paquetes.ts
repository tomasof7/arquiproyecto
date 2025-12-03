import { api } from "./client";
import { TravelPackage } from "./types";

export const listarPaquetes = () =>
  api.get<TravelPackage[]>("/paquetes").then(r => r.data);

export const obtenerPaquete = (id: string | number) =>
  api.get<TravelPackage>(`/paquetes/${id}`).then(r => r.data);
