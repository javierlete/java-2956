import { createBrowserRouter } from "react-router";
import ListaMensajes from "../componentes/ListaMensajes";
import Login from "../componentes/Login";
import App from "../App";

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
