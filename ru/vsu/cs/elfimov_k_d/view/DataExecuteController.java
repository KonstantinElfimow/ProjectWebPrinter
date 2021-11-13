package ru.vsu.cs.elfimov_k_d.view;

import ru.vsu.cs.elfimov_k_d.model.Data;
import ru.vsu.cs.elfimov_k_d.util.MyPriorityQueue;
import java.util.List;


public class DataExecuteController {
    public static void solveButton (List<Data> input) {
        MyPriorityQueue<Data> priorityQueue = new MyPriorityQueue<>((o1, o2) -> {
            int compare = Integer.parseInt(o1.getPriority()) - Integer.parseInt(o2.getPriority());
            if (compare == 0) {
                int compare1 = Integer.parseInt(o1.getNumberOfPages()) - Integer.parseInt(o2.getNumberOfPages());
                return (compare1 == 0 ?
                        (Integer.parseInt(o1.getIdentification()) - Integer.parseInt(o2.getIdentification()))
                        : compare1);
            } else {
                return compare;
            }
        });
        for (Data data : input) {
            priorityQueue.add(data);
        }
    }
}
