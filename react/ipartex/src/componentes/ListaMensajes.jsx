import { useContext, useEffect, useState } from "react";
import FormularioEnviarMensaje from "./FormularioEnviarMensaje";
import Mensaje from "./Mensaje";
import { URL_MENSAJES } from "../constantes/rutas";
import { UsuarioContext } from "../contextos/UsuarioContext";

export default function ListaMensajes() {
    const [mensajes, setMensajes] = useState([]);

    const { usuario } = useContext(UsuarioContext);

    useEffect(() => {
        fetch(URL_MENSAJES).then(
            respuesta => respuesta.json().then(
                mensajes => setMensajes(mensajes)
            )
        )
    }, []);

    return <>
        {usuario ? <FormularioEnviarMensaje /> : null}
        <ul className="list-group mb-4">
            {mensajes.map(mensaje => <li key={mensaje.id} className="list-group-item ms-2"><Mensaje mensaje={mensaje} /></li>)}
        </ul>
    </>;
}