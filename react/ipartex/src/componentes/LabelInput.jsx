export default function LabelInput({ etiqueta, tipo = 'text', nombre, valor = '' }) {
    const Etiqueta = tipo === 'textarea' ? 'textarea' : 'input';

    return <div className="row mb-2">
        <label htmlFor={nombre} className={'col-form-label' + (etiqueta ? ' col-sm-2' : '')}>{etiqueta}</label>
        <div className="col-sm">
            <Etiqueta type={tipo} className={tipo === 'submit' ? 'btn btn-primary' : 'form-control'} id={nombre} name={nombre} value={valor} />
        </div>
    </div>;
}