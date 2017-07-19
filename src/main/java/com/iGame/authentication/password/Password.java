package com.iGame.authentication.password;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Password {
    public static PasswordEncoder encoder = new BCryptPasswordEncoder();
    private static final int workload = 12;
    
    public static String encrypt(String plaintext_password) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(plaintext_password, salt);
        return(hashed_password);
    }
    
    public static boolean isValidatePassword(String password, String hashPassword) {
        return BCrypt.checkpw(password, hashPassword);
    }
    
    public static void main(String[] args) {
    	//test
        String tmp = "0000";	//$2a$12$dQevIWsWVAVrkzFDk6Z2LOPJWpsNGEEbkFqbltCrQ3kYXuLn2HEpC
        String encryptor  = encrypt(tmp);
        System.out.println("encryptor:" + encryptor );
        
        boolean isValidatePassword = isValidatePassword( tmp, encryptor);
        System.out.println("isValidatePassword:" + isValidatePassword);
        System.out.println("----------");
        String method = "getUserId";
        String MethodOfSetOrGet = (method.toString()).substring(0, 3);
		String property = (method.toString()).substring(3, 4).toLowerCase() + (method.toString()).substring(4);
		
		System.out.println("MethodOfSetOrGet:" + MethodOfSetOrGet);
		System.out.println("property:" + property);
    }
}