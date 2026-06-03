import LabelInput from "./LabelInput";

export default function FormularioEnviarMensaje() {
    return <form>
            <LabelInput nombre="texto" tipo="textarea" />
            <LabelInput tipo="submit" valor="Enviar mensaje" />
        </form>;
}