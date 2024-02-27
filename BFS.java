import java.util.*;

public class BFS {

    public BFS() {
        graph = new HashMap<>();
    }


    //인접 리스트
    private final Map<Integer, List<Integer>> graph;

    //정점 추가
    public void addVertex(int vertex) {
        graph.put(vertex, new ArrayList<>());
    }

    //간선 추가
    public void addEdge(int source, int destination) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }

        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }

        graph.get(source).add(destination);
    }

    //Queue를 이용하여 BFS 메서드 작성
    //BFS 탐색
    public void bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();

        //방문한 리스트
        Set<Integer> visited = new HashSet<>();

        queue.offer(source);
        visited.add(source); //방문 기록

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.printf(current + " ");

            for (Integer neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

}
