'use strict';

var URL_BASE = 'http://localhost:8080/ipartex/api/v1'
var URL_MENSAJES = `${URL_BASE}/mensajes`;
var URL_USUARIOS = `${URL_BASE}/usuarios`;

var ALMACEN = sessionStorage;

$(function() {
    $('#mensajes form').on('submit', enviarMensaje);
    $('#login form').on('submit', login);

    guardarUsuario(obtenerUsuario());
    mensajes();
});

function enviarMensaje(e) {
    e.preventDefault();

    console.log($('#texto').val());

    var mensaje = {
        "texto": $('#texto').val(),
        "usuario": {
            "id": obtenerUsuario().id
        }
    }

    $('#mensajes form')[0].reset();

    console.log(mensaje);

    $.ajax(URL_MENSAJES, {
        method: 'POST',
        data: JSON.stringify(mensaje),
        contentType: 'application/json'
    }).done(function(respuesta) {
        console.log(respuesta);
        cargarListado();
    });
}


function cargarListado() {
    var usuario = obtenerUsuario();

    var usuarioId = usuario ? '/' + usuario.id : '';

    $.getJSON(`${URL_MENSAJES}/breves${usuarioId}`).done(function(mensajes) {
        console.log(mensajes);

        $('#listado-mensajes').empty();

        $.each(mensajes, function() {
            $('#listado-mensajes').append(crearMensaje(this));
        });
    });
}

function crearMensaje(m) {
    var relleno = m.rellenado ? '-fill' : '';

    return $(`<li id="m${m.id}" class="list-group-item ms-2 d-flex flex-column">`).html(`	
			<div class="d-flex justify-content-between align-items-baseline">
				<div class="fw-bold">${m.usuario}</div>
				<span class="badge text-bg-primary rounded-pill"><jl-fecha valor="${m.momento}"/></span>
			</div>
			<p>${m.texto}</p>
			<div>
				<span class="numero-me-gusta">${m.numeroMeGusta}</span> <a href="javascript:${relleno ? 'noMeGusta' : 'meGusta'}(${m.id})"><i class="text-danger bi bi-heart${relleno}"></i></a>
				
				<span class="numero-respuestas">${m.numeroRespuestas}</span> <a href="javascript:respuestas(${m.id})"><i class="bi bi-chat"></i></a> 
			</div>
		`);
}

function mostrarSeccion(id) {
    $('main>section').hide();

    $('main>section#' + id).show();
}

function login(e) {
    e.preventDefault();

    $.getJSON(`${URL_USUARIOS}/autenticacion?email=${$('#email').val()}&password=${$('#password').val()}`).done(function(usuario) {
        console.log(usuario);
        guardarUsuario(usuario);
        $('#login form')[0].reset();
        mensajes();
    }).fail(function() {
        alert('Usuario o contraseña incorrectos');
    });
}

function guardarUsuario(usuario) {
    ALMACEN.setItem('usuario', JSON.stringify(usuario));

	console.log(usuario);
	
    $('#menu-usuario').removeClass('d-none').find('span').html(usuario ? usuario.nombre : '');

    $('#menu-login').addClass('d-none');
    $('#menu-logout').removeClass('d-none');

    $('#mensajes form').removeClass('d-none');
}

function obtenerUsuario() {
    return JSON.parse(ALMACEN.getItem('usuario'));
}

function logout() {
    ALMACEN.removeItem('usuario');

    $('#menu-usuario').addClass('d-none');
    $('#menu-login').removeClass('d-none');
    $('#menu-logout').addClass('d-none');

    $('#mensajes form').addClass('d-none');

    mostrarSeccion('login');
}

function mensajes() {
    cargarListado();

    mostrarSeccion('mensajes');
}

function meGusta(id) {
    var idUsuario = obtenerUsuario().id;
    console.log('ME GUSTA', id, idUsuario);

    $.getJSON(`${URL_USUARIOS}/me-gusta?mensajeId=${id}&usuarioId=${idUsuario}`).done(function(respuesta) {
        console.log(respuesta);
        mensajes();
    });
}

function noMeGusta(id) {
    const idUsuario = obtenerUsuario().id;
    console.log('NO me gusta', id, idUsuario);

    $.getJSON(`${URL_USUARIOS}/no-me-gusta?mensajeId=${id}&usuarioId=${idUsuario}`).done(function(respuesta) {
        console.log(respuesta);
        mensajes();
    });
}

function respuestas(id) {
    console.log('RESPUESTAS', id);
    const usuario = obtenerUsuario();

    pedirRespuestas(id).done(function(respuestas) {
        if (!respuestas.length && !usuario) {
            return;
        }

        if (borrarBloqueRespuestas(id)) {
            return;
        }

        cargarBloqueRespuestas(id);
    });
}

function borrarBloqueRespuestas(id) {
    var contenedorHijos = $(`#m${id} ul`);

    if (contenedorHijos.length) {
        $('#m' + id + ' form').remove();
        contenedorHijos.remove();
        return true;
    }

    return false;
}

function pedirRespuestas(id) {
    var usuario = obtenerUsuario();

    var resto = '';

    if (usuario) {
        resto = '?idUsuario=' + usuario.id;
    }

    return $.getJSON(`${URL_MENSAJES}/breves/respuestas/${id}${resto}`);
}

function cargarBloqueRespuestas(id) {
    if (obtenerUsuario()) {
        crearFormulario(id);
    }

    cargarRespuestas(id);
}

function cargarRespuestas(id) {

    pedirRespuestas(id).done(function(respuestas) {
        $('#m' + id + ' .numero-respuestas').html(respuestas.length);
        $('#m' + id + ' ul').remove();

        var ul = $('<ul class="list-group list-group my-4">').appendTo('#m' + id);

        $(respuestas).each(function() {
            ul.append(crearMensaje(this));
        });
    	
		$('#m' + id).append(ul);
    });
}

function crearFormulario(id) {
    $('#mensajes>form').clone().append(
        $('<input type="hidden" name="id" value="' + id + '">')
    ).on('submit', enviarRespuesta).appendTo($('#m' + id));
}

function enviarRespuesta(e) {
    e.preventDefault();

	var form = e.target;
	
    var id = +$(form).find('[name=id]').val();
    var texto = $(form).find('[name=texto]').val();

    console.log(texto);
    console.log(id);

    var mensaje = {
        texto,
        usuario: {
            id: obtenerUsuario().id
        },
        respuestaA: {
            id
        }
    }

    e.target.reset();

    console.log(mensaje);

    $.ajax(URL_MENSAJES, {
        method: 'POST',
        data: JSON.stringify(mensaje),
        contentType: 'application/json'
    }).done(function(respuesta) {
        console.log(respuesta);

        cargarRespuestas(id);
    });
}
