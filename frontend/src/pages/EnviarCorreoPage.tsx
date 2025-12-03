import { useState } from "react";
import { enviarCorreoAsync } from "../api/email";

export default function EnviarCorreoPage() {
  const [form, setForm] = useState({ para: "", asunto: "", cuerpo: "" });
  const [msg, setMsg] = useState("");

  const send = async (e: React.FormEvent) => {
    e.preventDefault();
    setMsg("");
    try {
      await enviarCorreoAsync(form.para, form.asunto, form.cuerpo);
      setMsg("Publicado en cola de correo");
    } catch {
      setMsg("Error al enviar");
    }
  };

  return (
    <div className="max-w-md">
      <h2 className="mb-3 text-2xl font-semibold">Enviar correo (async)</h2>
      <form onSubmit={send} className="space-y-2">
        <input
          className="input"
          placeholder="Para"
          value={form.para}
          onChange={e => setForm({ ...form, para: e.target.value })}
        />
        <input
          className="input"
          placeholder="Asunto"
          value={form.asunto}
          onChange={e => setForm({ ...form, asunto: e.target.value })}
        />
        <textarea
          className="input"
          placeholder="Cuerpo"
          value={form.cuerpo}
          onChange={e => setForm({ ...form, cuerpo: e.target.value })}
        />
        <button className="btn-primary w-full">Enviar</button>
        {msg && <p className="text-sm text-slate-600">{msg}</p>}
      </form>
    </div>
  );
}
