export default function SearchBar() {
    return <>
        <input style={{ width: '100%' }} type="search" placeholder="Search" /> <br/>
        <label><input type="checkbox" /> Only show products in stock</label>
    </>
}