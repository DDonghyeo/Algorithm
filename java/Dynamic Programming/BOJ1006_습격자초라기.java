import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1006_습격자초라기 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1006"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 초라기는 한국의 비밀국방기지(원타곤)를 습격하라는 임무를 받은 특급요원이다. 원타곤의 건물은 도넛 형태이며, 초라기는 효율적인 타격 포인트를 정하기 위해 구역을 아래와 같이 두 개의 원 모양으로 나누었다. (그림의 숫자는 각 구역의 번호이다.)
     *
     * <img src= "https://www.acmicpc.net/upload/201003/dfck3232_34g7t9f4gp_b.jpg">
     * <p>
     * 초라기는 각각 W명으로 구성된 특수소대를 다수 출동시켜 모든 구역에 침투시킬 예정이며, 각 구역 별로 적이 몇 명씩 배치되어 있는지는 초라기가 모두 알고 있다. 특수소대를 아래 조건에 따라 침투 시킬 수 있다.
     * <p>
     * 한 특수소대는 침투한 구역 외에, 인접한 한 구역 더 침투할 수 있다. (같은 경계를 공유하고 있으면 인접 하다고 한다. 위 그림에서 1구역은 2, 8, 9 구역과 서로 인접한 상태다.) 즉, 한 특수소대는 한 개 혹은 두 개의 구역을 커버할 수 있다.
     * 특수소대끼리는 아군인지 적인지 구분을 못 하기 때문에, 각 구역은 하나의 소대로만 커버해야 한다.
     * 한 특수소대가 커버하는 구역의 적들의 합은 특수소대원 수 W 보다 작거나 같아야 한다.
     * 이때 초라기는 원타곤의 모든 구역을 커버하기 위해 침투 시켜야 할 특수 소대의 최소 개수를 알고 싶어 한다.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 다음과 같이 구성되어있다.
     * <p>
     * 첫째 줄에는 (구역의 개수)/2 값 N과 특수 소대원의 수 W가 주어진다. (1 ≤ N ≤ 10000, 1 ≤ W ≤ 10000).
     * <p>
     * 둘째 줄에는 1~N번째 구역에 배치된 적의 수가 주어지고, 셋째 줄에는 N+1 ~ 2N번째 구역에 배치된 적의 수가 공백으로 구분되어 주어진다. (1 ≤ 각 구역에 배치된 최대 적의 수 ≤ 10000) 단, 한 구역에서 특수 소대원의 수보다 많은 적이 배치된 구역은 존재하지 않는다. (따라서, 각 구역에 배치된 최대 적의 수 ≤ W)
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 각 테스트케이스에 대해서 한 줄에 하나씩 원타곤의 모든 구역을 커버하기 위해 침투 시켜야 할 특수 소대의 최소 개수를 출력하시오.
     */
    static int min = Integer.MAX_VALUE;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {

            StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken()); //구역의 개수
            int w = Integer.parseInt(st.nextToken()); //소대원의 수

            int[][] arr = new int[2][n]; //비밀국방기지(원타곤)

            for (int j = 0; j < 2; j++) {
                st  = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < n; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }


            List<int[]> possibles = new ArrayList<>(); //다른 구역과 묶어서 커버할 수 있는 구역 리스트
            int[][] cover = new int[2][n];

            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0; l < 4; l++) {
                        int nextX = k + dx[l];
                        int nextY = j + dy[l];
                        if (nextY == -1) {
                            nextY = n;
                        }

                        if ( nextX >=0 &&nextX <= 1 && nextY <= n-1) {
                            if (arr[k][j] + arr[nextX][nextY] < w) {
                                cover[k][j] = nextX*n + nextY;
                                possibles.add(new int[]{k * n + j, nextX * n + nextY });
                            }
                        }

                    }
                }
            }

            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < n; j++) {
                    if () {

                    }
                }
            }


            System.out.println("Done");

















        }




    }
}
