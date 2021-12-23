import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * 
 * @author paragrt Longest increasing subsequence length
 */
public class LIS {

	public static void main(String[] args) {
		int[][] arrOfarr = { { 10, 22, 3, 33, 21, 50, 41, 60, 4, 5, 6, 7 }, // 2 LIS [10, 22, 33, 41, 60], [3, 4, 5, 6,
																			// 7]
				// { 7,10,5,4,3},
				// {50, 3, 10, 7, 40, 80,4,5,6,8}
				// { 4, 6, 10, 3, 4, 5, 1 }
		};
		// for(int[] arr: arrOfarr) System.out.println(lengthOfLIS(arr));
		for (int[] arr : arrOfarr)
			System.out.println(lis_dp(arr));

	}

	public static int lis_dp(int[] nums) {
		Integer[] dp = new Integer[nums.length];
		Arrays.fill(dp, 1);

		for (int curr = 1; curr < nums.length; curr++) {
			for (int j = 0; j < curr; j++) {
				if (nums[curr] > nums[j] && dp[curr] < dp[j] + 1) {
					dp[curr] = dp[j] + 1;
				}
			}
		}
		// return max
		int max = Collections.max(Arrays.asList(dp));

		List<Integer> maxIndices = new ArrayList<Integer>();
		for (int i = 0; i < dp.length; i++) {
			if (max == dp[i])
				maxIndices.add(i);
		}
		List<Integer> stack = new ArrayList<Integer>();
		//for (Integer startAt : maxIndices) {//if u only want max LISs
		for (int startAt = dp.length-1; startAt >= 0; startAt--) { // if u want all LISs
			
			int tmp = dp[startAt];
			for (int i = startAt; i >= 0; i--) {
				if (dp[i] == tmp) {
					stack.add(0, nums[i]);
					tmp--;
				}
			}
			if (stack.size()>0) System.out.println(stack);
			stack.clear();
		}
		return max;

	}

	// This works for length ... but does not record the LIS itself
	public static int lengthOfLIS(int[] nums) {

		TreeSet<Integer> s = new TreeSet<>();
		for (int x : nums) {
			// returns the next higher value than the current number if present
			// else returns null
			Integer higher_value = s.ceiling(x);
			if (higher_value != null) {
				s.remove(higher_value);
			}
			s.add(x);
			System.out.println(s);
		}
		System.out.println(s);
		return s.size();
		// total tc is O(nlogn)
	}
}
