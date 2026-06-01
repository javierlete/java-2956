import { useState } from 'react';
import styles from './TareaNueva.module.css';

import { URL_TAREAS } from './constantes';
import axios from 'axios';

const TAREA_VACIA = { texto: '', terminada: false };

export function TareaNueva({ tareas, setTareas }) {
    const [tarea, setTarea] = useState(TAREA_VACIA);

    async function agregarTarea(e) {
        e.preventDefault();

        console.log('Agregar Tarea', tarea);

        const respuesta = await axios.post(URL_TAREAS, tarea);

        console.log(respuesta);

        setTareas([respuesta.data, ...tareas]);

        setTarea(TAREA_VACIA);
    }

    return <form onSubmit={agregarTarea} className={styles.tareaNueva}>
        <input placeholder="El texto de la tarea" onChange={e => setTarea({ ...tarea, texto: e.target.value })} value={tarea.texto} />
    </form>;
}
