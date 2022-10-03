import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvalidNegativeNumberException extends Throwable {
    ArrayList<Integer> invalidNumbers;
    String message;

    public InvalidNegativeNumberException(ArrayList<Integer> invalidNumbers) {
        this.invalidNumbers = invalidNumbers;
    }


    public String getMessage() {
        return "Invalid number exception: " + invalidNumbers.toString().replaceAll("[\\[\\]]", "");
    }

}
