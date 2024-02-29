package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2606 {

    /**
     * 신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. <br>
     * 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다. <br>
     * 예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. <br>
     * 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. <br>
     * 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다. <br>
     * 어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. <br>
     * 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오. <br>
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
