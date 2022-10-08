package entities.codegenerators;

import java.util.ArrayList;
import java.util.Random;

public class NumericCodeGenerator implements CodeGenerator {
    private int length;
    private final Random rand;
    private final ArrayList<Integer> existingCodes;

    /**
     * Create a new code generator
     * @param length Length of codes to generate
     */
    public NumericCodeGenerator(int length) {
        this.length = length;
        rand = new Random();
        existingCodes = new ArrayList<>();
    }

    /**
     * Create a new code generator which cannot generate the provided
     * codes
     * @param length Length of codes to generate
     */
    public NumericCodeGenerator(int length, String[] existingCodes) {
        // Call above constructor
        this(length);

        for (String s: existingCodes) {
            // If the string is an integer number,
            if (s.matches("^[0-9]*$")) {
                this.existingCodes.add(Integer.parseInt(s));
            };
        }
    }

    @Override
    public String generateUniqueCode() {
        double val;
        int code;

        // Generate a random integer not in existingCodes with at most length digits
        do {
            val = rand.nextDouble() * Math.pow(10, length);
            code = (int) val;
        } while (existingCodes.contains(code));

        // Add the integer to the list to prevent it getting chosen again
        existingCodes.add(code);

        // Convert to string and add leading 0s to match length, if needed
        StringBuilder s = new StringBuilder(Integer.valueOf(code).toString());
        while (s.length() < length) {
            s.insert(0, "0");
        }

        return s.toString();
    }
}
