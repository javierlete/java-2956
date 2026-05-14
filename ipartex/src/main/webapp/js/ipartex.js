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
const URL_USUARIOS = `${URL_BASE}/usuarios`;

const ALMACEN = sessionStorage;

let formMensaje;
let formLogin;

window.addEventListener('DOMContentLoaded', async () => {
    mostrarSeccion('mensajes');

    await cargarListado();

    formMensaje = document.querySelector('#mensajes form');
    formLogin = document.querySelector('#login form');

    formMensaje.addEventListener('submit', enviarMensaje);
    formLogin.addEventListener('submit', login);
});

async function enviarMensaje(e) {
    e.preventDefault();

    console.log(formMensaje.texto.value);

    const mensaje = {
        "texto": formMensaje.texto.value,
        "usuario": {
            "id": obtenerUsuario().id
        }
    }

    formMensaje.reset();

    console.log(mensaje);

    const respuesta = await fetch(URL_MENSAJES, {
        method: 'POST',
        body: JSON.stringify(mensaje),
        headers: {
            'Content-type': 'application/json'
        }
    });

    console.log(respuesta);

    await cargarListado();
}


async function cargarListado() {
    const respuesta = await fetch(URL_MENSAJES);
    const mensajes = await respuesta.json();

    console.log(mensajes);

    const listaMensajes = document.getElementById('listado-mensajes');

    listaMensajes.innerHTML = '';

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
}

function mostrarSeccion(id) {
    const secciones = document.querySelectorAll('main>section');

    for (const seccion of secciones) {
        seccion.style.display = 'none';
    }

    const seccion = document.querySelector('main>section#' + id);

    seccion.style.display = null;
}

async function login(e) {
    e.preventDefault();

    const email = formLogin.email.value;
    const password = formLogin.password.value;

    const respuesta = await fetch(`${URL_USUARIOS}/autenticacion?email=${email}&password=${password}`);

    console.log(respuesta);

    if (!respuesta.ok) {
        alert('Usuario o contraseña incorrectos');
        return;
    }

    const usuario = await respuesta.json();

    console.log(usuario);

    guardarUsuario(usuario);
	
	formLogin.reset();
	
	mostrarSeccion('mensajes');
}

function guardarUsuario(usuario) {
    ALMACEN.setItem('usuario', JSON.stringify(usuario));

    const li = document.getElementById('menu-usuario');

    li.querySelector('span').innerHTML = usuario.nombre;

    li.classList.remove('d-none');

    document.getElementById('menu-login').classList.add('d-none');
    document.getElementById('menu-logout').classList.remove('d-none');

	formMensaje.classList.remove('d-none');
}

function obtenerUsuario() {
    return JSON.parse(ALMACEN.getItem('usuario'));
}

function logout() {
    ALMACEN.removeItem('usuario');

    document.getElementById('menu-usuario').classList.add('d-none');
    document.getElementById('menu-login').classList.remove('d-none');
    document.getElementById('menu-logout').classList.add('d-none');
	
	formMensaje.classList.add('d-none');
	
	mostrarSeccion('login');
}

function mensajes() {
	cargarListado();
	
	mostrarSeccion('mensajes');
}