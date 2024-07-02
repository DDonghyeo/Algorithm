import java.io.*;
import java.util.*;

public class Temp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<String> noListen = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            noListen.add(br.readLine());
        }

        String[] result = new String[500000];

        int resultCnt = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (noListen.contains(str)) {
                result[resultCnt] = str;
                resultCnt++;
            }
        }

        Arrays.sort(result, (o1, o2) -> {
            if (o1.length() == o2.length()) { //만약 길이가 같다면
                return o1.compareTo(o2); //compareTo 메서드 : 사전순 정렬 메서드
            } else return o1.length() - o2.length(); //길이가 같지 않다면 : 양수를 반환할 경우 순서가 바뀜
        });

        System.out.println(resultCnt);
        for (int i = 0; i < resultCnt; i++) {
            System.out.println(result[i]);
        }
    }
}
