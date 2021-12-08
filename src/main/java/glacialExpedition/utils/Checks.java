package glacialExpedition.utils;

public class Checks {
    public void checkNames(String name, String message) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
}
