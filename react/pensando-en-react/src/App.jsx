import { useState } from 'react'
import './App.css'
import { FilterableProductTable } from './componentes/FilterableProductTable'
import { PRODUCTS } from './mocks/products'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <FilterableProductTable products={PRODUCTS} />
    </>
  )
}

export default App
