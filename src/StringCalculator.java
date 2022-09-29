import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringCalculator {
    public static void main(String[] args) {
        StringCalculator stringCalculator = new StringCalculator();
        ///
        String test1 = "";
        int sumTest1 = stringCalculator.add(test1);
        assert sumTest1 == 0;
        ///
        String test2 = "2,4";
        int sumTest2 = stringCalculator.add(test2);
        assert sumTest2 == 6;
        ///
        String test5 = "4,";
        int sumTest5 = stringCalculator.add(test5);
        assert sumTest5 == 4;
        ///
        String test3 = "1,2,7";
        int sumTest3 = stringCalculator.add(test3);
        assert sumTest3 == 10;
        ///
        String test4 = "2\n2,5";
        int sumTest4 = stringCalculator.add(test4);
        assert sumTest4 == 9;
        ///
        String test6 = "//[***]\n2***5";
        int sumTest6 = stringCalculator.add(test6);
        assert sumTest6 == 7;
        ///
        String test7 = "//[;][%]\n2%6;6";
        int sumTes7 = stringCalculator.add(test7);
        assert sumTes7 == 14;
        ///
        String test8 = "//[!!!][//]\n7!!!4//5";
        int sumTest8 = stringCalculator.add(test8);
        assert sumTest8 == 16;
    }


    int add(String numbers) {
        if (numbers.contains("//")) {
            String[] retrievedDelimeter = numbers.split("\\n");
            String formattedDelimiter = retrievedDelimeter[0].replaceFirst("//", "");
            String[] arr = formattedDelimiter.replaceAll("\\[", "").split("]");
            String arrToString = Arrays.toString(arr).replaceAll(",", "");
            String[] retrievedDigit = numbers.replaceAll("^//.+\\n", "")
                    .replaceAll("[" + arrToString + "]", ",").split(",");
            return calculateWithCustomerDelimiter(retrievedDigit);

        } else {
            String[] retrievedDigit = numbers.split("[,\\n]");
            return calculateWithDefaultDelimiter(retrievedDigit);
        }
    }

    public static int calculateWithCustomerDelimiter(String[] retrievedDigit) {
        int sum = 0;
        List<Integer> negativeNumber = new ArrayList<>();
        for (int i = 0; i < retrievedDigit.length; i++) {
            if (retrievedDigit[i].equals("")) {
                sum += 0;
            } else if (Integer.parseInt(retrievedDigit[i]) < 0) {
                negativeNumber.add(Integer.parseInt(retrievedDigit[i]));
            } else {
                sum = (Integer.parseInt(retrievedDigit[i]) > 100) ? sum : sum + Integer.parseInt(retrievedDigit[i]);
            }
            if (!negativeNumber.isEmpty()) {
                try {
                    throw new InvalidNegativeNumberException(negativeNumber);
                } catch (InvalidNegativeNumberException e) {
                    e.getMessage();
                }
            }
        }
        return sum;
    }

    public static int calculateWithDefaultDelimiter(String[] retrievedDigit) {
        int sum = 0;
        List<Integer> negativeNumber = new ArrayList<>();
        for (String s : retrievedDigit) {
            if (s.equals("")) {
                break;
            }
            if (Integer.parseInt(s) > 100) {
                continue;
            }
            if (Integer.parseInt(s) < 0) {
                negativeNumber.add(Integer.parseInt(s));
            } else {
                sum += Integer.parseInt(s);
            }
            if (!negativeNumber.isEmpty()) {
                try {
                    throw new InvalidNegativeNumberException(negativeNumber);
                } catch (InvalidNegativeNumberException e) {
                    e.getMessage();
                }
            }
        }
        return sum;
    }


}