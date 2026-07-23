import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministracionFormularioProducto } from './administracion-formulario-producto';

describe('AdministracionFormularioProducto', () => {
  let component: AdministracionFormularioProducto;
  let fixture: ComponentFixture<AdministracionFormularioProducto>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministracionFormularioProducto],
    }).compileComponents();

    fixture = TestBed.createComponent(AdministracionFormularioProducto);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
