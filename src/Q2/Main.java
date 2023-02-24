package Q2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. 입력 데이터 받기
        HashMap<String, Object> data = getInput();

        Coin machine = new Coin((int)data.get("sum"), (int[])data.get("coins"));

        // 2. 경우의 수 구하기
        System.out.println(machine.numberOfCases());
    }

    /**
     * 입력 데이터 받기
     *
     * 결과 예시)
     * 입력)
     *      sum을 입력하세요. : 10
     *      동전 정수 배열을 입력하세요. : {1,2,5}
     * 출력)
     *      10
     * @return
     */
    static HashMap<String, Object> getInput(){
        Scanner sc = new Scanner(System.in);
        HashMap<String, Object> data = new HashMap<>();

        System.out.print("sum을 입력하세요. : ");
        int sum = Integer.parseInt(sc.nextLine());
        data.put("sum", sum);

        System.out.print("동전 정수 배열을 입력하세요. : ");
        String input = sc.nextLine().replaceAll(" ", "");

        int[] coins = Arrays.stream(input.substring(1, input.length()-1).split(","))
                .mapToInt(value -> Integer.parseInt(value))
                .toArray();
        data.put("coins", coins);

        return data;
    }
}
