package mvc.controller;
import mvc.model.Person;
import java.util.ArrayList;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = { "/api/people/*" })
public class PeopleController extends HttpServlet {
	private ArrayList<Person> people = new ArrayList<>();
	private int getId(HttpServletRequest req) {
		return req.getPathInfo().split("/").length > 0
			? Integer.parseInt(req.getPathInfo().split("/")[1]):
			-1;
	}
	private String getData(HttpServletRequest req) {
		StringBuilder s = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()))) {
			return s.append(br.lines().collect(Collectors.joining())).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		Person p = new Gson().fromJson(getData(req), Person.class);
		people.add(p);
		res.getWriter().print(new Gson().toJson(p));
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		final int id = getId(req);
		res.getWriter().print(new Gson().toJson(id < 0 ? people : people.get(id)));
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		final int id = getId(req);
		Person p = new Gson().fromJson(getData(req), Person.class);
		people.get(id).set(p);
		res.getWriter().print(new Gson().toJson(people.get(id)));
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		final int id = getId(req);
		final Person p = people.get(id);
		people.remove(id);
		res.getWriter().print(new Gson().toJson(p));
	}
}