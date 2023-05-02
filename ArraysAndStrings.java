//Reverse string
class Solution {
    //Or StringBuilder#reverse
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char k = s[i];
            s[i] = s[j];
            s[j] = k;
            i++;
            j--;
        }
    }
}

//Squares of a sorted array
//Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order
class Solution {
    public int[] sortedSquares(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            nums[i] = nums[i] * nums[i];
            i++;
        }
        Arrays.sort(nums);
        return nums;
    }
}

//Maximum Average Subarray
// Given an integer array nums consisting of n elements, and an integer k.
// Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value. 
class Solution {
    public int[] sortedSquares(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            nums[i] = nums[i] * nums[i];
            i++;
        }
        Arrays.sort(nums);
        return nums;
    }
}

//Maximum Consecutive Ones (sliding window)
// Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
class Solution {
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int numOfZeroes = 0;
        int maxConsecutiveOnes = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right]== 0) {
                numOfZeroes++;
            }

            while (numOfZeroes > k) {
                if (nums[left] == 0) {
                    numOfZeroes--;
                }

                left++;
            }

            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, right - left + 1);
        }

        return maxConsecutiveOnes;
    }
}

//Running Sum
// Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
// Return the running sum of nums.
class Solution {
    public int[] runningSum(int[] nums) {
        int[] runningSums = new int[nums.length];
        runningSums[0] = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            runningSums[i] = nums[i] + runningSums[i-1];
        }
        
        return runningSums;
    }
}

//Minimum Value to Get Positive Step by Step Sum
// Given an array of integers nums, you start with an initial positive value startValue.
// In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
// Return the minimum positive value of startValue such that the step by step sum is never less than 1.
class Solution {
    public int minStartValue(int[] nums) {
        int startValue = 1;
        int stepByStep = startValue;
        for (int i = 0; i < nums.length; i++) {
            stepByStep += nums[i];
            while (stepByStep < 1) {
                startValue++;
                stepByStep++;
            }
        }
        
        return startValue;
    }
}