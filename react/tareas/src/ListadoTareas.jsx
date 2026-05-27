export default function ListadoTareas({tareas}) {
    return <ul>
        {
            tareas && tareas.length 
                ? tareas.map(tarea => <li key={tarea}>{tarea}</li> )
                : <li>No hay tareas</li>
        }
    </ul>;
}
