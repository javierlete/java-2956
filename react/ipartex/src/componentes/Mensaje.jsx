import Fecha from "./Fecha";

export default function Mensaje({ mensaje }) {
    return <div className="d-flex flex-column">
        <div className="d-flex justify-content-between align-items-baseline">
            <div className="fw-bold">{mensaje.usuario}</div>
            <span className="badge text-bg-primary rounded-pill"><Fecha iso={mensaje.momento} /></span>
        </div>
        <p>{mensaje.texto}</p>
        <div>
            <span className="numero-me-gusta">{mensaje.numeroMeGusta}</span> <a href="#"><i className={'text-danger bi bi-heart' + (mensaje.rellenado ? '-fill': '') }></i></a>

            <span className="numero-respuestas ms-2">{mensaje.numeroRespuestas}</span> <a href="#"><i className="bi bi-chat"></i></a>
        </div>
    </div>;
}