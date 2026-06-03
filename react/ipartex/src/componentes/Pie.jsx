import Copyright from "./Copyright";

export default function Pie() {
    return <footer
		className="mt-5 p-2 text-bg-dark d-flex justify-content-between align-items-baseline">
		<p><Copyright/></p>
		<ul className="nav">
			<li className="nav-item"><a className="nav-link text-light" href="#"><i
					className="bi bi-facebook"></i></a></li>
			<li className="nav-item"><a className="nav-link text-light" href="#"><i
					className="bi bi-twitter-x"></i></a></li>
			<li className="nav-item"><a className="nav-link text-light" href="#"><i
					className="bi bi-youtube"></i></a></li>
			<li className="nav-item"><a className="nav-link text-light" href="#"><i
					className="bi bi-instagram"></i></a></li>
			<li className="nav-item"><a className="nav-link text-light" href="#"><i
					className="bi bi-tiktok"></i></a></li>
		</ul>
	</footer>;
}