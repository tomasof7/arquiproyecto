export interface Destination {
  id: number;
  nombre: string;
  ciudad: string;
  region: string;
  precioBase: number;
}

export interface TravelPackage {
  id: number;
  codigo: string;
  nombre: string;
  descripcion: string;
  destinos: Destination[];
  precioTotal: number;
}

export interface ShoppingCart {
  id: number;
  usuarioTvp: string;
  paquetes: TravelPackage[];
  total: number;
}

export type PaymentStatus = "EN_PROCESO" | "APROBADO" | "RECHAZADO";

export interface Purchase {
  id: number;
  usuarioTvp: string;
  celularContacto: string;
  estado: PaymentStatus;
  total: number;
  medioPago: string;
  fechaCreacion: string;
  fechaActualizacion: string;
  paquetes: TravelPackage[];
}

export interface Customer {
  id: number;
  nombre: string;
  correo: string;
  telefono: string;
}

export interface EmailMessage {
  para: string;
  asunto: string;
  cuerpo: string;
}
