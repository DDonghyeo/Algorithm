import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1929_소수구하기 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1929"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1 ≤ M ≤ N ≤ 1,000,000) M이상 N이하의 소수가 하나 이상 있는 입력만 주어진다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
     */

    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        prime = new boolean[n + 1];
        get_prime();

        for(int i = m; i <= n; i++) {
            if(!prime[i]) System.out.println(i); //소수면 print
        }
    }


    // 에라토스테네스의 체 알고리즘
    public static void get_prime() {
        // true = 소수아님 , false = 소수
        prime[0] = prime[1] = true;

        for(int i = 2; i <= Math.sqrt(prime.length); i++) {
            if(prime[i]) continue;
            for(int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
