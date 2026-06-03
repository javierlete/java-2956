import FormularioEnviarMensaje from "./FormularioEnviarMensaje";
import Mensaje from "./Mensaje";

export default function ListaMensajes({ mensajes }) {
    return <>
        <FormularioEnviarMensaje />
        <ul className="list-group mb-4">
            {mensajes.map(mensaje => <li className="list-group-item ms-2"><Mensaje mensaje={mensaje} /></li>)}
        </ul>
    </>;
}