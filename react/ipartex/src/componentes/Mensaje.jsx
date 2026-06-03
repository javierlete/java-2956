import Fecha from "./Fecha";

export default function Mensaje() {
    return <div class="d-flex flex-column">
        <div class="d-flex justify-content-between align-items-baseline">
            <div class="fw-bold">USUARIO</div>
            <span class="badge text-bg-primary rounded-pill"><Fecha iso="2026-01-02T03:04" /></span>
        </div>
        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Iste sunt dolor repudiandae quibusdam, nihil non ratione, sint temporibus veniam maiores magnam deserunt ipsam voluptates itaque ullam magni rerum quod autem.</p>
        <div>
            <span class="numero-me-gusta">5</span> <a href="#"><i class="text-danger bi bi-heart"></i></a>
            
            <span class="numero-respuestas ms-2">2</span> <a href="#"><i class="bi bi-chat"></i></a>
        </div>
    </div>;
}