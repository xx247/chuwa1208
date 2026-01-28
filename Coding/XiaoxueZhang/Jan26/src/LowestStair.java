//Problem 1:
//Lowest Starting Stair
//
//        Description
//
//A grasshopper is jumping on a numbered staircase where the bottom stair is numbered 1, the next is 2, and so on. The grasshopper can jump up or down according to an array of jump values.
//
//For each jump in the array:
//        •If jumps[i] > 0, the grasshopper jumps jumps[i] steps up.
//        •If jumps[i] < 0, the grasshopper jumps |jumps[i]| steps down.
//
//Find the lowest possible stair number (startingStair) where the grasshopper can begin and remain on the staircase (stair number ≥ 1) throughout all jumps.
//
//        Example: jumps = [1, -4, -2, 3], lowest possible stairs: 6
//To never go below stair 1, every position must satisfy:
//
//S ≥ 1
//
//S + 1 ≥ 1 always true if S ≥ 1
//
//S - 3 ≥ 1 → S ≥ 4
//
//S - 5 ≥ 1 → S ≥ 6 (strongest constraint)
//
//S - 2 ≥ 1 → S ≥ 3
//
//So the minimum valid starting stair is S = 6.
public class LowestStair {
    public static int lowestStair(int[] jumps){
        int sum = 0;
        int start = 0;

        for(int jump : jumps){
            sum += jump;
            if(sum < start){
                start = sum;
            }
        }
        return 1 - start;
    }

    private static void runTest(int[] jumps, int expected){
        int result = lowestStair(jumps);
        System.out.println((result == expected ? "yes" : "no"));
    }
    public static void main(String[] args) {
        runTest(new int[]{1, -4, -2, 3}, 6);

        // 2) All positive jumps (never goes down; smallest start is 1)
        runTest(new int[]{2, 3, 1}, 1);

        // 3) All negative jumps (keeps going down; needs bigger start)
        // Prefix sums: 0, -1, -3, -6 => min = -6 => S = 1 - (-6) = 7
        runTest(new int[]{-1, -2, -3}, 7);

        // 4) Edge case: empty array (no jumps; just need to be on stair >= 1)
        runTest(new int[]{}, 1);

        // (Optional extra edge) oscillating around 1
        // Prefix sums: 0, -1, 0, -1, 0 => min = -1 => S = 2
        runTest(new int[]{-1, 1, -1, 1}, 2);
    }
}
