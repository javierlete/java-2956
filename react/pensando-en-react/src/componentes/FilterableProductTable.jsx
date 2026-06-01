import ProductTable from "./ProductTable";
import SearchBar from "./SearchBar";

export function FilterableProductTable({ products }) {
    return <>
        <SearchBar />
        <ProductTable products={products}/>
    </>;
}