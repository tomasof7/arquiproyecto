import { api } from "./client";

export const enviarCorreoAsync = (para: string, asunto: string, cuerpo: string) =>
  api.post<string>("/email/send-async", null, {
    params: { para, asunto, cuerpo }
  });
