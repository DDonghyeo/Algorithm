import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1966"> 링크 </a>
     * <h1>문제</h1>
     *
     * 여러분도 알다시피 여러분의 프린터 기기는 여러분이 인쇄하고자 하는 문서를 인쇄 명령을 받은 ‘순서대로’, 즉 먼저 요청된 것을 먼저 인쇄한다. 여러 개의 문서가 쌓인다면 Queue 자료구조에 쌓여서 FIFO - First In First Out - 에 따라 인쇄가 되게 된다. 하지만 상근이는 새로운 프린터기 내부 소프트웨어를 개발하였는데, 이 프린터기는 다음과 같은 조건에 따라 인쇄를 하게 된다.
     *
     * 현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
     * 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
     * 예를 들어 Queue에 4개의 문서(A B C D)가 있고, 중요도가 2 1 4 3 라면 C를 인쇄하고, 다음으로 D를 인쇄하고 A, B를 인쇄하게 된다.
     *
     * 여러분이 할 일은, 현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다. 예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫 줄에 테스트케이스의 수가 주어진다. 각 테스트케이스는 두 줄로 이루어져 있다.
     *
     * 테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과, 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)이 주어진다. 이때 맨 왼쪽은 0번째라고 하자. 두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다. 중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력한다.
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {

            StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
            //문서의 개수
            int n = Integer.parseInt(st.nextToken());
            //궁금한 문서의 위치
            int targetIndex = Integer.parseInt(st.nextToken());

            st  = new StringTokenizer(br.readLine(), " ");

            LinkedList<int[]> queue = new LinkedList<>();

            for (int j = 0; j < n; j++) {
                queue.offer(new int[]{0, Integer.parseInt(st.nextToken())}); //j : target인지?
            }

            queue.get(targetIndex)[0] = 1; //target을 1로 바꾸기

            boolean isDone = false;
            int cnt = 1;
            while (!isDone) {
                int[] current = queue.poll();

                boolean isOut = true;
                for (int j = 0; j < queue.size(); j++) {
                    if (queue.get(j)[1] > current[1]) {
                        isOut = false;
                        break;
                    }
                }

                if (isOut) {
                    if (current[0] == 1) {
                        isDone = true;
                    } else cnt++;
                } else {
                    queue.offer(current);
                }

            }
            System.out.println(cnt);

        }


    }
}
