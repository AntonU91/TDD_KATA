import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvalidNegativeNumberException extends Throwable {
    List<Integer> invalidNumbers;
    String message;

    public InvalidNegativeNumberException(List<Integer> invalidNumbers) {
        this.invalidNumbers = invalidNumbers;
    }

    @Override
    public String getMessage() {
        return invalidNumbers.toString().replaceAll("[\\[\\]]", "");
    }
}
