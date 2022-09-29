import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        String str = "12e97wwww1";
//        System.out.println(Integer.parseInt(str));
String s = "adadasd";
        System.out.println(s.matches("^ad\\w+"));
        String[] split = s.split("");
        System.out.println(Arrays.toString(split));

        ArrayList<Integer> test = new ArrayList<>();
        test.add(234);
        test.add(5);
        System.out.println(Arrays.toString(test.toArray()));
    }
}
