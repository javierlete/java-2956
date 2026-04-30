package bibliotecas.controladorfrontal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.extern.java.Log;

@Log

@MultipartConfig
@WebServlet("/cf/*")
public class ControladorFrontalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String metodo = request.getMethod();
		String rutaEntrada = request.getPathInfo();

		if (rutaEntrada == null) {
			rutaEntrada = "/";
		}

		Map<String, String[]> entrada = new HashMap<>(); 
		Map<String, Object> salida = new HashMap<>();
		Map<String, Object> sesion = new HashMap<>();
		Map<String, InputStream> ficheros = new HashMap<>();
		AtomicReference<Boolean> cerrarSesion = new AtomicReference<Boolean>(false);
		String rutaRaiz = getServletContext().getRealPath("/");
		
		request.getParameterMap().entrySet().stream().forEach(par -> entrada.put(par.getKey(), par.getValue()));

		boolean esMultipart = request.getContentType() != null
				&& request.getContentType().toLowerCase().startsWith("multipart/");

		if (esMultipart) {
			for (Part part : request.getParts()) {

				String nombreCampo = part.getName();
				String nombreFichero = part.getSubmittedFileName();

				if (nombreFichero != null && !nombreFichero.isEmpty()) {
					// 🔹 Es un archivo
					System.out.println("Archivo subido: " + nombreFichero);

					entrada.put(nombreCampo, new String[] { nombreFichero });

					ficheros.put(nombreCampo, part.getInputStream());
				}
			}
		}

		session.getAttributeNames().asIterator()
				.forEachRemaining(clave -> sesion.put(clave, session.getAttribute(clave)));

		Datos datos = new Datos(metodo, entrada, salida, sesion, cerrarSesion, ficheros, rutaRaiz);

		String rutaSalida = ejecutarMetodoControlador(rutaEntrada, datos);

		if (rutaSalida == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");

			response.getWriter()
					.append("<h1>Esta no es la página que estás buscando (con gesto Jedi)</h1> " + rutaEntrada);

			return;
		}

		if (datos.cerrarSesion().get().booleanValue()) {
			session.invalidate();
		} else {
			salida.forEach((clave, valor) -> request.setAttribute(clave, valor));
			sesion.forEach((clave, valor) -> session.setAttribute(clave, valor));
		}

		if (rutaSalida.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + "/cf" + rutaSalida.replace("redirect:", ""));
			return;
		}

		request.getRequestDispatcher("/WEB-INF/vistas/" + rutaSalida + ".jsp").forward(request, response);
	}

	private String ejecutarMetodoControlador(String rutaEntrada, Datos datos) {
		try (ScanResult scanResult = new ClassGraph().enableAllInfo().scan()) {

			ClassInfoList clasesConRuta = scanResult.getClassesWithMethodAnnotation(Ruta.class.getName());

			for (ClassInfo classInfo : clasesConRuta) {
				log.fine(classInfo.toString());

				Class<?> clazz = classInfo.loadClass();

				for (Method m : clazz.getDeclaredMethods()) {
					if (m.isAnnotationPresent(Ruta.class)) {
						Ruta r = m.getAnnotation(Ruta.class);
						if (rutaEntrada.equals(r.value())) {
							return (String) m.invoke(scanResult, datos);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ControladorFrontalException("NO SE HA PODIDO EJECUTAR LA RUTA", e);
		}

		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public record Datos(String metodo, Map<String, String[]> entrada, Map<String, Object> salida,
			Map<String, Object> sesion, AtomicReference<Boolean> cerrarSesion, Map<String, InputStream> ficheros, String rutaRaiz) {
	}
}
