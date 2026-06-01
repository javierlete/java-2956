import { useState } from "react";

import styles from './Tarea.module.css';
import { URL_TAREAS } from "./constantes";
import axios from "axios";

export default function Tarea({ tarea }) {
    const [estaTarea, setEstaTarea] = useState(tarea);

    async function cambioEstado(e) {
        console.log(e.target);

        const respuesta = await axios.patch(URL_TAREAS + estaTarea.id, { terminada: !estaTarea.terminada });

        console.log(respuesta);

        const tareaRecibida = respuesta.data;

        console.log(tareaRecibida);

        setEstaTarea({ ...estaTarea, terminada: !estaTarea.terminada });
    }

    return <label className={styles.tarea}>
        <input type="checkbox" checked={estaTarea.terminada} onChange={cambioEstado} />
        {estaTarea.texto}
    </label>;
}
