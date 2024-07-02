import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1003_피보나치함수 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1003"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 다음 소스는 N번째 피보나치 수를 구하는 C++ 함수이다.
     * <br>
     * int fibonacci(int n) { <br>
     * if (n == 0) {<br>
     * printf("0");<br>
     * return 0;<br>
     * } else if (n == 1) {<br>
     * printf("1");<br>
     * return 1;<br>
     * } else {<br>
     * return fibonacci(n‐1) + fibonacci(n‐2);<br>
     * }<br>
     * }<br>
     * fibonacci(3)을 호출하면 다음과 같은 일이 일어난다.
     * <p>
     * fibonacci(3)은 fibonacci(2)와 fibonacci(1) (첫 번째 호출)을 호출한다. <br>
     * fibonacci(2)는 fibonacci(1) (두 번째 호출)과 fibonacci(0)을 호출한다. <br>
     * 두 번째 호출한 fibonacci(1)은 1을 출력하고 1을 리턴한다. <br>
     * fibonacci(0)은 0을 출력하고, 0을 리턴한다. <br>
     * fibonacci(2)는 fibonacci(1)과 fibonacci(0)의 결과를 얻고, 1을 리턴한다. <br>
     * 첫 번째 호출한 fibonacci(1)은 1을 출력하고, 1을 리턴한다. <br>
     * fibonacci(3)은 fibonacci(2)와 fibonacci(1)의 결과를 얻고, 2를 리턴한다. <br>
     * 1은 2번 출력되고, 0은 1번 출력된다. N이 주어졌을 때, fibonacci(N)을 호출했을 때, 0과 1이 각각 몇 번 출력되는지 구하는 프로그램을 작성하시오. <br>
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
     * <p>
     * 각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. N은 40보다 작거나 같은 자연수 또는 0이다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 각 테스트 케이스마다 0이 출력되는 횟수와 1이 출력되는 횟수를 공백으로 구분해서 출력한다.
     */
    public static int zeroCnt = 0;
    public static int oneCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        int[][] fibonaccis = new int[41][2];

        fibonaccis[0] = new int[]{1, 0};
        fibonaccis[1] = new int[]{0, 1};
        for (int i = 2; i <= 40; i++) {
            fibonaccis[i] = new int[]
                    {fibonaccis[i-1][0]+fibonaccis[i-2][0],
                    fibonaccis[i-1][1]+fibonaccis[i-2][1]};
        }

        for (int i = 0; i < num; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(fibonaccis[n][0] + " " + fibonaccis[n][1]);
        }

    }
}
