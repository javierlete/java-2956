const FORMATO = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit"
};

export default function Fecha({ iso = '2025-01-02T03:04' }) {
    return new Date(iso).toLocaleString("es-ES", FORMATO);
}