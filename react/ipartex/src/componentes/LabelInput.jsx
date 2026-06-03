export default function LabelInput({ etiqueta, tipo = 'text', nombre, valor = '' }) {
    const Etiqueta = tipo === 'textarea' ? 'textarea' : 'input';

    return <div class="row mb-2">
        <label for={nombre} class={'col-form-label' + (etiqueta ? ' col-sm-2' : '')}>{etiqueta}</label>
        <div class="col-sm">
            <Etiqueta type={tipo} class={tipo === 'submit' ? 'btn btn-primary' : 'form-control'} id={nombre} name={nombre} value={valor} />
        </div>
    </div>;
}