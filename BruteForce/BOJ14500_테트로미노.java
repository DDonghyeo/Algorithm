import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

    /**
     * <a href= "https://www.acmicpc.net/problem/14500"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 폴리오미노란 크기가 1×1인 정사각형을 여러 개 이어서 붙인 도형이며, 다음과 같은 조건을 만족해야 한다.
     * <p>
     * 정사각형은 서로 겹치면 안 된다.
     * 도형은 모두 연결되어 있어야 한다.
     * 정사각형의 변끼리 연결되어 있어야 한다. 즉, 꼭짓점과 꼭짓점만 맞닿아 있으면 안 된다.
     * 정사각형 4개를 이어 붙인 폴리오미노는 테트로미노라고 하며, 다음과 같은 5가지가 있다.
     *
     * <img src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/14500/1.png">
     * <p>
     * 아름이는 크기가 N×M인 종이 위에 테트로미노 하나를 놓으려고 한다. 종이는 1×1 크기의 칸으로 나누어져 있으며, 각각의 칸에는 정수가 하나 쓰여 있다.
     * <p>
     * 테트로미노 하나를 적절히 놓아서 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오.
     * <p>
     * 테트로미노는 반드시 한 정사각형이 정확히 하나의 칸을 포함하도록 놓아야 하며, 회전이나 대칭을 시켜도 된다.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
     * <p>
     * 둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
     */
    static int[][] map;
    static int max = 0;
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //보라색(ㅗ)모양을 제외하면, 다른 블록으로 4칸으로 갈 수 있는 모든 경로를 표현할 수 있다.
                //4칸만 가는 dfs + ㅗ모양을 구현하면 된다.
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }
        System.out.println(max);

    }

    public static void dfs(int j, int k, int count, int value) {
        if (count == 4) { // 4칸을 모두 갔을 경우 Max 계산
            if (max < value) {
                max = value;
            }
            return;
        }

        for (int i = 0; i < 4; i++) { //4방향 탐색
            int x = j + dx[i];
            int y = k + dy[i];
            if (x < n && x >= 0 && y < m && y >= 0) { //범위 검사
                if ( !visited[x][y]) { // 방문했는지

                    // 보라색(ㅗ) 테트로미노 --> 2번째 칸에서 탐색 한번 더 진행
                    if(count == 2) {
                        visited[x][y] = true; //방문기록
                        dfs(j, k, count + 1, value + map[x][y]);
                        visited[x][y] = false; //다음 탐색을 위해 방문 기록 초기화
                    }

                    visited[x][y] = true; //방문기록
                    dfs(x, y, count+1, value + map[x][y]);
                    visited[x][y] = false; //다음 탐색을 위해 방문 기록 초기화

                }
            }

        }

    }


}
