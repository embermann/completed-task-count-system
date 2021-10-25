package task.system.dao;

import task.system.domain.OperationObj;
import task.system.domain.TaskObj;
import task.system.objects.Operation;
import task.system.utils.DatabaseConnection;
import task.system.utils.StateEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRetrieval {

    Connection connection;

    public DataRetrieval() {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

    }

    public int insertNewTask(String description) {
        int result = 0;

        String sql = "INSERT INTO task (description, id_state, creation_date) " +
                "VALUES (?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            stmt.setString(++i, description);
            stmt.setByte(++i, StateEnum.PROJECT.getId());
            stmt.setDate(++i, new Date(System.currentTimeMillis()));

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int insertNewOperation(OperationObj operation) {
        int result = 0;

        String sql = "INSERT INTO task_operation (id_task, description, planned_quantity, price, total_cost, id_state, creation_date) " +
                "VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            stmt.setInt(++i, operation.getIdTask());
            stmt.setString(++i, operation.getDescription());
            stmt.setInt(++i, operation.getPlannedQuantity());
            stmt.setInt(++i, operation.getPrice());
            stmt.setInt(++i, operation.getTotalCost());
            stmt.setByte(++i, operation.getIdState());
            stmt.setDate(++i, new Date(System.currentTimeMillis()));

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getInt(1);
            }

            updateTaskPrice(operation);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void updateTaskPrice(OperationObj operation) {

        int taskPrice = getTaskPrice(operation) + operation.getTotalCost();

        String sql = "UPDATE task " +
                "SET price=? " +
                "WHERE id_task=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, taskPrice);
            stmt.setInt(++i, operation.getIdTask());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private int getTaskPrice(OperationObj operation) {
        int result = 0;
        String sql = "SELECT price " +
                "FROM task " +
                "WHERE id_task = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, operation.getIdTask());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    public List<TaskObj> getTaskList() {
        List<TaskObj> result = new ArrayList<>();

        String sql = "SELECT id_task, description, price, id_state, creation_date " +
                "FROM task " +
                "WHERE NOT id_state=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setByte(1, StateEnum.COMPLETED.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(
                        new TaskObj(
                                rs.getInt("id_task"),
                                rs.getString("description"),
                                rs.getInt("price"),
                                rs.getByte("id_state"),
                                rs.getDate("creation_date")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    public int getOperationPrice(int id) {
        int result = 0;

        String sql = "SELECT price " +
                "FROM task_operation " +
                "WHERE id_task_operation=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("price");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    public void completeOperation(OperationObj operationObj) {

        String sql = "UPDATE task_operation " +
                "SET id_state=?, actual_quantity=?, total_cost=?, completion_date=? " +
                "WHERE id_task_operation=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setByte(++i, operationObj.getIdState());
            stmt.setInt(++i, operationObj.getActualQuantity());
            stmt.setInt(++i, operationObj.getTotalCost());
            stmt.setDate(++i, operationObj.getCompletionDate());
            stmt.setInt(++i, operationObj.getIdTaskOperation());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTaskData(StateEnum state, int price, int taskOperationId) {

        int taskId = getTaskId(taskOperationId);

        String sql = "UPDATE task " +
                "SET id_state=?, price=?";
                if (state.getId() == StateEnum.COMPLETED.getId()) {
                   sql += ", completion_date=?";
                }
                sql += " WHERE id_task=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setByte(++i, state.getId());
            stmt.setInt(++i, price);
            if (state.getId() == StateEnum.COMPLETED.getId()) {
                stmt.setDate(++i, new Date(System.currentTimeMillis()));
            }
            stmt.setInt(++i, taskId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OperationObj> getTaskOperationList(int taskOperationId) {
        List<OperationObj> result = new ArrayList<>();

        int taskId = getTaskId(taskOperationId);

        String sql = "SELECT id_task_operation, id_state, total_cost " +
                "FROM task_operation " +
                "WHERE id_task=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, taskId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(
                        new OperationObj(
                                rs.getInt("id_task_operation"),
                                rs.getByte("id_state"),
                                rs.getInt("total_cost")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

    private int getTaskId(int taskOperationId) {
        int result = 0;
        String sql = "SELECT id_task " +
                "FROM task_operation " +
                "WHERE id_task_operation = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setInt(++i, taskOperationId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = rs.getInt("id_task");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public StateEnum getOperationState(int id) {
        StateEnum result = null;

        String sql = "SELECT id_state " +
                "FROM task_operation " +
                "WHERE id_task_operation=? ";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                result = StateEnum.getByID(rs.getByte("id_state"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<TaskObj> getTaskListInPeriod(Date from, Date to) {

        List<TaskObj> result = new ArrayList<>();

        String sql = "SELECT id_task, description, price, id_state, completion_date " +
                "FROM task " +
                "WHERE completion_date>=? and completion_date<=? and id_state=?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            int i = 0;
            stmt.setDate(++i, from);
            stmt.setDate(++i, to);
            stmt.setInt(++i, StateEnum.COMPLETED.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(
                    new TaskObj(
                        rs.getInt("id_task"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getByte("id_state"),
                        rs.getDate("completion_date")
                    )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;

    }


}
