import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1920 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1920"> 링크 </a>
     * <h1>문제</h1>
     *
     * N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 이분 탐색 사용하기 위해 정렬

        int m = Integer.parseInt(br.readLine());

        st  = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            if (binarySearch(arr, target) >= 0) {
                System.out.println("1");
            }else System.out.println("0");
        }

    }

    public static int binarySearch(int[] arr, int key) {

        int low = 0;					// 가장 처음 인덱스
        int high = arr.length - 1;	// 가장 끝 인덱스

        // low가 high보다 커지기 전까지 반복
        while(low <= high) {
            int mid = (low + high) / 2;	// 중간위치
            // key가 중간보다 작을 경우
            if(key < arr[mid]) {
                high = mid - 1;
            }
            // key가 중간보다 클 경우
            else if(key > arr[mid]) {
                low = mid + 1;
            }
            // key가 중간과 같을 경우
            else {
                return mid;
            }
        }
        // 존재하지 않을 경우
        return -1;

    }
}
