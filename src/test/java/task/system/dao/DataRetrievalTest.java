package task.system.dao;

import junit.framework.TestCase;
import org.junit.Assert;
import task.system.objects.Operation;

import java.util.ArrayList;
import java.util.List;

public class DataRetrievalTest extends TestCase {

    String testDescription = "Test Description";
    Operation operation = new Operation();
    DataRetrieval dataRetrieval = new DataRetrieval();


    public void testInsertNewTask() {


        Integer taskId = dataRetrieval.insertNewTask(testDescription);

        Assert.assertNotNull("Task insert test", taskId);

        testInsertNewOperation(taskId);

    }

    public void testInsertNewOperation(Integer taskId) {

        List<Integer> idList = new ArrayList<>();


        for (int i = 10; i < 20; i++) {
            Integer id = operation.addNewOperation(taskId, 10, 10, testDescription);
            idList.add(id);
            Assert.assertNotNull("Operation insert test", id);
        }

        testUpdateOperationState(idList);

    }

    public void testUpdateOperationState(List<Integer> idList) {

        for (Integer id : idList) {
            operation.changeState(id, 20);
        }

    }

}