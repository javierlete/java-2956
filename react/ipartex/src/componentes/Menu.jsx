import { NavLink } from "react-router";

export default function Menu() {
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
					<li id="menu-usuario" className="navbar-text d-none"><i className="bi bi-person"></i> <span></span></li>
					<li id="menu-logout" className="nav-item d-none"><a className="nav-link" href="javascript:logout()"><i
							className="bi bi-box-arrow-right"></i></a></li>
					<li id="menu-login" className="nav-item"><NavLink className="nav-link" to="login"><i
							className="bi bi-box-arrow-in-right"></i></NavLink></li>
				</ul>
			</div>
		</div>
	</nav>;
}