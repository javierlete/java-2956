export default function Menu() {
    return <nav class="navbar navbar-expand-sm bg-dark mb-5" data-bs-theme="dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="javascript:mensajes()"><i class="bi bi-chat me-2"></i>
				IparteX</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-sm-0">
					<li class="nav-item"><a class="nav-link" href="javascript:mensajes()">Principal</a></li>
				</ul>
				<ul class="navbar-nav mb-2 mb-sm-0">
					<li id="menu-usuario" class="navbar-text d-none"><i class="bi bi-person"></i> <span></span></li>
					<li id="menu-logout" class="nav-item d-none"><a class="nav-link" href="javascript:logout()"><i
							class="bi bi-box-arrow-right"></i></a></li>
					<li id="menu-login" class="nav-item"><a class="nav-link" href="javascript:mostrarSeccion('login')"><i
							class="bi bi-box-arrow-in-right"></i></a></li>
				</ul>
			</div>
		</div>
	</nav>;
}