import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <section className="grid gap-6 items-center md:grid-cols-2">
      <div className="space-y-4">
        <h1 className="text-4xl font-bold">Explora Colombia con TVP</h1>
        <p className="text-slate-600">
          Paquetes curados con destinos como Cartagena, Eje Cafetero, San Andrés y más.
          Compra segura y notificaciones por correo.
        </p>
        <div className="flex gap-3">
          <Link className="btn-primary" to="/paquetes">
            Ver paquetes
          </Link>
          <Link className="btn-secondary" to="/registro">
            Registrarse
          </Link>
        </div>
      </div>
      <div className="rounded-xl bg-gradient-to-br from-blue-500 to-cyan-400 p-10 text-white shadow-lg">
        <p className="text-xl font-semibold">Pagos con Págame + RabbitMQ</p>
        <p className="mt-3 text-sm opacity-90">
          Sigue tu compra en tiempo real y recibe alertas por correo/SMS simulado.
        </p>
      </div>
    </section>
  );
}
