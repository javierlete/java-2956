import { CurrencyPipe } from '@angular/common';
import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto-service';

@Component({
  selector: 'app-ficha-producto',
  imports: [CurrencyPipe],
  templateUrl: './ficha-producto.html',
  styleUrl: './ficha-producto.css',
})
export class FichaProducto {
  route = inject(ActivatedRoute);
  changeDetectionRef = inject(ChangeDetectorRef);
  productoService = inject(ProductoService);

  producto: Producto = {
    id: 0,
    nombre: '',
    descripcion: '',
    precio: 0,
    categoria: ''
  }

  constructor() {
    const id = Number(this.route.snapshot.params['id']);

    this.productoService.obtenerPorId(id).then(producto => {
      this.producto = producto;
      this.changeDetectionRef.markForCheck();
    });
  }
}
