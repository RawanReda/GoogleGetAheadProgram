import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

// Data structure for the result.
class Coord {
	int y = 0;
	int x = 0;
};

class Rect {
	Coord topLeft = new Coord();
	Coord bottomRight = new Coord();
};

class Result {
	int sum;
	Rect rectangle = new Rect();
};

public class RectangleSums {

	public static Result findLargestSum(int[][] input) {

		ArrayList<ArrayList<Result>> sum_arr = new ArrayList<ArrayList<Result>>();
		for (int i = 0; i < input.length; i++) {
			ArrayList<Result> arr = new ArrayList<Result>();
			for (int j = 0; j < input[i].length; j++) {
				int sum1 = 0;
				Result Res = new Result();
				Rect rectangle1 = new Rect();
				if (i > 0 && j > 0 && j < input[i - 1].length) {
					sum1 = input[i][j] + sum_arr.get(i - 1).get(j).sum + arr.get(j - 1).sum
							- sum_arr.get(i - 1).get(j - 1).sum;
					Coord l = sum_arr.get(i - 1).get(j - 1).rectangle.topLeft;
					Coord r = new Coord();
					r.x = j;
					r.y = i;
					rectangle1.topLeft = l;
					rectangle1.bottomRight = r;
					Res.sum = sum1;
					Res.rectangle = rectangle1;
					arr.add(Res);
				} else if (i == 0 && j > 0) {
					sum1 = input[i][j] + arr.get(j - 1).sum;

					Coord l = new Coord();
					l.x = 0;
					l.y = 0;
					Coord r = new Coord();
					r.x = j;
					r.y = i;
					rectangle1.topLeft = l;
					rectangle1.bottomRight = r;
					Res.sum = sum1;
					Res.rectangle = rectangle1;
					arr.add(Res);

				} else if (i > 0 && j == 0 && input[i-1].length>0) {
					sum1 = input[i][j] + sum_arr.get(i - 1).get(j).sum;
					Coord l = new Coord();
					l.x = 0;
					l.y = 0;
					Coord r = new Coord();
					r.x = j;
					r.y = i;
					rectangle1.topLeft = l;
					rectangle1.bottomRight = r;
					Res.sum = sum1;
					Res.rectangle = rectangle1;
					arr.add(Res);

				} else if (i > 0 && j >= input[i - 1].length) {
					for (int k = 0; k <= j; k++) {
						sum1 += input[i][k];
					}

					Coord l = new Coord();
					l.x = 0;
					l.y = i;
					Coord r = new Coord();
					r.x = j;
					r.y = i;
					rectangle1.topLeft = l;
					rectangle1.bottomRight = r;
					Res.sum = sum1;
					Res.rectangle = rectangle1;
					arr.add(Res);
				} else {
					sum1 = input[i][j];
					Coord l = new Coord();
					l.x = 0;
					l.y = 0;
					Coord r = new Coord();
					r.x = j;
					r.y = i;
					rectangle1.topLeft = l;
					rectangle1.bottomRight = r;
					Res.sum = sum1;
					Res.rectangle = rectangle1;
					arr.add(Res);
				}

			}
			sum_arr.add(arr);
		}

		Result max = new Result();


		for (int i = 0; i < sum_arr.size(); i++) {
			for (int j = 0; j < sum_arr.get(i).size(); j++) {
				Rect r = sum_arr.get(i).get(j).rectangle;
				if ((sum_arr.get(i).get(j).sum) > max.sum) {

					max = sum_arr.get(i).get(j);

				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		Result r = findLargestSum(new int[][] {
		      {1, 3, 2, 2},
		      {2, 1, 2, 3},
		      {},
		      {1, 1, 2, 17, 14},
		      {3, 1, 2, 2},
		    });
		System.out.println(r.sum);
		System.out.println((r.rectangle).topLeft.x);
		System.out.println((r.rectangle).topLeft.y);
		System.out.println((r.rectangle).bottomRight.x);
		System.out.println((r.rectangle).bottomRight.y);

	}

//	@Test
//	public void testEquals() {
//		Result result = new Result();
//		result.sum = 35;
//		result.rectangle.topLeft.x = 0;
//		result.rectangle.topLeft.y = 3;
//		result.rectangle.bottomRight.x = 4;
//		result.rectangle.bottomRight.y = 3;
//		assertEquals(result, findLargestSum(new int[][]{{1, 2},{3, 4, 5, 6},{7, 8},  {9}}));
//
//	}
}