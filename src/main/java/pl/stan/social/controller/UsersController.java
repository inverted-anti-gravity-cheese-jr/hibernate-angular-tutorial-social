package pl.stan.social.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import spark.Request;
import spark.Response;

public class UsersController {

	public String listUsers(Request req, Response resp) throws IOException {
		if(req.session().attribute("loggedAs") == null) {
			return "null";
		}
		
		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		List<?> users = session.createQuery("FROM User").list();
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(users);
	}

}
