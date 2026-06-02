import LabelInput from "./LabelInput";

export default function Login() {
    return <form>
        <LabelInput etiqueta="Email" nombre="email" tipo="email" />
        <LabelInput etiqueta="Contraseña" nombre="password" tipo="password" />
        <LabelInput tipo="submit" valor="Iniciar sesión" />
    </form>;
}
