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
        String test13 ="67";
        int sumTest13 =stringCalculator.add(test13);
        assert  sumTest13 ==67;
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
//        String test14 ="45\n,";
//        int sumTest14 = stringCalculator.add(test14);
//        assert sumTest14 ==45;
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
        String test12 = "-12,2,-3,4,-97";
        int sumTest12 = stringCalculator.add(test12); // exception must be thrown
    }


    int add(String numbers) {
        if (numbers.matches("(\\d+[,\\n]?\\d*)+")) {
            String[] retrievedDigit = numbers.split("[,\\n]");
            return executeCalculating(retrievedDigit);
        }else if (numbers.matches("//\\[?\\D+]?+\\n")) {
            return 0;
        }
         else if (numbers.matches("//\\[?\\D+]?\\n.+")) {
            String[] dividedString = numbers.split("\\n");
            String retrievedDelimiterArr = getDelimiter(dividedString);
            String[] retrievedDigit = getDigitSequence(numbers, retrievedDelimiterArr);
            return executeCalculating(retrievedDigit);
        } else return 0;
    }

    private String[] getDigitSequence(String numbers, String retrievedDelimiterArr) {
        return numbers.replaceAll("^//.+\\n", "")
                .replaceAll("[" + retrievedDelimiterArr + "]", ",").split(",");
    }

    private String getDelimiter(String[] dividedString) {
        String firstFormattingStep = dividedString[0].replaceFirst("//", "");
        String[] secondFormattingStep = firstFormattingStep.replaceAll("\\[", "").split("]");
        return Arrays.toString(secondFormattingStep);
    }

    public static int executeCalculating(String[] retrievedDigit) {
        int sum = 0;
        List<Integer> negativeNumber = new ArrayList<>();
        for (String s : retrievedDigit) {
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
            handleException(negativeNumber);
        }
        return sum;
    }

    static void handleException(List<Integer> negativeNumber) {
        try {
            throw new InvalidNegativeNumberException(negativeNumber);
        } catch (InvalidNegativeNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}