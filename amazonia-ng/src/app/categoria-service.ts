import { Service } from '@angular/core';
import { Categoria } from './categoria';

@Service()
export class CategoriaService {
    private readonly URL = 'http://localhost:8080/api/v2/categorias'

    async obtenerTodos(): Promise<Categoria[]> {
        const respuesta = await fetch(this.URL);
        const categorias = await respuesta.json();

        return Promise.resolve(categorias);
    }
}
