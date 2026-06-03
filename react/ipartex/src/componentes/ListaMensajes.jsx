import FormularioEnviarMensaje from "./FormularioEnviarMensaje";
import Mensaje from "./Mensaje";

export default function ListaMensajes() {
    return <>
    <FormularioEnviarMensaje/>
    <ul className="list-group mb-4">
        <li className="list-group-item ms-2"><Mensaje /></li>
        <li className="list-group-item ms-2"><Mensaje /></li>
        <li className="list-group-item ms-2"><Mensaje /></li>
        <li className="list-group-item ms-2"><Mensaje /></li>
        <li className="list-group-item ms-2"><Mensaje /></li>
    </ul>
    </>;
}