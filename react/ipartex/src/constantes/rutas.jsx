import { createBrowserRouter } from "react-router";
import ListaMensajes from "../componentes/ListaMensajes";
import Login from "../componentes/Login";
import App from "../App";
import { MENSAJES } from "./mocks";

export const URL_MENSAJES = 'http://localhost:8080/ipartex/api/v1/mensajes/breves';
export const URL_USUARIOS = 'http://localhost:8080/ipartex/api/v1/usuarios';

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { index: true, element: <ListaMensajes /> },
      { path: "login", element: <Login /> }
    ]
  },
]);
