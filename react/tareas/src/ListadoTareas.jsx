import styles from './ListadoTareas.module.css';
import Tarea from './Tarea';

export default function ListadoTareas({tareas}) {
    return <ul className={styles.listadoTareas}>
        {
            tareas && tareas.length 
                ? tareas.map(tarea => <li key={tarea.id}><Tarea tarea={tarea}/></li> )
                : <li>No hay tareas</li>
        }
    </ul>;
}
