import java.util.ArrayList;
import java.util.List;

public class Tree {
	int val;
	List<Tree> tree_children;

	public Tree(int value, List<Tree> children) {
		val = value;
		tree_children = children;
	}

	public int longestPath() {
		int max = 0;
		if (this == null)
			return 1;
		for (Tree t : tree_children) {
			int len = 0;
			len += t.longestPath();
			max = Math.max(max, len);
		}
		return max;

	}

	public static void main(String[] args) {
		List<Tree> s = new ArrayList<>();

		Tree t = new Tree(12, s);
		System.out.println(t.longestPath());
	}

}