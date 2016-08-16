package advanced.scan.singlepointer.lc134_gasstation;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * Note: The solution is guaranteed to be unique.
 */
public class Solution {

    // My 2nd: Max cost, max gas, max gas-cost? All wrong...
    // Improved by idea from leetcode discuss, update total from tank, beat 85%!
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length != cost.length) {
            return -1;
        }

        // Tank describes total gap - cost from [start,i]
        // Tank < 0 means station between [start,i] is excluded
        int total = 0, tank = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            tank += (gas[i] - cost[i]);
            if (tank < 0) {
                start = i + 1;
                total += tank;
                tank = 0;
            }
        }
        return total + tank >= 0 ? start : -1; // error: don't forget last tank
    }
    
    // My 1st
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }

        int totalGap = 0, startGap = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int curGap = (gas[i] - cost[i]);
            if (i == 0) {
                totalGap = startGap = curGap;
                //continue;     // error: [1,2][2,1] -> 1 not 0, so check startGap<0 anyway!
            } else {
                startGap += curGap;
                totalGap += curGap;
            }
            if (startGap < 0) { // [start,i] cannot be starting point
                start = i + 1;
                startGap = 0;
            }
        }
        return (totalGap >= 0) ? start : -1;
    }

    // Max gap doesn't guarantee optimal or even complete, since you don't know what'll happen behind
    // [6,1,4,3,5]
    // [3,8,2,4,2]
    // mine: 0, should be: 2
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }

        int maxGap = 0, totalGap = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int curGap = (gas[i] - cost[i]);
            if (maxGap < curGap) {
                maxGap = curGap;
                start = i;
            }
            totalGap += curGap;
        }
        return (totalGap >= 0) ? start : -1;
    }

}
