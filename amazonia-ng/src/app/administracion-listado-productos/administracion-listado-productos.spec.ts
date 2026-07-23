import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministracionListadoProductos } from './administracion-listado-productos';

describe('AdministracionListadoProductos', () => {
  let component: AdministracionListadoProductos;
  let fixture: ComponentFixture<AdministracionListadoProductos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministracionListadoProductos],
    }).compileComponents();

    fixture = TestBed.createComponent(AdministracionListadoProductos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
