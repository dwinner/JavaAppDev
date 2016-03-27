package statistics;

import java.io.*;

/**
 * Демонстрация использования классов Stats и Graphs.
 * @author dwinner@inbox.ru
 */
public class DemoStat {

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) throws IOException {
        double nums[] = {
            10, 10, 11, 9, 8, 8, 9,
            10, 10, 13, 11, 11, 11,
            11, 12, 13, 14, 16, 17,
            15, 15, 16, 14, 16
        };
        new StatsWin(nums);
    }

}