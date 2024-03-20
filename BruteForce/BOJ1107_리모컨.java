import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1107_리모컨 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1107"> 링크 </a>
     * <h1>문제</h1>
     *
     * 수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼이 고장났다.
     *
     * 리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.
     *
     * 수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 고장났는지 주어졌을 때, 채널 N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오.
     *
     * 수빈이가 지금 보고 있는 채널은 100번이다.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 고장난 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 고장난 버튼이 있는 경우에는 셋째 줄에는 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * 첫째 줄에 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int target = Integer.parseInt(br.readLine());

        int num = Integer.parseInt(br.readLine());

        ArrayList<String> disabled = new ArrayList<>();

        if (num != 0) {
            StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < num; i++) {
                disabled.add(st.nextToken()) ;
            }
        }

        boolean minusDone = false;
        boolean plusDone = false;

        if (disabled.size() == 9 && !disabled.contains("0")) {
            //0을 제외하고 1~9 모두 고장난 경우
            if (0 < target) {
                //타겟이 0보다 큰 경우 -> plus 방향은 안 해도 됨
                plusDone = true;
            } else {
                //타겟이 0보다 작은 경우 -> minus 방향은 안 해도 됨
                minusDone = true;
            }
        }

        //100에서 +, -만 눌러서 가는 경우
        int fromHundred = target - 100;
        int totalFromHundred = Math.abs(fromHundred);
//        System.out.println("100에서 가는 경우 차이 : " + fromHundred);
//        System.out.println("100에서 가는 경우 버튼 누르는 횟수 : " + totalFromHundred);

        // target 으로 부터 시작하는 경우
        int current = target;
        // - 방향
        while (!minusDone) {
            String currentStr = String.valueOf(current);
            boolean isPossible = true;
            for (int i = 0; i < currentStr.length(); i++) {
                if (disabled.contains(String.valueOf(currentStr.charAt(i)))) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                minusDone = true;
            } else current--;
        }
        int totalFromTargetMinusSide = String.valueOf(current).length() + Math.abs(target - current);


        // + 방향
        current = target;
        while (!plusDone) {
            String currentStr = String.valueOf(current);
            boolean isPossible = true;
            for (int i = 0; i < currentStr.length(); i++) {
                if (disabled.contains(String.valueOf(currentStr.charAt(i)))) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                plusDone = true;
            } else current++;
        }
        int totalFromTargetPlusSide = String.valueOf(current).length() + Math.abs(target - current);

        if (disabled.size() == 9 && !disabled.contains("0")) {
            //0을 제외하고 1~9 모두 고장난 경우
            if (0 < target) {
                //타겟이 0보다 큰 경우 -> plus 방향은 안 해도 됨
                totalFromTargetPlusSide = Integer.MAX_VALUE;
            } else {
                //타겟이 0보다 작은 경우 -> minus 방향은 안 해도 됨
                totalFromTargetMinusSide = Integer.MAX_VALUE;
            }
        }

//        System.out.println("타겟부터 시작하는 경우 시작하는 숫자(타겟과 가장 가까운) - 방향 : " + fromTargetMinusSideMin);
//        System.out.println("타겟부터 시작하는 경우 시작하는 숫자(타겟과 가장 가까운) + 방향: " + fromTargetPlusSideMin);

        int totalFromTargetSideMin = Integer.min(totalFromTargetMinusSide, totalFromTargetPlusSide);

        System.out.println(Integer.min(totalFromTargetSideMin, totalFromHundred));

    }
}
