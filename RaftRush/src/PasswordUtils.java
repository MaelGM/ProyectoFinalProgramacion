import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordUtils {

    /**
     * La función hashPassword toma una contraseña, la combina con un valor aleatorio (salt), y luego genera un hash seguro utilizando SHA-256.
     * El resultado es una cadena que contiene tanto el salt como el hash de la contraseña, separados por :, lo que permite almacenar
     * y verificar la contraseña de manera segura.
     * @param password Contraseña que se debe encriptar
     * @return Cadena que contiene tanto el salt como el hash de la contraseña, separados por :.
     * @throws NoSuchAlgorithmException
     */
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

    /**
     * La función checkPassword verifica si una contraseña ingresada es correcta comparándola con un hash almacenado.
     * @param password Contraseña sin encriptar
     * @param storedHash Contraseña encriptada
     * @return True si ambas son iguales, o false en caso contrario
     * @throws NoSuchAlgorithmException
     */
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

    /**
     * El método getSalt genera un salt aleatorio de 16 bytes usando SecureRandom
     * @return Salt aleatorio de 16 bytes usando SecureRandom
     */
    private static byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
