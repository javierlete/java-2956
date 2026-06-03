import Fecha from "./Fecha";

export default function Mensaje() {
    return <div className="d-flex flex-column">
        <div className="d-flex justify-content-between align-items-baseline">
            <div className="fw-bold">USUARIO</div>
            <span className="badge text-bg-primary rounded-pill"><Fecha iso="2026-01-02T03:04" /></span>
        </div>
        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Iste sunt dolor repudiandae quibusdam, nihil non ratione, sint temporibus veniam maiores magnam deserunt ipsam voluptates itaque ullam magni rerum quod autem.</p>
        <div>
            <span className="numero-me-gusta">5</span> <a href="#"><i className="text-danger bi bi-heart"></i></a>
            
            <span className="numero-respuestas ms-2">2</span> <a href="#"><i className="bi bi-chat"></i></a>
        </div>
    </div>;
}