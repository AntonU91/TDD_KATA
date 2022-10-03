public class WrongInputException extends Throwable {

    @Override
    public String getMessage() {
        return "You have typed wrong data";
    }
}
