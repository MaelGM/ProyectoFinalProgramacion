import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordUtils {
    // Generar un hash para la contraseña
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        // Crear un salt
        byte[] salt = getSalt();

        // Crear el hash
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());

        // Concatenar salt y hash para almacenarlo juntos
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Verificar si la contraseña ingresada coincide con el hash almacenado
    public static boolean checkPassword(String password, String storedHash) throws NoSuchAlgorithmException {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        // Crear el hash de la contraseña ingresada usando el mismo salt
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] testHash = md.digest(password.getBytes());

        // Comparar el hash ingresado con el hash almacenado
        return MessageDigest.isEqual(testHash, hash);
    }

    // Generar un salt seguro
    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
