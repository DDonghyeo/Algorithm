package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ10026_적록색약 {

    /**
     * <a href= "https://www.acmicpc.net/problem/10026"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
     * <p>
     * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)
     * <p>
     * 예를 들어, 그림이 아래와 같은 경우에
     * <br>
     * RRRBB <br>
     * GGBBB <br>
     * BBBRR<br>
     * BBRRR<br>
     * RRRRR<br><br>
     * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)
     * <p>
     * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
     * <p>
     * 둘째 줄부터 N개 줄에는 그림이 주어진다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
     */
    public static String[][] paint;
    public static boolean[][] visited;
    public static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //방향배열 - 상, 하, 좌, 우
    public static int N;
    public static int normal= 0;
    public static int color = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        paint = new String[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                paint[i][j] = String.valueOf(str.charAt(j));
            }
        }

        //일반 사람
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfsNormal(new int[]{i, j});
                }
            }
        }

        visited = new boolean[N][N]; //visited 초기화
        //색약
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfsColor(new int[]{i, j});
                }
            }
        }


        System.out.println(normal + " " + color);

    }

    public static void bfsNormal(int[] first) {
        Queue<int[]> queue = new LinkedList<>();

        String firstColor = paint[first[0]][first[1]];

        queue.offer(first); // Queue에 현재 노드 넣기
        visited[first[0]][first[1]] = true; // 방문 기록
        normal++;

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<4; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1]};

                if (next[0] >= 0 && next[1] >= 0 && next[0] < N && next[1] < N) {
                    if( paint[next[0]][next[1]].equals(firstColor) && !visited[next[0]][next[1]] ) { //같은 색인지 & 방문 했던 길인지 검사
                        queue.add(next);
                        visited[next[0]][next[1]] = true; //방문 기록
                    }
                }
            }
        }
    }

    public static void bfsColor(int[] first) {
        Queue<int[]> queue = new LinkedList<>();

        String firstColor = paint[first[0]][first[1]];

        queue.offer(first); // Queue에 현재 노드 넣기
        visited[first[0]][first[1]] = true; // 방문 기록
        color++;

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<4; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1]};

                if (next[0] >= 0 && next[1] >= 0 && next[0] < N && next[1] < N) {
                    if (firstColor.equals("R") || firstColor.equals("G")) {
                        //만약 빨강 혹은 초록이었을 경우
                        if ((paint[next[0]][next[1]].equals("R") || paint[next[0]][next[1]].equals("G")) && !visited[next[0]][next[1]]) { //같은 색인지 & 방문 했던 길인지 검사
                            queue.add(next);
                            visited[next[0]][next[1]] = true; //방문 기록
                        }
                    } else {
                        //아니면 그냥 검사
                        if (paint[next[0]][next[1]].equals(firstColor) && !visited[next[0]][next[1]]) { //같은 색인지 & 방문 했던 길인지 검사
                            queue.add(next);
                            visited[next[0]][next[1]] = true; //방문 기록
                        }
                    }

                }
            }
        }
    }
}
