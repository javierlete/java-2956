import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { LabelInput, Opcion } from '../label-input/label-input';
import { ActivatedRoute } from '@angular/router';
import { ProductoService } from '../producto-service';
import { Producto } from '../producto';
import { CategoriaService } from '../categoria-service';

@Component({
  selector: 'app-administracion-formulario-producto',
  imports: [LabelInput],
  templateUrl: './administracion-formulario-producto.html',
  styleUrl: './administracion-formulario-producto.css',
})
export class AdministracionFormularioProducto {
  productoService = inject(ProductoService);
  categoriaService = inject(CategoriaService);

  changeDetectorRef = inject(ChangeDetectorRef);
  route = inject(ActivatedRoute);

  opciones: Opcion[] = [];

  producto: Producto = {
    id: 0,
    nombre: '',
    precio: 0,
    categoria: '',
    descripcion: '',
  };

  constructor() {
    const id = Number(this.route.snapshot.params['id']);
    
    this.categoriaService.obtenerTodos().then(categorias => {
      this.opciones = categorias.map(c => ({ id: String(c.id), texto: c.nombre }));

      if (id) {
        this.productoService.obtenerPorId(id).then(p => {
          this.producto = p;
          this.changeDetectorRef.markForCheck();
        });
      }
    });
  }
}
