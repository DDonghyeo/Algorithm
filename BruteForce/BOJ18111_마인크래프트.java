import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111_마인크래프트 {

    /**
     * <a href= "https://www.acmicpc.net/problem/18111"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 팀 레드시프트는 대회 준비를 하다가 지루해져서 샌드박스 게임인 ‘마인크래프트’를 켰다. 마인크래프트는 1 × 1 × 1(세로, 가로, 높이) 크기의 블록들로 이루어진 3차원 세계에서 자유롭게 땅을 파거나 집을 지을 수 있는 게임이다.
     * <p>
     * 목재를 충분히 모은 lvalue는 집을 짓기로 하였다. 하지만 고르지 않은 땅에는 집을 지을 수 없기 때문에 땅의 높이를 모두 동일하게 만드는 ‘땅 고르기’ 작업을 해야 한다.
     * <p>
     * lvalue는 세로 N, 가로 M 크기의 집터를 골랐다. 집터 맨 왼쪽 위의 좌표는 (0, 0)이다. 우리의 목적은 이 집터 내의 땅의 높이를 일정하게 바꾸는 것이다. 우리는 다음과 같은 두 종류의 작업을 할 수 있다.
     * <p>
     * 1. 좌표 (i, j)의 가장 위에 있는 블록을 제거하여 인벤토리에 넣는다.
     * 2. 인벤토리에서 블록 하나를 꺼내어 좌표 (i, j)의 가장 위에 있는 블록 위에 놓는다.
     * 1번 작업은 2초가 걸리며, 2번 작업은 1초가 걸린다. 밤에는 무서운 몬스터들이 나오기 때문에 최대한 빨리 땅 고르기 작업을 마쳐야 한다. ‘땅 고르기’ 작업에 걸리는 최소 시간과 그 경우 땅의 높이를 출력하시오.
     * <p>
     * 단, 집터 아래에 동굴 등 빈 공간은 존재하지 않으며, 집터 바깥에서 블록을 가져올 수 없다. 또한, 작업을 시작할 때 인벤토리에는 B개의 블록이 들어 있다. 땅의 높이는 256블록을 초과할 수 없으며, 음수가 될 수 없다.
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 N, M, B가 주어진다. (1 ≤ M, N ≤ 500, 0 ≤ B ≤ 6.4 × 107)
     * <p>
     * 둘째 줄부터 N개의 줄에 각각 M개의 정수로 땅의 높이가 주어진다. (i + 2)번째 줄의 (j + 1)번째 수는 좌표 (i, j)에서의 땅의 높이를 나타낸다. 땅의 높이는 256보다 작거나 같은 자연수 또는 0이다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * 첫째 줄에 땅을 고르는 데 걸리는 시간과 땅의 높이를 출력하시오. 답이 여러 개 있다면 그중에서 땅의 높이가 가장 높은 것을 출력하시오.
     */

    public static int n;
    public static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());


        int[][] land = new int[n][m];

        int blocks = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int block = Integer.parseInt(st.nextToken());
                land[i][j] = block;
                blocks += block;
            }
        }





        // 후보 추출
        int average = blocks / (n * m);
        blocks += b; //땅에 있는 블럭 + 갖고 있는 블럭 모두 합하기

        // maxHeight & minHeight 정하기
        int maxHeight = blocks / (n * m); //땅에 있는 블럭과 갖고 있는 블럭으로 쌓을 수 있는 최대 높이
        if (maxHeight > 256) { //최대 높이는 256
            maxHeight = 256;
        }
        int minHeight = 0;


        //평균으로부터 위로 쌓는 방향 검사
        int minTimeUpSide = Integer.MAX_VALUE;
        int currentHeightUpside = average; //평균부터 시작
        boolean isDone = false;
        while (!isDone) {
            if (currentHeightUpside > maxHeight) { //최대 높이를 넘었는지 검사
                break;
            }

            int currentTime = calTime(land, currentHeightUpside);  //걸리는 시간 검사
            if (currentTime <= minTimeUpSide) { //만약 걸린 시간이 더 작다면
                minTimeUpSide = currentTime; //minTime에 저장
                currentHeightUpside++; //다음 높이 (위 방향)
            } else {
                isDone = true;
            }
        }
        currentHeightUpside--; // 높이 복구

        //평균으로부터 아래로 쌓는 방향 검사
        int minTimeDownSide = Integer.MAX_VALUE;
        int currentHeightDownside = average; //평균 부터 시작
        isDone = false;
        while (!isDone) {
            if (currentHeightDownside < minHeight) { //최소 높이를 넘었는지 검사
                break;
            }

            int currentTime = calTime(land, currentHeightDownside); //걸리는 시간 검사
            if (currentTime < minTimeDownSide) { //만약 걸린 시간이 더 작다면
                minTimeDownSide = currentTime; //minTime에 저장
                currentHeightDownside--; //다음 높이 (아래 방향)
            } else{
                isDone = true;
            }
        }
        currentHeightDownside++; //높이 복구

        if (minTimeUpSide <= minTimeDownSide) { //윗 방향의 시간이 더 짧다면 (=을 넣어서 더 높은 높이가 출력됨)
            System.out.println(minTimeUpSide + " " + currentHeightUpside );
        }else System.out.println(minTimeDownSide+ " " + currentHeightDownside); //아랫 방향의 시간이 더 짧다면
    }

    public static int calTime(int[][] land ,int height) {
        int time = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int diff = height - land[i][j];
                if ( diff >= 0) {
                    time += diff;
                } else time += -diff * 2;
            }
        }
        return time;
    }
}
