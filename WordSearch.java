import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

class Dictionary {
	Collection<String> words;

	public Dictionary(Collection<String> words) {
		this.words = new ArrayList<String>(words);
	}

//	public boolean isWord(String word) {
//		// Implement
//		return false;
//	}
//
//	public boolean isPrefix(String prefix) {
//		// Implement
//		return false;
//	}

}

class Cell {
	int row;
	int column;

	Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}
}

public class WordSearch {

	public static boolean exist(char[][] board, String word) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == word.charAt(0) && dfs(i, j, 0, board, word))
					return true;
			}
		}
		return false;
	}

	public static boolean dfs(int i, int j, int count, char[][] board, String word) {
		if (count == word.length())
			return true;
		char temp = board[i][j];
		board[i][j] = ' ';
		boolean found = false;
		for (Cell neighbour : getNeighbours(i, j, board.length, board[0].length)) {
			if (temp == word.charAt(count)) {
				found |= dfs(neighbour.row, neighbour.column, count + 1, board, word);
			}
		}
		board[i][j] = temp;
		return found;
	}

	static HashSet<Cell> getNeighbours(int row, int column, int maxRow, int maxColumn) {
		HashSet<Cell> neighbours = new HashSet<>(8);
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (isValidCell(row + i, column + j, row, column, maxRow, maxColumn)) {
					neighbours.add(new Cell(row + i, column + j));
				}
			}
		}
		return neighbours;
	}

	private static boolean isValidCell(int i, int j, int row, int column, int maxRow, int maxColumn) {
		if (i < 0 || i >= maxRow || j < 0 || j >= maxColumn)
			return false;
		return true;
	}

	public static HashSet<String> findWords(char[][] board, Dictionary dictionary) {
		HashSet<String> st = new HashSet<String>();
		ArrayList<String> words = (ArrayList<String>) dictionary.words;

		for (int i = 0; i < words.size(); i++) {
			if (exist(board, words.get(i))) {
				st.add(words.get(i));
			}
		}
		return st;
	}

	public static void main(String[] args) {

		char[][] grid = { { 'a', 'a', 'r' }, { 't', 'c', 'd' }, };
		Dictionary dictionary = new Dictionary(Arrays.asList("car", "card", "cart", "cat"));

		HashSet<String> hs = findWords(grid, dictionary);

		System.out.println(hs.equals(new HashSet<String>(Arrays.asList("car", "card", "cat"))));
		Iterator itr = hs.iterator();
		  
        // check element is present or not. if not loop will
        // break.
        while (itr.hasNext()) {
            System.out.println(itr.next());
	}
	}
}