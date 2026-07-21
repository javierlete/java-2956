import { Service } from '@angular/core';
import { Producto } from './producto';
import { PRODUCTOS } from './mock-productos';

@Service()
export class ProductoService {
    async obtenerTodos(): Promise<Producto[]> {
        return Promise.resolve(PRODUCTOS);
    }
}
