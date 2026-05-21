const FORMATO = {
    year: "numeric",
    month: "long",
    day: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    second: "2-digit"
};

class JlFecha extends HTMLElement {
	constructor() {
		super();
		this.innerHTML = new Date(this.getAttribute('valor')).toLocaleString("es-ES", FORMATO);
	}
}

customElements.define("jl-fecha", JlFecha);
