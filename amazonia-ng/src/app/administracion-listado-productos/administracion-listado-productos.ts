import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto-service';

@Component({
  selector: 'app-administracion-listado-productos',
  imports: [],
  templateUrl: './administracion-listado-productos.html',
  styleUrl: './administracion-listado-productos.css',
})
export class AdministracionListadoProductos {
  productoService = inject(ProductoService);
  changeDetectionRef = inject(ChangeDetectorRef);

  productos: Producto[] = [];

  constructor() {
    this.productoService.obtenerTodos().then(productos => {
      this.productos = productos;
      this.changeDetectionRef.markForCheck();
    });
  }
}
