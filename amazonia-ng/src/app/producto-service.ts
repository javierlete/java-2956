import { Service } from '@angular/core';
import { Producto } from './producto';

@Service()
export class ProductoService {
    private readonly URL = 'http://localhost:8080/api/v2/productos';

    async obtenerTodos(): Promise<Producto[]> {
        const respuesta = await fetch(this.URL);
        const productos: any[] = await respuesta.json();

        return Promise.resolve(productos.map(p => ({ ...p, categoria: p.categoria.nombre })));
    }
}
