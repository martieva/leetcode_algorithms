//First pass at the Minimum Cost Climbing Stairs problem
//You are given an integer array cost where costs[i] is the cost of ith step on a staircase. 
//Once you pay the cost, you can either climb one or two steps.
//You can either start from the step with index 0, or the step with index 1.
//Return the minimum cost to reach the top of the floor.

class Solution {
    Map<Integer, Integer> results = new HashMap<>();
    public int minCostClimbingStairs(int[] costs) {
        int result1 = step(costs, 0);
        int result2 = step(costs, 1);
        if (result1 < result2) return result1;
        return result2;
    }

    private int step(int[] costs, int currentStep) {
        if (costs.length <= currentStep) {
            return 0;
        }

        int result1 = results.getOrDefault(currentStep+1, -1);
        int firstTotal = -1;
        if (result1 > -1) {
            firstTotal = result1;
        } else {
            firstTotal = step(costs, currentStep+1);
            results.put(currentStep+1, firstTotal);
        }

        int result2 = results.getOrDefault(currentStep+2, -1);
        int secondTotal = -1;
        if (result2 > -1) {
            secondTotal = result2;
        } else {
            secondTotal = step(costs, currentStep+2);
            results.put(currentStep+2, secondTotal);
        }
        
        int cost = costs[currentStep];
        if (firstTotal < secondTotal) {
            cost += firstTotal;
        } else {
            cost += secondTotal;
        }
        return cost;
    }
}