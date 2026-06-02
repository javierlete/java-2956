import Copyright from "./Copyright";

export default function Pie() {
    return <footer
		class="mt-5 p-2 text-bg-dark d-flex justify-content-between align-items-baseline">
		<p><Copyright/></p>
		<ul class="nav">
			<li class="nav-item"><a class="nav-link text-light" href="#"><i
					class="bi bi-facebook"></i></a></li>
			<li class="nav-item"><a class="nav-link text-light" href="#"><i
					class="bi bi-twitter-x"></i></a></li>
			<li class="nav-item"><a class="nav-link text-light" href="#"><i
					class="bi bi-youtube"></i></a></li>
			<li class="nav-item"><a class="nav-link text-light" href="#"><i
					class="bi bi-instagram"></i></a></li>
			<li class="nav-item"><a class="nav-link text-light" href="#"><i
					class="bi bi-tiktok"></i></a></li>
		</ul>
	</footer>;
}