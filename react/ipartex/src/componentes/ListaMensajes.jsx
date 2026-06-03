import FormularioEnviarMensaje from "./FormularioEnviarMensaje";
import Mensaje from "./Mensaje";

export default function ListaMensajes() {
    return <>
        <FormularioEnviarMensaje />
        <ul className="list-group mb-4">
            <li className="list-group-item ms-2"><Mensaje mensaje={{
                "id": 1,
                "texto": "Prueba inicial",
                "momento": "2026-06-03T15:14:57.747",
                "usuario": "Javier",
                "rellenado": false,
                "numeroMeGusta": 1,
                "numeroRespuestas": 0
            }} /></li>
            <li className="list-group-item ms-2"><Mensaje mensaje={{
                "id": 2,
                "texto": "Claro, como eres el que ha hecho la red",
                "momento": "2026-06-03T15:14:57.747",
                "usuario": "Pepe",
                "rellenado": false,
                "numeroMeGusta": 1,
                "numeroRespuestas": 0
            }} /></li>
        </ul>
    </>;
}