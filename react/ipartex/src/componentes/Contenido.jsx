import { Outlet } from "react-router";

export default function Contenido() {
    return <main className="container">
            <Outlet />
        </main>;
}