import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1978_소수찾기 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1978"> 링크 </a>
     * <h1>문제</h1>
     *
     * 주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 주어진 수들 중 소수의 개수를 출력한다.
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1 || num == 0) {
                continue;
            }

            boolean isPrime = true;
            for (int j = 2; j < num; j++) {
                if (num % j == 0) {
                    isPrime = false; //소수가 아님
                    break;
                }
            }
            if (isPrime) {
                cnt++;
            }
        }
        System.out.println(cnt);





    }
}
