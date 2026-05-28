import { useState } from 'react';
import styles from './TareaNueva.module.css';

import { URL_TAREAS } from './constantes';

export function TareaNueva({tareas, setTareas}) {
    const [texto, setTexto] = useState('');

    async function agregarTarea(e) {
        e.preventDefault();

        console.log('Agregar Tarea', texto);

        const respuesta = await fetch(URL_TAREAS, {
            method: 'POST',
            body: JSON.stringify({texto}),
            headers: {
                'Content-type': 'application/json'
            }
        });

        setTareas([texto, ...tareas]);

        setTexto('');
    }
    
    return <form onSubmit={agregarTarea} className={styles.tareaNueva}>
        <input placeholder="El texto de la tarea" onChange={e => setTexto(e.target.value)} value={texto} />
    </form>;
}
