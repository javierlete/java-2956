'use strict';

const FORMATO_FECHA = {
  year: "numeric",
  month: "long",
  day: "numeric",
  hour: "2-digit",
  minute: "2-digit",
  second: "2-digit"
};

const URL_BASE = 'http://localhost:8080/ipartex/api/v1'
const URL_MENSAJES = `${URL_BASE}/mensajes`;

window.addEventListener('DOMContentLoaded', async () => {
    const respuesta = await fetch(URL_MENSAJES);
    const mensajes = await respuesta.json();

    console.log(mensajes);

    const listaMensajes = document.getElementById('listado-mensajes');

    for (const m of mensajes) {
        const li = document.createElement('li');

        li.className = 'list-group-item d-flex justify-content-between align-items-start';

        li.innerHTML = `	
				<div class="ms-2 me-auto">
					<div class="fw-bold">${m.usuario.nombre}</div>
					${m.texto}
					<div>
						${m.numeroMeGusta} <a href="#"><i class="text-danger bi bi-heart"></i></a>
					</div>
				</div> 
				<span class="badge text-bg-primary rounded-pill">${new Date(m.momento).toLocaleString("es-ES", FORMATO_FECHA)}</span>
			`;

        listaMensajes.append(li);
    }
});
