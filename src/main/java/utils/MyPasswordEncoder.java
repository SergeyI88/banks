package utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {

        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt(12));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }

    private String getMD5(CharSequence rawPassword) {
        String result = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(rawPassword.toString().getBytes());
            result = new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        MyPasswordEncoder myPasswordEncoder = new MyPasswordEncoder();
        System.out.println(myPasswordEncoder.encode("man"));
    }
}
