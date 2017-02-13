package pl.stan.social;

import java.util.HashMap;

import org.codehaus.jackson.map.ObjectMapper;

import pl.stan.social.controller.UsersController;
import pl.stan.social.model.User;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import spark.Spark;

public class MainClass {
	
	public static void main(String[] args) {
		
		Spark.port(8080);
		Spark.staticFileLocation("/WEB-INF/resources");
		Spark.get("/user", (req, res) -> new UsersController().listUsers(req, res));
		Spark.get("/", (req, res) -> "Hello wyrld");
		Spark.get("/test", (req, res) -> { return new ModelAndView(new HashMap(), "templates/test.html"); }, new VelocityTemplateEngine());
	}
}
