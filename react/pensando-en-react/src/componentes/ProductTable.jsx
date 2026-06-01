import ProductCategoryRow from "./ProductCategoryRow";
import ProductRow from "./ProductRow";

export default function ProductTable({ products }) {
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
                    products.map(product => 
                    <>
                    {
                        lastCategory !== product.category ? 
                        <ProductCategoryRow category={lastCategory = product.category} /> : 
                        <></>
                    }
                    <ProductRow product={product} />
                    </>)
                }
            </tbody>
        </table>
    </>;
}