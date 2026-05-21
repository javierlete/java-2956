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
    formMensaje = document.querySelector('#mensajes form');
    formLogin = document.querySelector('#login form');

    formMensaje.addEventListener('submit', enviarMensaje);
    formLogin.addEventListener('submit', login);

    guardarUsuario(obtenerUsuario());
    mensajes();
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
    const usuario = obtenerUsuario();

    const usuarioId = usuario ? '/' + usuario.id : '';

    const respuesta = await fetch(`${URL_MENSAJES}/breves${usuarioId}`);
    const mensajes = await respuesta.json();

    console.log(mensajes);

    const listaMensajes = document.getElementById('listado-mensajes');

    listaMensajes.innerHTML = '';

    for (const m of mensajes) {
        const li = crearMensaje(m);

        listaMensajes.append(li);
    }
}

function crearMensaje(m) {
    const li = document.createElement('li');

    li.id = 'm' + m.id;

    li.className = 'list-group-item ms-2 d-flex flex-column';

    const relleno = m.rellenado ? '-fill' : '';

    li.innerHTML = `	
			<div class="d-flex justify-content-between align-items-baseline">
				<div class="fw-bold">${m.usuario}</div>
				<span class="badge text-bg-primary rounded-pill">${new Date(m.momento).toLocaleString("es-ES", FORMATO_FECHA)}</span>
			</div>
			<p>${m.texto}</p>
			<div>
				${m.numeroMeGusta} <a href="javascript:${relleno ? 'noMeGusta' : 'meGusta'}(${m.id})"><i class="text-danger bi bi-heart${relleno}"></i></a>
				
				${m.numeroRespuestas} <a href="javascript:respuestas(${m.id})"><i class="bi bi-chat"></i></a> 
			</div>
		`;
    return li;
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

    mensajes();
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

async function meGusta(id) {
    const idUsuario = obtenerUsuario().id;
    console.log('ME GUSTA', id, idUsuario);

    const respuesta = await fetch(`${URL_USUARIOS}/me-gusta?mensajeId=${id}&usuarioId=${idUsuario}`);
    console.log(respuesta);

    mensajes();
}

async function noMeGusta(id) {
    const idUsuario = obtenerUsuario().id;
    console.log('NO me gusta', id, idUsuario);

    const respuesta = await fetch(`${URL_USUARIOS}/no-me-gusta?mensajeId=${id}&usuarioId=${idUsuario}`);
    console.log(respuesta);

    mensajes();
}

async function respuestas(id) {
    console.log('RESPUESTAS', id);

    const respuestas = await pedirRespuestas(id);

    if (!respuestas.length) {
        return;
    }

    const mensajePadre = document.getElementById('m' + id);
    const contenedorHijos = mensajePadre.querySelector('ul');

    if (contenedorHijos) {
        mensajePadre.querySelector('form')?.remove();
        mensajePadre.removeChild(contenedorHijos);
        return;
    }

    await cargarRespuestas(id);
}

async function pedirRespuestas(id) {
    const usuario = obtenerUsuario();

    let resto = '';

    if (usuario) {
        resto = '?idUsuario=' + usuario.id;
    }

    const respuesta = await fetch(`${URL_MENSAJES}/breves/respuestas/${id}${resto}`);
    const respuestas = await respuesta.json();

    return respuestas;
}

async function cargarRespuestas(id) {
    const respuestas = await pedirRespuestas(id);

    const mensajePadre = document.getElementById('m' + id);

    if (obtenerUsuario()) {
        const formulario = document.querySelector('#mensajes form').cloneNode(true);

        const input = document.createElement('input');

        input.type = 'hidden';
        input.name = 'id';
        input.value = id;

        formulario.appendChild(input);

        formulario.addEventListener('submit', enviarRespuesta);

        mensajePadre.appendChild(formulario);
    }

    mensajePadre.querySelector('ul')?.remove();

    const ul = document.createElement('ul');

    ul.className = 'list-group list-group my-4';

    for (const mensaje of respuestas) {
        const li = crearMensaje(mensaje);

        ul.appendChild(li);
    }

    mensajePadre.appendChild(ul);
}

async function enviarRespuesta(e) {
    e.preventDefault();

    const form = e.target;

    const texto = form.texto.value;
    const id = form['id'].value;

    console.log(texto);
    console.log(id);

    const mensaje = {
        texto,
        usuario: {
            id: obtenerUsuario().id
        },
        respuestaA: {
            id
        }
    }

    form.reset();

    console.log(mensaje);

    const respuesta = await fetch(URL_MENSAJES, {
        method: 'POST',
        body: JSON.stringify(mensaje),
        headers: {
            'Content-type': 'application/json'
        }
    });

    console.log(respuesta);

    respuestas(id);
}
