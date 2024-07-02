package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2178 {

    /**
     * N×M크기의 배열로 표현되는 미로가 있다. <br>
     *
     * 1	0	1	1	1	1 <br>
     * 1	0	1	0	1	0 <br>
     * 1	0	1	0	1	1 <br>
     * 1	1	1	0	1	1  <br>
     * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. <br>
     * 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. <br>
     * 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.<br>
     *<br>
     * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.<br>
     * */

    public static Set<int[]> visited = new HashSet<>(); //방문한 리스트

    public static int[][] map;

    public static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //방향배열 - 상, 하, 좌, 우

    public static int n;
    public static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // n*m 크기의 배열 (미로)
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp.charAt(j) - '0' ;
            }
        }


        bfs(0, 0); // (0, 0)부터 시작
        System.out.println(map[n-1][m-1]);
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();

        int[] first = {x, y};

        queue.offer(first); // Queue에 현재 노드 넣기
        visited.add(first); // 방문 기록

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<4; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1]};

                if(next[0] >= 0 && next[1] >= 0 && next[0] < n && next[1] < m) { //map bound에 넘치는지 검사
                    if( map[next[0]][next[1]] == 1 && !visited.contains(next)) { //갈 수 있는 길인지 & 방문 했던 길인지 검사
                        queue.add(next);
                        map[next[0]][next[1]] = map[current[0]][current[1]] + 1;
                        visited.add(next);
                    }
                }
            }
        }
    }

}
