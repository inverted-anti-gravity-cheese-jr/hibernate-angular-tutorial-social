package pl.stan.social.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import pl.stan.social.EncoderService;
import pl.stan.social.model.User;
import spark.Request;
import spark.Response;

public class AuthController {

	public String login(Request req, Response res) throws JsonGenerationException, JsonMappingException,
			NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		String username = req.params("username");
		String password = req.params("password");

		SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from User u where u.userName=:username");
		query.setParameter("username", username);
		List<?> l = query.list();

		if (!l.isEmpty()) {
			User user = (User) l.iterator().next();
			if (user.getPassword().equals(new EncoderService().hashPassword(password, user.getSalt()))) {
				req.session().attribute("loggedAs", user);
				return new ObjectMapper().writeValueAsString(user);
			}
		}
		return "null";
	}
	
	public String register(Request req, Response res) {
		System.out.println(req.params());
		return "";
	}

}
