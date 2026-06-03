import FormularioEnviarMensaje from "./FormularioEnviarMensaje";
import Mensaje from "./Mensaje";

export default function ListaMensajes() {
    return <>
    <FormularioEnviarMensaje/>
    <ul class="list-group mb-4">
        <li class="list-group-item ms-2"><Mensaje /></li>
        <li class="list-group-item ms-2"><Mensaje /></li>
        <li class="list-group-item ms-2"><Mensaje /></li>
        <li class="list-group-item ms-2"><Mensaje /></li>
        <li class="list-group-item ms-2"><Mensaje /></li>
    </ul>
    </>;
}