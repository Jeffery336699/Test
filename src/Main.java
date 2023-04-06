import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    private static final char[] values = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final HashMap<Character, Integer> map = new HashMap<>();

    static {
        for (int i = 0; i < 16; i++) {
            map.put(values[i], i);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String input = in.nextLine();
            output10Value(input);
        }
    }

    private static void output10Value(String input) {
        char[] chars = input.toCharArray();
        int result = 0;
        for (int i = 2; i < chars.length; i++) {
            int vInt = map.get(chars[i]);
            result = (result << 4) + vInt;
        }
        System.out.println(result);
    }


    // 去重排序输出
    private static void sortNums() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // FIXME: 不能有多余的东西 System.out.println("请输入有多少个数：");
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int input = in.nextInt();
            int[] is = new int[input];
            for (int i = 0; i < input; i++) {
                is[i] = in.nextInt();
            }
            outputSortIs(is);
        }
    }

    private static void outputSortIs(int[] is) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i : is) {
            treeSet.add(i);
        }
        for (Integer value : treeSet) {
            System.out.println(value);
        }
    }


    // 三空汽水瓶换一瓶
    private static void qishuiping() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int input = scanner.nextInt();
            if (input == 0) return;
            int result = getNums(input);
            System.out.println(result);
        }
    }

    public static int getNums(int inputNums) {
        int result = 0;
        while (inputNums >= 2) {
            int theOne;
            int theYu;
            inputNums++;
            theOne = inputNums / 3;
            theYu = inputNums % 3;
            result += theOne;
            inputNums = theOne + theYu;
            inputNums--;
        }
        return result;
    }
}