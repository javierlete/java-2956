import { useState } from "react";

import styles from './Tarea.module.css';
import { URL_TAREAS } from "./constantes";

export default function Tarea({ tarea }) {
    const [estaTarea, setEstaTarea] = useState(tarea);

    async function cambioEstado(e) {
        console.log(e.target);

        const respuesta = await fetch(URL_TAREAS + estaTarea.id, {
            method: 'PATCH',
            body: JSON.stringify({ terminada: !estaTarea.terminada}),
            headers: {
                'Content-type': 'application/json'
            }
        });

        console.log(respuesta);

        const tareaRecibida = await respuesta.json();

        console.log(tareaRecibida);

        setEstaTarea({ ...estaTarea, terminada: !estaTarea.terminada });
    }

    return <label className={styles.tarea}>
        <input type="checkbox" checked={estaTarea.terminada} onChange={cambioEstado} />
        {estaTarea.texto}
    </label>;
}
