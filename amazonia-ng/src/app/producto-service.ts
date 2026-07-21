import { Service } from '@angular/core';
import { Producto } from './producto';

@Service()
export class ProductoService {
    private readonly URL = 'http://localhost:3000/productos/';

    async obtenerTodos(): Promise<Producto[]> {
        const respuesta = await fetch(this.URL);
        return await respuesta.json();
    }
}
