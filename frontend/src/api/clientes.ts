import { api } from "./client";
import { Customer } from "./types";

export const registrarCliente = (payload: {
  nombre: string;
  correo: string;
  telefono: string;
}) => api.post<Customer>("/clientes/registro", payload).then(r => r.data);

export const obtenerCliente = (correo: string) =>
  api.get<Customer>(`/clientes/${encodeURIComponent(correo)}`).then(r => r.data);

export const eliminarCliente = (correo: string) =>
  api.delete<void>(`/clientes/${encodeURIComponent(correo)}`);
