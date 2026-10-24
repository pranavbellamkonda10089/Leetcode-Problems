class Solution {
    // Days in each month for a standard non-leap year
    private static final int[] DAYS_IN_MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // 1. Find the latest arrival and earliest departure using lexicographical comparison
        String overlapStart = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String overlapEnd = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;

        // 2. If the start of the overlap is after the end, they never meet
        if (overlapStart.compareTo(overlapEnd) > 0) {
            return 0;
        }

        // 3. Convert the overlapping dates to day-of-year and find the difference
        return dateToDayOfYear(overlapEnd) - dateToDayOfYear(overlapStart) + 1;
    }

    private int dateToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += DAYS_IN_MONTHS[i];
        }
        return totalDays;
    }
}
