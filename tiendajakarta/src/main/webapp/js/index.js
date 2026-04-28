'use strict';

window.addEventListener('DOMContentLoaded', async () => {
	console.log('cargado');
	
	const respuesta = await fetch('http://localhost:8080/tiendajakarta/api/v1/productos');
	const productos = await respuesta.json();
	
	console.log(productos);
	
	const ul = document.querySelector('ul');
	
	for(const p of productos) {
		console.log(p);
		
		const li = document.createElement('li');
		
		li.innerText = `${p.nombre}: ${p.precio}`;
		
		ul.appendChild(li);
	}
});
