public class CountingIslands {

	static boolean[][] visited;

	public static int countIslands(boolean[][] grid) {
		if (grid.length == 0)
			return 0;
		visited = new boolean[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] && !visited[i][j]) {
					dfs(i, j, grid);
					count++;
				}
			}
		}
		return count;
	}

	public static void dfs(int i, int j, boolean[][] grid) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || !grid[i][j] || visited[i][j])
			return;
		visited[i][j] = true;
		dfs(i + 1, j, grid);
		dfs(i - 1, j, grid);
		dfs(i, j - 1, grid);
		dfs(i, j + 1, grid);
	}

	public static void main(String[] args) {
		System.out.println(countIslands(new boolean[][] {}));
	}

}