import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ5525_IOIOI {

    /**
     * <a href= "https://www.acmicpc.net/problem/1018"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * N+1개의 I와 N개의 O로 이루어져 있으면, I와 O이 교대로 나오는 문자열을 PN이라고 한다.
     * <br>
     * P1 IOI <br>
     * P2 IOIOI <br>
     * P3 IOIOIOI <br>
     * PN IOIOI...OI (O가 N개) <br>
     * I와 O로만 이루어진 문자열 S와 정수 N이 주어졌을 때, S안에 PN이 몇 군데 포함되어 있는지 구하는 프로그램을 작성하시오.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 N이 주어진다. 둘째 줄에는 S의 길이 M이 주어지며, 셋째 줄에 S가 주어진다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * S에 PN이 몇 군데 포함되어 있는지 출력한다.
     */
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        str = br.readLine();

        int count = 0;

        for (int i = 0; i <m; i++) {
            String current = String.valueOf(str.charAt(i));


        }
    }

    private void check(int index) {

        String next = "I";
        String current = "O";
        while (current.equals(next)) {
            current = String.valueOf(str.charAt(index));

            if (current.equals("O")) {
                next = "I";
            } else next = "O";

        }
    }
}
