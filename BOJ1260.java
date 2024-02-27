import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//DFS와 BFS
public class BOJ1260 {

    public static int[][] edges; //간선 기록을 위한 인접 행렬

    public static Set<Integer> visited = new HashSet<>(); //방문한 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine(), " ");

        //N = 정점의 개수
        int n = Integer.parseInt(st.nextToken());
        //M = 간선의 개수
        int m = Integer.parseInt(st.nextToken());
        //V = 탐색을 시작할 정점의 번호
        int v = Integer.parseInt(st.nextToken());

        edges = new int[n+1][n+1];

        for (int i = 0; i < m; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a][b] = edges[b][a] = 1; // (a,b) 와 (b,a) 모두 기록
        }

        dfs(v);
        visited.clear();
        System.out.println();
        bfs(v);

        Stack<Integer> stack = new Stack<>();
        stack.pop()

    }

    public static void dfs(int source) {
        System.out.printf(source + " ");
        visited.add(source); // 노드 방문 기록

        for (int i = 0; i < edges[source].length; i++) {
            if (edges[source][i] == 1 && !visited.contains(i)) { // 만약 해당 노드를 방문한 적이 없다면
                dfs(i); // 해당 노드 방문
            }
        }
    }

    public static void bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(source); // Queue에 현재 노드 넣기

        visited.add(source); // 방문 기록

        while (!queue.isEmpty()) { // Queue가 빌 때까지 반복
            int current = queue.poll(); // Queue에서 현재 값 꺼내기
            System.out.printf(current + " ");

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
