import { Routes } from '@angular/router';
import { FichasProductos } from './fichas-productos/fichas-productos';
import { FichaProducto } from './ficha-producto/ficha-producto';

export const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: '/productos' },
    { path: 'productos', component: FichasProductos },
    { path: 'productos/:id', component: FichaProducto },
];
