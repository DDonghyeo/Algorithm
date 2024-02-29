package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2667 {

    /**
     * <h1>문제</h1>
     * <그림 1>과 같이 정사각형 모양의 지도가 있다.
     * 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
     * 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
     * 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다.
     * 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
     * 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
     *<br><br>
     * <h1>입력</h1>
     * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.<br>
     * 7 <br>
     * 0110100 <br>
     * 0110101<br>
     * 1110101<br>
     * 0000111<br>
     * 0100000<br>
     * 0111110<br>
     * 0111000<br>
     *<br><br>
     * <h1>출력</h1>
     * 3<br>
     * 7<br>
     * 8<br>
     * 9<br>
     * */

    public static boolean[][] visited ;

    public static int[][] map;

    public static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //방향배열 - 상, 하, 좌, 우

    public static ArrayList<Integer> houseSize = new ArrayList<>();


    public static int count = 1;

    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        // n*m 크기의 배열 (미로)
        map = new int[n][n];

        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //맵 탐색
                if (map[i][j] == 1) {
                    bfs(new int[]{i, j});
                }
            }
        }

        System.out.println(count-1);
        Collections.sort(houseSize);
        houseSize.forEach(System.out::println);

    }

    public static void bfs(int[] first) {
        Queue<int[]> queue = new LinkedList<>();
        count++;

        int houseSizeCount = 1;

        queue.offer(first); // Queue에 현재 노드 넣기
        visited[first[0]][first[1]] = true; // 방문 기록

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<4; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1]};


                if (next[0] >= 0 && next[1] >= 0 && next[0] < n && next[1] < n) {
                    if( map[next[0]][next[1]] == 1 && !visited[next[0]][next[1]] ) { //갈 수 있는 길인지 & 방문 했던 길인지 검사
                        queue.add(next);
                        map[next[0]][next[1]] = count;
                        visited[next[0]][next[1]] = true;
                        houseSizeCount++;
                    }
                }
            }
        }
        houseSize.add(houseSizeCount);
    }

}
