package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2178 {

    /**
     * N×M크기의 배열로 표현되는 미로가 있다. <br>
     *
     * 1	0	1	1	1	1 <br>
     * 1	0	1	0	1	0 <br>
     * 1	0	1	0	1	1 <br>
     * 1	1	1	0	1	1  <br>
     * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. <br>
     * 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. <br>
     * 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.<br>
     *<br>
     * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.<br>
     * */
    public static int[][] edges; //간선 기록을 위한 인접 행렬

    public static Set<Integer> visited = new HashSet<>(); //방문한 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //N = 정점의 개수
        int n = Integer.parseInt(br.readLine());
        //M = 간선의 개수
        int m = Integer.parseInt(br.readLine());

        edges = new int[n+1][n+1];

        StringTokenizer st;

        for (int i = 0; i < m; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a][b] = edges[b][a] = 1; // (a,b) 와 (b,a) 모두 기록
        }

        bfs(1);

        System.out.println(visited.size()-1);

    }

    public static void bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source); // Queue에 현재 노드 넣기

        visited.add(source); // 방문 기록

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int current = queue.poll(); // Queue에서 현재 값 꺼내기

            //인접한 노드들 모두 방문
            for (int i = 0; i<edges[current].length; i++) {
                if (edges[current][i] == 1 && !visited.contains(i)) { // 인접 노드 중, 방문하지 않은 노드에 대해
                    queue.offer(i); // 해당 노드 Queue에 넣기
                    visited.add(i); //방문 기록
                }
            }
        }
    }

}
