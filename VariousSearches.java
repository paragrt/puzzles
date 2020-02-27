package algorithms;
/*
LoopCount = 5 for BinSrch Found tgt1=1 at index position:0
LoopCount = 3 for BinSrch Found tgt2=1000 at index position:26
LoopCount = 1 for ExpSrch Found tgt1=1 at index position:0
LoopCount = 2 for ExpSrch Found tgt2=1000 at index position:26

*/
public class VariousSearches {

	static int loopcount = 0;
	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 7, 11, 12, 15, 17, 20, 25, 26, 30, 45, 67, 89, 101, 200, 300, 400, 500,
				600, 700, 800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000 };
		for (int i = 0; i < arr.length; i++) {
			System.out.println(i + ")=" + arr[i]);
		}
		int tgt1 = 1;
		int tgt2 = 1000;
		loopcount = 0;
		System.out.println("BinSrch Found tgt1=" + tgt1 + " at index position:" + binarySearch(arr, tgt1));
		loopcount = 0;
		System.out.println("BinSrch Found tgt2=" + tgt2 + " at index position:" + binarySearch(arr, tgt2));
		loopcount = 0;
		System.out.println("ExpSrch Found tgt1=" + tgt1 + " at index position:" + ExpSearch(arr, tgt1));
		loopcount = 0;
		System.out.println("ExpSrch Found tgt2=" + tgt2 + " at index position:" + ExpSearch(arr, tgt2));
	}

	static int binarySearch(int[] arr, int tgt) {
		return binarySearch(arr, tgt, 0, arr.length);
	}

	static int binarySearch(int[] arr, int tgt, int left, int right) {
		int ans = -1;
		while (left < right) {

			int middle = (left + right) / 2;
			if (arr[middle] == tgt) {
				ans = middle;
				break;
			}
			if (arr[middle] < tgt) {
				left = middle;
			} else {
				right = middle;
			}
			loopcount++;
		}
		System.out.print("LoopCount = " + loopcount + " for ");
		return ans;
	}

	static int ExpSearch(int[] arr, int tgt) {
		int bound = 1;
		while (bound < arr.length && arr[bound] < tgt) {
			bound *= 2;
		}

		return binarySearch(arr, tgt, bound / 2, Math.min(bound + 1, arr.length));
	}

}
