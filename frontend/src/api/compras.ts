import { api } from "./client";
import { Purchase } from "./types";

export const pagar = (correo: string, celular: string) =>
  api
    .post<Purchase>(
      `/compras/${encodeURIComponent(correo)}/pagar?celular=${encodeURIComponent(
        celular
      )}`
    )
    .then(r => r.data);

export const obtenerCompra = (id: string | number) =>
  api.get<Purchase>(`/compras/${id}`).then(r => r.data);
