import java.util.*;

public class GraphImplementation implements Graph{
	private int[][] adjacency_matrix;
	public GraphImplementation(int vertices){
		adjacency_matrix = new int[vertices][vertices];
	}

	public void addEdge(int v1, int v2) throws Exception{
		if(v1<0 || v2<0 || v1>=adjacency_matrix.length||v2>=adjacency_matrix.length)
			throw new Exception("V1 or V2 are out of bounds");
		adjacency_matrix[v1][v2] = 1;
	}

	public List<Integer> neighbors(int vertex) throws Exception{
		if(vertex<0 || vertex>=adjacency_matrix.length)
			throw new Exception("Vertex is out of bounds");
		List<Integer> list = new LinkedList<Integer>();
		for(int i=0; i<adjacency_matrix[vertex].length; i++){
			if(adjacency_matrix[vertex][i] == 1)
				list.add(i);
		}

		return list;
	}

	public int findzero(int[] a){
		for(int i=0; i<a.length; i++){
			if(a[i] == 0)
				return i;
		}
		return -1;
	}

	public List<Integer> topologicalSort(){
		List<Integer> list = new LinkedList<Integer>();

		int[] sum = new int[adjacency_matrix.length];
		for(int i=0; i<adjacency_matrix.length; i++)
			for(int j=0; j<adjacency_matrix[i].length; j++)
				sum[i] += adjacency_matrix[j][i];

		for(int count=0; count<adjacency_matrix.length; count++){
			int next = findzero(sum);
			list.add(next);
			sum[next] = -1;
			for(int i=0; i<sum.length; i++)
				sum[i] -= adjacency_matrix[next][i];
		}

		return list;
	}
}