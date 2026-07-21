import { Component } from '@angular/core';
import { PRODUCTOS } from '../mock-productos';
import { Producto } from '../producto';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-fichas-productos',
  imports: [JsonPipe],
  templateUrl: './fichas-productos.html',
  styleUrl: './fichas-productos.css',
})
export class FichasProductos {
  productos: Producto[] = PRODUCTOS;
}
