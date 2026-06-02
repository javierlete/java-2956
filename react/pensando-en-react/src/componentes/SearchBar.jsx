export default function SearchBar({ filterText, inStockOnly }) {
    return <>
        <input style={{ width: '100%' }} type="search" placeholder="Search" value={filterText} /> <br />
        <label><input type="checkbox" checked={inStockOnly} /> Only show products in stock</label>
    </>
}