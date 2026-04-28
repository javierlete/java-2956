'use strict';

const URL = 'http://localhost:8080/tiendajakarta/api/v1/productos';

window.addEventListener('DOMContentLoaded', async () => {
    console.log('cargado');

    await recargarLista();

    const form = document.querySelector('form');

	form.addEventListener('submit', async e => {
		e.preventDefault();
		
	    const producto = { nombre: form.nombre.value, precio: form.precio.value, descripcion: form.descripcion.value };

		console.log(producto);
		
		const respuesta = await fetch(URL, {
			method: 'POST',
			body: JSON.stringify(producto),
			headers: { 
				'Content-type': 'application/json' 
			},
		});
		
		const objeto = await respuesta.json();
		
		console.log(objeto);
		
		await recargarLista();
	});
	
});

async function recargarLista() {
    const respuesta = await fetch(URL);
    const productos = await respuesta.json();

    console.log(productos);

    const ul = document.querySelector('ul');

	ul.innerHTML = '';
	
    for (const p of productos) {
        console.log(p);

        const li = document.createElement('li');

        li.innerText = `${p.nombre}: ${p.precio}`;

        ul.appendChild(li);
    }
}
