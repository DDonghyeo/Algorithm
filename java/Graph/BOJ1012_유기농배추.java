package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1012"> 링크 </a>
     * <h1>문제</h1>
     *
     * 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다. 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에, 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다. 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다. 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어, 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
     *
     * 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다. 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다. 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
     *<br>
     * 1	1	0	0	0	0	0	0	0	0 <br>
     * 0	1	0	0	0	0	0	0	0	0 <br>
     * 0	0	0	0	1	0	0	0	0	0 <br>
     * 0	0	0	0	1	0	0	0	0	0 <br>
     * 0	0	1	1	0	0	0	1	1	1 <br>
     * 0	0	0	0	1	0	0	1	1	1 <br>
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 그 다음 줄부터 각각의 테스트 케이스에 대해 첫째 줄에는 배추를 심은 배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 두 배추의 위치가 같은 경우는 없다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
     *
     * */
    public static int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //방향배열 - 상, 하, 좌, 우
    public static int M;
    public static int N;
    public static int K;
    public static boolean[][] visited;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            visited = new boolean[M][N];
            map = new int[M][N];

            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    map[j][k] = 0; //map 초기화
                }
            }
            for (int j = 0; j < K; j++) {
                st  = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1; //배추 위치 기록
            }

            int cnt = 0;
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] == 1) {
                        bfs(new int[]{j, k});
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    public static void bfs(int[] first) {
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(first); // Queue에 현재 노드 넣기
        visited[first[0]][first[1]] = true; // 방문 기록

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int[] current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문 - 상, 하, 좌, 우 모두 방문
            for (int i = 0; i<4; i++) {

                int[] next = {current[0] + direction[i][0], current[1] + direction[i][1]};

                if (next[0] >= 0 && next[1] >= 0 && next[0] < M && next[1] < N) {
                    if( map[next[0]][next[1]] == 1 && !visited[next[0]][next[1]] ) { //갈 수 있는 길인지 & 방문 했던 길인지 검사
                        queue.add(next);
                        map[next[0]][next[1]] = 2; // 지나간 길은 2로 저장
                        visited[next[0]][next[1]] = true; //방문 기록
                    }
                }
            }
        }
    }

}



