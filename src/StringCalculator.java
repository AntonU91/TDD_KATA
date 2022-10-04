import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringCalculator {
    public static void main(String[] args) throws WrongInputException, InvalidNegativeNumberException {

        StringCalculator stringCalculator = new StringCalculator();
        Scanner scanner = new Scanner(System.in);
        try {
            String inputStr = scanner.nextLine();
            System.out.println(stringCalculator.add(inputStr));
        } catch (WrongInputException e) {
            System.out.println(e.getMessage());
        } catch (InvalidNegativeNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
        /// TESTS
        String test1 = "";
        int sumTest1 = stringCalculator.add(test1);
        assert sumTest1 == 0;
        ///
        String test13 = "67";
        int sumTest13 = stringCalculator.add(test13);
        assert sumTest13 == 67;
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
        String test11 = "//#\n4#9";
        int sumTest11 = stringCalculator.add(test11);
        assert sumTest11 == 13;
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
        ///
        String test9 = "1001,2,7,999";
        int sumTest9 = stringCalculator.add(test9);
        assert sumTest9 == 1008;
        ///
        String test10 = "//[&&&&]\n";
        int sumTest10 = stringCalculator.add(test10);
        assert sumTest10 == 0;
        ///
        String test17 = "//[!!][##]\n25,56!!89\n33\n";
        System.out.println(stringCalculator.add(test17));
    }

    int add(String inputStr) throws WrongInputException, InvalidNegativeNumberException {
        if (inputStr.equals("")) {
            return 0;
        } else if (inputStr.matches("(-?\\d+[,\\n]?-?\\d*)+")) {
            String[] retrievedDigit = inputStr.split("[,\\n]");
            return executeCalculating(retrievedDigit);
        } else if (inputStr.matches("//\\[?\\D+]?+\\n")) { //
            return 0;
        } else if (inputStr.matches("//\\[?\\D+]?\\n(-?\\d+([,\\n]?)|(\\D*))+")) {
            String[] dividedString = inputStr.split("\\n");
            String[] retrievedDelimiterArr = getDelimiter(dividedString);
            String[] retrievedDigit = getDigitSequence(inputStr, retrievedDelimiterArr);
            return executeCalculating(retrievedDigit);
        } else throw new WrongInputException();
    }

    private String[] getDigitSequence(String inputStr, String[] retrievedDelimiterArr) {
        inputStr = inputStr.replaceAll("^//.+\\n", "").replaceAll("\\n",",");
        for (String delimiter : retrievedDelimiterArr) {
            if (delimiter.matches("[+?*$^\\[ \\](){}]+")) {
                inputStr = (inputStr.replaceAll(Pattern.quote(delimiter), ","));
            } else {
                inputStr = (inputStr.replaceAll((delimiter), ","));
            }
        }
        return inputStr.split(","); //[[2] [56]]
    }

    private String[] getDelimiter(String[] dividedString) {
        String firstFormattingStep = dividedString[0].replaceFirst("//", "");
        return firstFormattingStep.replaceAll("\\[", "").split("]");
    }

    public static int executeCalculating(String[] retrievedDigit) throws InvalidNegativeNumberException {
        int sum = 0;
        ArrayList<Integer> negativeNumber = new ArrayList<>();
        for (String s : retrievedDigit) { //1001
            if (s.equals("") || Integer.parseInt(s) > 1000) {
                continue;
            }
            if (Integer.parseInt(s) < 0) {
                negativeNumber.add(Integer.parseInt(s));
            } else {
                sum += Integer.parseInt(s);
            }
        }
        if (!negativeNumber.isEmpty()) {
            throw new InvalidNegativeNumberException(negativeNumber);
        }
        return sum;
    }
}