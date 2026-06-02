export default function LabelInput({ etiqueta, tipo = 'text', nombre, valor = '' }) {
    return <div class="row mb-2">
        <label for={nombre} class="col-sm-2 col-form-label">{etiqueta}</label>
        <div class="col-sm">
            <input type={tipo} class={tipo === 'submit' ? 'btn btn-primary' : 'form-control'} id={nombre} name={nombre} value={valor} />
        </div>
    </div>;
}