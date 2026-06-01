import { useState } from 'react'
import './App.css'
import { FilterableProductTable } from './componentes/FilterableProductTable'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <FilterableProductTable />
    </>
  )
}

export default App
