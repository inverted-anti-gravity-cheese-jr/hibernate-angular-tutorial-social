package pl.stan.social;

import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

import pl.stan.social.controller.AuthController;
import pl.stan.social.controller.UsersController;
import pl.stan.social.model.User;
import spark.ModelAndView;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

public class MainClass {

	public static void main(String[] args) throws Exception {
		Spark.port(8080);
		Spark.staticFileLocation("/WEB-INF/resources");
		Spark.get("/ses", (req, res) -> {
			req.session().attribute("loggedAs", new User() {{
					setFistName("kookk");
				}});
			return "";
		});
		Spark.get("/user", (req, res) -> new UsersController().listUsers(req, res));
		Spark.get("/", (req, res) -> {
			HashMap<String, String> map = new HashMap<>();
			map.put("subpage", req.queryParams("page"));
			return new ModelAndView(map, "templates/page.html");
		}, new VelocityTemplateEngine());
		Spark.get("/view/app", (req, res) -> {
			return new ModelAndView(new HashMap<>(), "templates/app.html");
		}, new VelocityTemplateEngine());
		Spark.get("/view/login", (req, res) -> {
			return new ModelAndView(new HashMap<>(), "templates/login.html");
		}, new VelocityTemplateEngine());
		Spark.get("/view/register", (req, res) -> {
			return new ModelAndView(new HashMap<>(), "templates/register.html");
		}, new VelocityTemplateEngine());
		Spark.get("/login", (req, res) -> new AuthController().login(req, res));
		Spark.post("/register", (req, res) -> new AuthController().register(req, res));
		Spark.get("/login/loggedAs",
				(req, res) -> new ObjectMapper().writeValueAsString(req.session().attribute("loggedAs")));
		Spark.get("/login/heartbeat", (req, res) -> Boolean.toString(req.session().attribute("loggedAs") != null));
	}
}
