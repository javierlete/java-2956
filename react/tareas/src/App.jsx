import { useState } from 'react'
import './App.css'
import ListadoTareas from './ListadoTareas'
import { TareaNueva } from './TareaNueva'
import { Titulo } from './Titulo'

function App() {
  const [tareas, setTareas] = useState([]);
  
  return (
    <>
      <Titulo texto="Tareas" />

      <TareaNueva tareas={tareas} setTareas={setTareas}/>

      <ListadoTareas tareas={tareas}/>
    </>
  )
}

export default App
