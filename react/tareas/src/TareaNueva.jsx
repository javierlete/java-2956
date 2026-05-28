import { useState } from 'react';
import styles from './TareaNueva.module.css';

export function TareaNueva({tareas, setTareas}) {
    const [texto, setTexto] = useState('');

    function agregarTarea(e) {
        e.preventDefault();

        console.log('Agregar Tarea', texto);

        setTareas([texto, ...tareas]);

        setTexto('');
    }
    
    return <form onSubmit={agregarTarea} className={styles.tareaNueva}>
        <input placeholder="El texto de la tarea" onChange={e => setTexto(e.target.value)} value={texto} />
    </form>;
}
