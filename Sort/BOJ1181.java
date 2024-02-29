import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1181 {

    /**
     * <a href= "https://www.acmicpc.net/problem/1181"> 링크 </a>
     * <h1>문제</h1>
     *
     * 알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.
     *
     * 1. 길이가 짧은 것부터
     * 2. 길이가 같으면 사전 순으로
     * 단, 중복된 단어는 하나만 남기고 제거해야 한다.
     *
     *<br><br>
     *
     * <h1>입력</h1>
     *
     * 13 <br>
     * but<br>
     * i<br>
     * wont<br>
     * hesitate<br>
     * no<br>
     * more<br>
     * no<br>
     * more<br>
     * it<br>
     * cannot<br>
     * wait<br>
     * im<br>
     * yours<br>
     *
     * 첫째 줄에 단어의 개수 N이 주어진다. (1 ≤ N ≤ 20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.
     *
     *<br><br>
     * <h1>출력</h1>
     *
     * i<br>
     * im<br>
     * it<br>
     * no<br>
     * but<br>
     * more<br>
     * wait<br>
     * wont<br>
     * yours<br>
     * cannot<br>
     * hesitate<br>
     *
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() == o2.length()) { //만약 길이가 같다면
                return o1.compareTo(o2); //compareTo 메서드 : 사전순 정렬 메서드
            } else return o1.length() - o2.length(); //길이가 같지 않다면 : 양수를 반환할 경우 순서가 바뀜
        });

        System.out.println(words[0]);
        for (int i =1; i<n; i++) {
            if(!words[i-1].equals(words[i]))
                System.out.println(words[i]);
        }
    }
}
