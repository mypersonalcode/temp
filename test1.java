import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//这个是一个test题，问题是如何获取一个数组内两个数之和等于target值，没有下标。
public class test1 {

    public static void main(String[] args) {
        System.out.println("Input:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int sum;
        int[] numberArray;
        if (null != line) {
            String[] num = line.split("&");
            if (num.length == 2 && null != num[1]) {
                sum = Integer.parseInt(num[1]);
                numberArray = formatJson(num[0]);
                getResult(numberArray, sum);
            } else {
                System.out.println("content is not right");
            }
        } else {
            System.out.println("content is null");
        }
    }

    public static int[] formatJson(String num) {
        try {
            num = num.replace("{", "[").replace("}", "]");
            JSONArray array = JSONArray.parseArray(num);
            int[] a = new int[array.size()];
            for (int i = 0; i < array.size(); i++) {
                a[i] = Integer.parseInt(array.get(i).toString());
            }
            return a;
        } catch (JSONException e) {
            throw new JSONException("content is not right");
        }
    }

    public static void getResult(int[] array, int sum) {
        int[] a = array;
        int tmp;
        int numberNum;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int j = 0; j < a.length; j++) {
            if (!map.containsKey(a[j])) {
                map.put(a[j], 1);
            } else {
                numberNum = map.get(a[j]);
                if ((sum == 2 * a[j]) && (1 == numberNum)) {
                    System.out.println(a[j] + "&" + a[j]);
                }
                map.put(a[j], numberNum + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            tmp = sum - entry.getKey();
            if ((map.containsKey(tmp)) && (map.get(tmp) != 0)) {
                System.out.println(entry.getKey() + "&" + tmp);
                map.put(entry.getKey(), 0);
            }
        }
    }
}
