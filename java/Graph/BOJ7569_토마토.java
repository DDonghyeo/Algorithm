package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569_토마토 {

    /**
     * <a href= "https://www.acmicpc.net/problem/7569"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자모양 상자의 칸에 하나씩 넣은 다음, 상자들을 수직으로 쌓아 올려서 창고에 보관한다. <br>
     *
     * <img style="width=20px; height=20px;" src="https://u.acmicpc.net/c3f3343d-c291-40a9-9fe3-59f792a8cae9/Screen%20Shot%202021-06-22%20at%202.49.11%20PM.png"></img> <br>
     * <p>
     * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토에 인접한 곳은 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 그 최소 일수를 알고 싶어 한다. <br>
     * <p>
     * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다. <br>
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N과 쌓아올려지는 상자의 수를 나타내는 H가 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100 이다. 둘째 줄부터는 가장 밑의 상자부터 가장 위의 상자까지에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 하나의 상자에 담긴 토마토의 정보가 주어진다. 각 줄에는 상자 가로줄에 들어있는 토마토들의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다. 이러한 N개의 줄이 H번 반복하여 주어진다.
     * <p>
     * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 여러분은 토마토가 모두 익을 때까지 최소 며칠이 걸리는지를 계산해서 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
     */
    public static int M;
    public static int N;
    public static int H;
    public static int[][][] storage;
    public static boolean[][][] visited;
    public static int[][] direction = {{1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {0, -1, 0}, {0, 1, 0}}; //방향배열 - 위, 아래, 상, 하, 좌, 우
    public static Queue<int[]> queue;
    public static int maxValue = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        storage = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st  = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < M; k++) {
                    storage[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // 1 : 익은 토마토
        // 0 : 익지 않은 토마토
        // -1 : 토마토가 들어있지 않은 칸
        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (storage[i][j][k] == 1) {
                        queue.offer(new int[]{i, j, k});
                        visited[i][j][k] = true;
                    }
                }
            }
        }
        bfs();

        boolean isContainZero = false;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (storage[i][j][k] == 0) {
                        isContainZero = true;
                    }
                }
            }
        }

        if (isContainZero) {
            System.out.println(-1);
        } else {
            System.out.println(maxValue - 1);
        }

    }

    public static void bfs() {


        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기
            visited[current[0]][current[1]][current[2]] = true;

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<6; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1], current[2] + direction[i][2]};

                if (next[0] >= 0 && next[1] >= 0 && next[2] >= 0 //모든 값들이 0보다 큰지
                        && next[0] < H && next[1] < N && next[2] < M //범위 내에 있는지
                ) {
                    if( storage[next[0]][next[1]][next[2]] != -1 && !visited[next[0]][next[1]][next[2]] ) { //갈 수 있는 길인지 & 방문 했던 길인지 검사
                        queue.add(next);
                        storage[next[0]][next[1]][next[2]] = storage[current[0]][current[1]][current[2]] +1 ; // 지나간 길은 현재의 +1
                        visited[next[0]][next[1]][next[2]] = true; //방문 기록
                        if (maxValue < storage[next[0]][next[1]][next[2]]) {
                            maxValue = storage[next[0]][next[1]][next[2]];
                        }
                    }
                }
            }
        }
    }
}
