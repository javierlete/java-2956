import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FichasProductos } from './fichas-productos';

describe('FichasProductos', () => {
  let component: FichasProductos;
  let fixture: ComponentFixture<FichasProductos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FichasProductos],
    }).compileComponents();

    fixture = TestBed.createComponent(FichasProductos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
