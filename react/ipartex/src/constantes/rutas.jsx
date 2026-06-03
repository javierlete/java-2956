import { createBrowserRouter } from "react-router";
import ListaMensajes from "../componentes/ListaMensajes";
import Login from "../componentes/Login";
import App from "../App";
import { MENSAJES } from "./mocks";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { index: true, element: <ListaMensajes mensajes={MENSAJES} /> },
      { path: "login", element: <Login /> }
    ]
  },
]);
