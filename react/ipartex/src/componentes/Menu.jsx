import { useContext } from "react";
import { NavLink } from "react-router";
import { UsuarioContext } from "../contextos/UsuarioContext";

export default function Menu() {
	const { usuario, setUsuario } = useContext(UsuarioContext);
	
	function logout() {
		localStorage.removeItem('usuario');

		setUsuario(undefined);
	}

	return <nav className="navbar navbar-expand-sm bg-dark mb-5" data-bs-theme="dark">
		<div className="container-fluid">
			<NavLink className="navbar-brand" to="/"><i className="bi bi-chat me-2"></i>
				IparteX</NavLink>
			<button className="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span className="navbar-toggler-icon"></span>
			</button>
			<div className="collapse navbar-collapse" id="navbarSupportedContent">
				<ul className="navbar-nav me-auto mb-2 mb-sm-0">
					<li className="nav-item"><NavLink className="nav-link" to="/">Principal</NavLink></li>
				</ul>
				<ul className="navbar-nav mb-2 mb-sm-0">
					{usuario ? <li id="menu-usuario" className="navbar-text"><i className="bi bi-person"></i> <span>{usuario.nombre}</span></li>: null}
					{usuario ? <li id="menu-logout" className="nav-item"><a onClick={logout} className="nav-link" href="#"><i
						className="bi bi-box-arrow-right"></i></a></li>: null}
					{usuario ? null: <li id="menu-login" className="nav-item"><NavLink className="nav-link" to="login"><i
						className="bi bi-box-arrow-in-right"></i></NavLink></li>}
				</ul>
			</div>
		</div>
	</nav>;
}