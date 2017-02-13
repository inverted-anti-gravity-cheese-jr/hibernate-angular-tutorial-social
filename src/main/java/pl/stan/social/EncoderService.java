package pl.stan.social;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class EncoderService {
	
	public String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		PBEKeySpec spec = new PBEKeySpec("kod".toCharArray(), "sol".getBytes(), 1000, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        return new String(skf.generateSecret(spec).getEncoded());
	}
	
	public String generateHash() {
		Random rng = new Random();
		return Long.toHexString(Double.doubleToLongBits(rng.nextDouble()) * rng.nextInt());
	}

}
