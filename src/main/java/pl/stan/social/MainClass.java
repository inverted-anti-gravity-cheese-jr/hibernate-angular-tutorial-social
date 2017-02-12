package pl.stan.social;

import java.util.HashMap;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import spark.Spark;

public class MainClass {
	
	public static void main(String[] args) {
		Spark.port(8080);
		Spark.staticFileLocation("static/");
		Spark.get("/", (req, res) -> "Hello wyrld");
		Spark.get("/test", (req, res) -> { return new ModelAndView(new HashMap(), "templates/test.html"); }, new VelocityTemplateEngine());
	}
}
