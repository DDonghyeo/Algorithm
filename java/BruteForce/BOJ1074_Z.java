import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1074_Z {

    /**
     * <a href= "https://www.acmicpc.net/problem/1074"> 링크 </a>
     * <h1>문제</h1>
     * <p>
     * 한수는 크기가 2N × 2N인 2차원 배열을 Z모양으로 탐색하려고 한다. 예를 들어, 2×2배열을 왼쪽 위칸, 오른쪽 위칸, 왼쪽 아래칸, 오른쪽 아래칸 순서대로 방문하면 Z모양이다.
     * <p>
     * <p>
     * <p>
     * N > 1인 경우, 배열을 크기가 2N-1 × 2N-1로 4등분 한 후에 재귀적으로 순서대로 방문한다.
     * <p>
     * 다음 예는 22 × 22 크기의 배열을 방문한 순서이다.
     * <p>
     * <p>
     * <p>
     * N이 주어졌을 때, r행 c열을 몇 번째로 방문하는지 출력하는 프로그램을 작성하시오.
     * <p>
     * 다음은 N=3일 때의 예이다.
     *
     * <img src="https://u.acmicpc.net/d3e84bb7-9424-4764-ad3a-811e7fcbd53f/Screen%20Shot%202020-12-30%20at%2010.50.47%20PM.png">
     *
     * <br><br>
     *
     * <h1>입력</h1>
     * <p>
     * 첫째 줄에 정수 N, r, c가 주어진다.
     *
     * <br><br>
     * <h1>출력</h1>
     * <p>
     * r행 c열을 몇 번째로 방문했는지 출력한다.
     */
    static int[][] arr;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n * 2][n * 2];

        int size = (int) Math.pow(2, n);

        Z(size, r, c);

        System.out.println(cnt);

    }

    public static void Z(int size, int r, int c) {
        if (size == 1) { //size가 1일 경우
            return;
        }

        if (r < size / 2 && c < size / 2) { //1사분면에 있을 경우
            Z(size / 2, r, c); //size를 1/2로 줄이고 재귀함수 호출
        }
        else if (r < size/2 && c >= size/2) { //2사분면에 있을 경우
            cnt += size * size / 4; //2사분면은 2번째 방문이기 때문에 1사분면을 모두 지나고 옴 -> size^2의 1/4
            Z(size / 2, r, c - size / 2);
        }
        else if(r >= size/2 && c < size/2) { //3사분면에 있을 경우
            cnt += (size * size / 4) * 2; //3사분면은 3번째 방문이기 때문에 1, 2사분면을 지나고 옴.
            Z(size/2, r - size/2, c);
        }
        else { //4사분면에 있을 경우
            cnt += (size * size / 4) * 3; //4사분면은 4번째 방문이기 때문에 1, 2, 3 사분면을 지나고 옴.
            Z(size/2, r - size/2, c - size/2);
        }
    }
}
