import { Routes } from '@angular/router';
import { FichasProductos } from './fichas-productos/fichas-productos';
import { FichaProducto } from './ficha-producto/ficha-producto';
import { AdministracionListadoProductos } from './administracion-listado-productos/administracion-listado-productos';
import { AdministracionFormularioProducto } from './administracion-formulario-producto/administracion-formulario-producto';

export const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: '/productos' },
    { path: 'productos', component: FichasProductos },
    { path: 'productos/:id', component: FichaProducto },
    { path: 'admin/productos', component: AdministracionListadoProductos },
    { path: 'admin/productos/:id', component: AdministracionFormularioProducto },
];
