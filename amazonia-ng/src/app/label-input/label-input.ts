import { Component, input } from '@angular/core';

@Component({
  selector: 'app-label-input',
  imports: [],
  templateUrl: './label-input.html',
  styleUrl: './label-input.css',
})
export class LabelInput {
  etiqueta = input<string>();
  tipo = input<string>('text');
  opciones = input<Opcion[]>([]);
}

export interface Opcion {
  id: string;
  texto: string;
}