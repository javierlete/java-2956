import ProductCategoryRow from "./ProductCategoryRow";
import ProductRow from "./ProductRow";

export default function ProductTable() {
    return <>
        <table style={{width: '100%'}}>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <ProductCategoryRow />
                <ProductRow />
                <ProductRow />
                <ProductRow />
                <ProductCategoryRow />
                <ProductRow />
                <ProductRow />
            </tbody>
        </table>
    </>;
}