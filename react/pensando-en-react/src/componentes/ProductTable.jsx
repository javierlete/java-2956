import { Fragment } from "react";
import ProductCategoryRow from "./ProductCategoryRow";
import ProductRow from "./ProductRow";

export default function ProductTable({ products, filterText, inStockOnly }) {
    let lastCategory;

    return <>
        <table style={{ width: '100%' }}>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                {
                    products.filter(product => product.name.toLowerCase().includes(filterText.toLowerCase()) && (inStockOnly ? product.stocked === inStockOnly : true)).map(product =>
                        <Fragment key={product.category + product.name}>
                            {
                                lastCategory !== product.category ?
                                    <ProductCategoryRow category={lastCategory = product.category} /> :
                                    <></>
                            }
                            <ProductRow product={product} />
                        </Fragment>)
                }
            </tbody>
        </table>
    </>;
}