import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1676 {
    /**
     * <a href= "https://www.acmicpc.net/problem/1676"> 링크 </a>
     * <h1>팩토리얼 0의 개수</h1>
     *
     * N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램을 작성하시오.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫째 줄에 N이 주어진다. (0 ≤ N ≤ 500)
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 첫째 줄에 구한 0의 개수를 출력한다.
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        int cnt = 0;

        // 0의 개수는 소인수 분해 시 2와 5 한쌍의 갯수이다.
        // 5는 2보다 커서 팩토리얼이 커질수록 자연스럽게 2의 수보다 5의 수가 더 많다.
        // 즉, 소인수 분해 시 5의 개수만 체크하면 된다.
        while (n >= 5) {
            cnt += n/5; //5가 들어간 갯수가 나온다.
            n /= 5;
        }

        System.out.println(cnt);

    }
}
