import { useContext, useState } from "react";
import { URL_USUARIOS } from "../constantes/rutas";
import LabelInput from "./LabelInput";
import { useNavigate } from "react-router";
import { UsuarioContext } from "../contextos/UsuarioContext";

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const { setUsuario } = useContext(UsuarioContext);

    const navigate = useNavigate();

    async function autenticarse(e) {
        e.preventDefault();
        console.log(email, password);

        const respuesta = await fetch(`${URL_USUARIOS}/autenticacion?email=${email}&password=${password}`);

        console.log(respuesta);

        if (!respuesta.ok) {
            alert('Login incorrecto');

            return;
        }

        const usuario = await respuesta.json();

        console.log(usuario);

        localStorage.setItem('usuario', JSON.stringify(usuario));
        setUsuario(usuario);

        // TODO: Corregir vaciado de formulario
        setEmail('');
        setPassword('');

        navigate('/');
    }

    return <form onSubmit={autenticarse}>
        <LabelInput etiqueta="Email" nombre="email" tipo="email" valor={email} onValorCambiado={v => setEmail(v)} />
        <LabelInput etiqueta="Contraseña" nombre="password" tipo="password" valor={password} onValorCambiado={v => setPassword(v)} />
        <LabelInput tipo="submit" valor="Iniciar sesión" />
    </form>;
}
