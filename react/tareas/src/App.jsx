import { useEffect, useState } from 'react'
import './App.css'
import ListadoTareas from './ListadoTareas'
import { TareaNueva } from './TareaNueva'
import { Titulo } from './Titulo'
import { URL_TAREAS } from './constantes'
import axios from 'axios'

function App() {
  const [tareas, setTareas] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const respuesta = await axios.get(URL_TAREAS);
      const listado = respuesta.data;
      setTareas(listado.reverse());
    }
    fetchData();
  }, []);

  return (
    <>
      <Titulo texto="Tareas" />

      <TareaNueva tareas={tareas} setTareas={setTareas} />

      <ListadoTareas tareas={tareas} />
    </>
  )
}

export default App
