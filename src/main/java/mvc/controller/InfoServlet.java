package mvc.controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/api/info/*", "/fakepage.php", "/info.json" })
public class InfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().printf(
			"""
			{
				"AuthType":	"%s",
				"CharEncoding":	"%s",
				"ContentType":	"%s",
				"ContextPath":	"%s",
				"LocalAddr":	"%s",
				"LocalName":	"%s",
				"LocalPort":	"%s",
				"Method":	"%s",
				"PathInfo":	"%s",
				"Protocol":	"%s",
				"ProtocolRequestId":	"%s",
				"QueryString":	"%s",
				"RequestId":	"%s",
				"RequestURI":	"%s",
				"Scheme":	"%s",
				"PathTranslated":	"%s"
			}
			""",
			req.getAuthType(),
			req.getCharacterEncoding(),
			req.getContentType(),
			req.getContextPath(),
			req.getLocalAddr(),
			req.getLocalName(),
			req.getLocalPort(),
			req.getMethod(),
			req.getPathInfo(),
			req.getProtocol(),
			req.getProtocolRequestId(),
			req.getQueryString(),
			req.getRequestId(),
			req.getRequestURI(),
			req.getScheme(),
			req.getPathTranslated().replaceAll("\\\\", "/")
		);
	}
}