import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5430_AC {

    /**
     * <a href= "https://www.acmicpc.net/problem/5430"> 링크 </a>
     * <h1>문제</h1>
     *
     * 선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.
     *
     * 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.
     *
     * 함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
     *
     * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
     *
     * 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
     *
     * 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
     *
     * 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
     *
     * 전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.
     *
     * */
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {


        ArrayDeque<Integer> deque;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());



        while(T --> 0) {

            String command = br.readLine();	// 문제에서 p에 해당하는 명령어
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");

            deque = new ArrayDeque<Integer>();

            for(int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            AC(command, deque);
        }

        System.out.println(sb);

    }

    public static void AC(String command, ArrayDeque<Integer> deque) {

        boolean isRight = true;

        for(char cmd : command.toCharArray()) {

            if(cmd == 'R') {
                isRight = !isRight;	// 방향을 바꿔준다.
                continue;
            }


            // D 함수이면서 isRight가 true 일 경우
            if(isRight) {

                // 만약 반환 된 원소가 없을 경우 error를 출력하도록 하고 함수 종료
                if(deque.pollFirst() == null) {
                    sb.append("error\n");
                    return;
                }

            }
            else {
                // 만약 반환 된 원소가 없을 경우 error를 출력하도록 하고 함수 종료
                if(deque.pollLast() == null) {
                    sb.append("error\n");
                    return;
                }
            }
        }
        // 모든 함수들이 정상적으로 작동했다면 덱의 남은 요소들을 출력문으로 만들어준다.
        print(deque, isRight);

    }

    public static void print(ArrayDeque<Integer> deque, boolean isRight) {

        sb.append('[');
        if(deque.size() > 0) {	//만약 출력 할 원소가 한 개 이상일 경우
            if(isRight) {	// 정방향일경우
                sb.append(deque.pollFirst());	// 먼저 첫 번째 원소를 넘겨준다.
                // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 하나씩 뽑아 넘긴다.
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollFirst());
                }
            }
            else {	// 역방향일경우
                sb.append(deque.pollLast());	// 먼저 뒤에서부터 첫 번째 원소를 넘겨준다.
                // 그 다음 원소부터 반점을 먼저 넘겨준 후 덱의 원소를 뒤에서부터 하나씩 뽑아 넘긴다.
                while(!deque.isEmpty()) {
                    sb.append(',').append(deque.pollLast());
                }
            }
        }
        sb.append(']').append('\n');	// 닫는 대괄호 및 개행으로 마무리
    }
}
