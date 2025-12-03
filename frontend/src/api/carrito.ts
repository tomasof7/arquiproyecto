import { api } from "./client";
import { ShoppingCart } from "./types";

export const verCarrito = (correo: string) =>
  api.get<ShoppingCart>(`/carrito/${encodeURIComponent(correo)}`).then(r => r.data);

export const agregarAlCarrito = (correo: string, paqueteId: number) =>
  api
    .post<ShoppingCart>(
      `/carrito/${encodeURIComponent(correo)}/agregar?paqueteId=${paqueteId}`
    )
    .then(r => r.data);

export const limpiarCarrito = (correo: string) =>
  api.delete<void>(`/carrito/${encodeURIComponent(correo)}`);
