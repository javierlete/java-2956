package bibliotecas.controladorfrontal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ipartek.formacion.ejemplos.ejemploweb.controladores.cf.RutasControlador.*;

@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String metodo = request.getMethod();
		String rutaEntrada = request.getPathInfo();
		
		if(rutaEntrada == null) {
			rutaEntrada = "/";
		}
		
		Map<String, String[]> entrada = request.getParameterMap();
		
		Map<String, Object> salida = new HashMap<>();
		
		Datos datos = new Datos(entrada, salida);
		
		String rutaSalida = switch(rutaEntrada) {
		case "/" -> index(datos); 
		case "/admin/listado" -> listado(datos);
		case "/admin/formulario" -> metodo.equals("GET") ? formulario(datos) : formularioPost(datos) ;
		case "/admin/borrar" -> borrar(datos);
		default -> throw new ServletException("NO SE HA ENCONTRADO LA RUTA " + rutaEntrada);
		};
		
		salida.forEach((clave, valor) -> request.setAttribute(clave, valor));
		
		if(rutaSalida.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + "/cf" + rutaSalida.replace("redirect:", ""));
			return;
		}
		
		request.getRequestDispatcher("/WEB-INF/vistas/" + rutaSalida + ".jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public record Datos(Map<String, String[]> entrada, Map<String, Object> salida) {}
}
