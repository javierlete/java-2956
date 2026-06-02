export default function SearchBar({ filterText, inStockOnly, onFilterTextChange, onInStockOnlyChange }) {
    return <>
        <input style={{ width: '100%' }} type="search" placeholder="Search" value={filterText} onChange={e => onFilterTextChange(e.target.value)} /> <br />
        <label><input type="checkbox" checked={inStockOnly} onChange={e => onInStockOnlyChange(e.target.checked)} /> Only show products in stock</label>
    </>
}