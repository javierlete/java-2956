import { useEffect, useState } from 'react'
import './App.css'
import ListadoTareas from './ListadoTareas'
import { TareaNueva } from './TareaNueva'
import { Titulo } from './Titulo'
import { URL_TAREAS } from './constantes'

function App() {
  const [tareas, setTareas] = useState([]);

  useEffect(() => {
    async function fetchData() {
      // You can await here
      const respuesta = await fetch(URL_TAREAS);
      const listado = await respuesta.json()
      setTareas(listado.map(tarea => tarea.texto).reverse());
    }
    fetchData();
  }, []); // Or [] if effect doesn't need props 

  return (
    <>
      <Titulo texto="Tareas" />

      <TareaNueva tareas={tareas} setTareas={setTareas} />

      <ListadoTareas tareas={tareas} />
    </>
  )
}

export default App
