import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Producto } from '../producto';
import { ProductoService } from '../producto-service';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-fichas-productos',
  imports: [CurrencyPipe],
  templateUrl: './fichas-productos.html',
  styleUrl: './fichas-productos.css',
})
export class FichasProductos {
  changeDetectorRef = inject(ChangeDetectorRef);
  productoService = inject(ProductoService);

  productos: Producto[] = [];

  constructor() {
    this.productoService.obtenerTodos().then(productos => {
      this.productos = productos;
      this.changeDetectorRef.markForCheck();
    });
  }
}
