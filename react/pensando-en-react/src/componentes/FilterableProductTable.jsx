import ProductTable from "./ProductTable";
import SearchBar from "./SearchBar";

export function FilterableProductTable() {
    return <>
        <SearchBar />
        <ProductTable />
    </>;
}