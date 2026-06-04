import { useState } from "react";

export default function LabelInput({ etiqueta, tipo = 'text', nombre, valor = '', onValorCambiado, onClick }) {
    const Etiqueta = tipo === 'textarea' ? 'textarea' : 'input';

    const [valorEstado, setValorEstado] = useState(valor);

    function cambioValor(nuevoValor) {
        setValorEstado(nuevoValor);

        onValorCambiado && onValorCambiado(nuevoValor);
    }

    return <div className="row mb-2">
        <label htmlFor={nombre} className={'col-form-label' + (etiqueta ? ' col-sm-2' : '')}>{etiqueta}</label>
        <div className="col-sm">
            <Etiqueta onClick={onClick} type={tipo} className={tipo === 'submit' ? 'btn btn-primary' : 'form-control'} id={nombre} name={nombre} value={valorEstado} onChange={e => cambioValor(e.target.value)} />
        </div>
    </div>;
}