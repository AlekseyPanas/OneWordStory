package entities.codegenerators;

public interface CodeGenerator {
    /**
     * Generate a random string which has not been generated
     * by any call previously
     * @return a random generated string code
     */
    public String generateUniqueCode ();
}
