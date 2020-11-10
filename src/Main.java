import Helpers.*;
import Interfaces.CPUProcessInterface;
import Schedulers.*;

import java.lang.Process;
import java.util.List;

public class Main {
    public static void main(String args[]){
        Scheduler scheduler = new Scheduler();
        FirstComeFirstServe fcfs = new FirstComeFirstServe();
        ShortestJobFirst sjf = new ShortestJobFirst();
        MultiLevelFeedbackQueue mlfq = new MultiLevelFeedbackQueue();
        CPUProcess[] arr, arr2, arr3;

        CPUProcess p1_1 = new CPUProcess("P1", new int[]{5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4});
        CPUProcess p2_1 = new CPUProcess("P2", new int[]{4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8});
        CPUProcess p3_1 = new CPUProcess("P3", new int[]{8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6});
        CPUProcess p4_1 = new CPUProcess("P4", new int[]{3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3});
        CPUProcess p5_1 = new CPUProcess("P5", new int[]{16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4});
        CPUProcess p6_1 = new CPUProcess("P6", new int[]{11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8});
        CPUProcess p7_1 = new CPUProcess("P7", new int[]{14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10});
        CPUProcess p8_1 = new CPUProcess("P8", new int[]{4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6});

        CPUProcess p1_2 = new CPUProcess("P1", new int[]{5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4});
        CPUProcess p2_2 = new CPUProcess("P2", new int[]{4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8});
        CPUProcess p3_2 = new CPUProcess("P3", new int[]{8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6});
        CPUProcess p4_2 = new CPUProcess("P4", new int[]{3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3});
        CPUProcess p5_2 = new CPUProcess("P5", new int[]{16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4});
        CPUProcess p6_2 = new CPUProcess("P6", new int[]{11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8});
        CPUProcess p7_2 = new CPUProcess("P7", new int[]{14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10});
        CPUProcess p8_2 = new CPUProcess("P8", new int[]{4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6});

        CPUProcess p1_3 = new CPUProcess("P1", new int[]{5, 27, 3, 31, 5, 43, 4, 18, 6, 22, 4, 26, 3, 24, 4});
        CPUProcess p2_3 = new CPUProcess("P2", new int[]{4, 48, 5, 44, 7, 42, 12, 37, 9, 76, 4, 41, 9, 31, 7, 43, 8});
        CPUProcess p3_3 = new CPUProcess("P3", new int[]{8, 33, 12, 41, 18, 65, 14, 21, 4, 61, 15, 18, 14, 26, 5, 31, 6});
        CPUProcess p4_3 = new CPUProcess("P4", new int[]{3, 35, 4, 41, 5, 45, 3, 51, 4, 61, 5, 54, 6, 82, 5, 77, 3});
        CPUProcess p5_3 = new CPUProcess("P5", new int[]{16, 24, 17, 21, 5, 36, 16, 26, 7, 31, 13, 28, 11, 21, 6, 13, 3, 11, 4});
        CPUProcess p6_3 = new CPUProcess("P6", new int[]{11, 22, 4, 8, 5, 10, 6, 12, 7, 14, 9, 18, 12, 24, 15, 30, 8});
        CPUProcess p7_3 = new CPUProcess("P7", new int[]{14, 46, 17, 41, 11, 42, 15, 21, 4, 32, 7, 19, 16, 33, 10});
        CPUProcess p8_3 = new CPUProcess("P8", new int[]{4, 14, 5, 33, 6, 51, 14, 73, 16, 87, 6});

        arr = new CPUProcess[]{p1_1,p2_1,p3_1,p4_1,p5_1,p6_1,p7_1,p8_1};
        arr2 = new CPUProcess[]{p1_2,p2_2,p3_2,p4_2,p5_2,p6_2,p7_2,p8_2};
        arr3 = new CPUProcess[]{p1_3,p2_3,p3_3,p4_3,p5_3,p6_3,p7_3,p8_3};

        mlfq.fillScheduler(arr);
        sjf.fillScheduler(arr2);
        fcfs.fillScheduler(arr3);

        sjf.run();
        fcfs.run();
        mlfq.run();

    }
}
