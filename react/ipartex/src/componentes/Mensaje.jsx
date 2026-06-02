import Fecha from "./Fecha";

export default function Mensaje() {
    return <><div class="ms-2 me-auto">
        <div class="fw-bold">Subheading</div>
        Content for list item
    </div>
        <span class="badge text-bg-primary rounded-pill"><Fecha/></span>
    </>;
}