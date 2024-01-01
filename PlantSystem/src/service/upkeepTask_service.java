

import DAO.Impl.UpkeepStaffDAOImpl;
import DAO.Impl.UpkeepTaskDAOImpl;
import DAO.Impl.staffDAOImpl;
import DAO.UpkeepStaffDAO;
import DAO.UpkeepTaskDAO;
import DAO.staffDAO;
import bean.TaskCompletionStatus;
import bean.UpkeepStaff;
import bean.UpkeepTask;
import bean.staff;
import comm.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class upkeepTask_service {

    public UpkeepTaskDAO upkeepTaskDAO = new UpkeepTaskDAOImpl();
    public UpkeepStaffDAO upkeepStaffDAO = new UpkeepStaffDAOImpl();

    public void upkeeptask_service_menu() throws Exception {
        // 创建 UpkeepTaskDAO 实例
        UpkeepTaskDAO upkeepTaskDAO = new UpkeepTaskDAOImpl();

        // 创建 UpkeepStaffDAO 实例
        UpkeepStaffDAO upkeepStaffDAO = new UpkeepStaffDAOImpl();

        // 创建管理员菜单

        // 启动管理员菜单
        displayMenu();
    }


        public void displayMenu() throws Exception {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("上级主管菜单:");
                System.out.println("1. 分配养护任务");
                System.out.println("2. 查看养护任务");
                System.out.println("3. 退出");
                System.out.print("请选择操作:");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageUpkeepTask();
                        break;
                    case 2:
                        viewUpkeepTask();
                        break;
                    case 3:
                        System.out.println("退出系统，谢谢使用！");
                        System.exit(0);
                        return;
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        break;
                }
            }
        }

        private void manageUpkeepTask() throws Exception {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("分配养护任务菜单:");
                System.out.println("1. 修改养护任务");
                System.out.println("2. 添加养护任务");
                System.out.println("3. 删除养护任务");
                System.out.println("4. 返回上级菜单");
                System.out.print("请选择操作:");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        updateUpkeepTask();
                        break;
                    case 2:
                        addUpkeepTask();
                        break;
                    case 3:
                        deleteUpkeepTask();
                        break;
                    case 4:
                        return; // 返回上级菜单
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        break;
                }
            }
        }

        private void viewUpkeepTask() throws Exception {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("查看养护任务菜单:");
                System.out.println("1. 查找养护任务");
                System.out.println("2. 查看养护任务完成情况");
                System.out.println("3. 返回上级菜单");
                System.out.print("请选择操作:");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        findUpkeepTask();
                        break;
                    case 2:
                        viewTaskCompletionStatus();
                        break;
                    case 3:
                        return; // 返回上级菜单
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        break;
                }
            }
        }

        private void updateUpkeepTask() throws Exception {
            // TODO: 实现修改养护任务的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.print("请输入要修改的养护任务的任务编号(UTaskId): ");
            String taskId = scanner.nextLine();

            // 根据任务id查找养护任务
            UpkeepTask existingTask = upkeepTaskDAO.findTaskById(taskId);

            if (existingTask == null) {
                System.out.println("未找到对应的养护任务，请确认任务编号是否正确。");
                return;
            }

            // 显示当前任务信息
            System.out.println("当前养护任务信息:");
            System.out.println("养护对象：" + existingTask.getPlantId());
            System.out.println("养护人员编号：" + existingTask.getUTaskSid());
            System.out.println("任务描述：" + existingTask.getUTaskDes());
            System.out.println("任务状态:" + existingTask.getUTaskStatus());

            // 用户输入新的养护任务信息
            System.out.println("请输入新的养护任务信息:");

            System.out.print("养护对象(Plant_id): ");
            String plantId = scanner.nextLine();
            existingTask.setPlantId(plantId);

            System.out.print("养护人员编号(UTaskSid): ");
            String staffId = scanner.nextLine();
            existingTask.setUTaskSid(staffId);

            System.out.print("任务描述(UTaskDes): ");
            String taskDescription = scanner.nextLine();
            existingTask.setUTaskDes(taskDescription);

//        System.out.print("任务状态(UTaskStatus): ");
//        String taskStatus = scanner.nextLine();
//        existingTask.setUTaskStatus(taskStatus);

            // 更新养护任务
            upkeepTaskDAO.updateTask(existingTask);

            System.out.println("养护任务已成功更新。");
        }

        private void addUpkeepTask() {
            // TODO: 实现添加养护任务的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入养护任务信息:");

            System.out.print("任务编号(UTaskId): ");
            String taskId = scanner.nextLine();

            System.out.print("养护对象(Plant_id): ");
            String plantId = scanner.nextLine();

            System.out.print("养护人员编号(UTaskSid): ");
            String staffId = scanner.nextLine();

            System.out.print("任务描述(UTaskDes): ");
            String taskDescription = scanner.nextLine();

//        System.out.print("任务状态(UTaskStatus): ");
//        String taskStatus = scanner.nextLine();
            //任务状态为未完成

            // 创建养护任务对象
            UpkeepTask newUpkeepTask = new UpkeepTask(taskId, plantId, staffId, taskDescription, "未完成");

            try {
                // 调用 DAO 方法插入养护任务
                upkeepTaskDAO.addTask(newUpkeepTask);

                System.out.println("养护任务已成功添加。");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("添加养护任务时发生错误。");
            }
        }

        private void deleteUpkeepTask() {
            // TODO: 实现删除养护任务的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.println("请输入要删除的养护任务的任务编号(UTaskId): ");
            String taskIdToDelete = scanner.nextLine();

            // 根据任务ID查找任务
            UpkeepTask taskToDelete = upkeepTaskDAO.findTaskById(taskIdToDelete);

            if (taskToDelete != null) {
                // 输出任务信息
                System.out.println("找到以下养护任务信息:");
                System.out.println("养护对象：" + taskToDelete.getPlantId());
                System.out.println("养护人员编号：" + taskToDelete.getUTaskSid());
                System.out.println("任务描述：" + taskToDelete.getUTaskDes());
                System.out.println("任务状态:" + taskToDelete.getUTaskStatus());

                // 向用户确认是否确定删除
                System.out.print("是否确定删除该养护任务？(y/n): ");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("y")) {
                    try {
                        // 调用 DAO 方法删除养护任务
                        upkeepTaskDAO.deleteTask(taskIdToDelete);
                        System.out.println("养护任务已成功删除。");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("删除养护任务时发生错误。");
                    }
                } else {
                    System.out.println("取消删除，返回上一级菜单。");
                }
            } else {
                System.out.println("未找到任务编号为 " + taskIdToDelete + " 的养护任务。");
            }
        }

        private void findUpkeepTask() throws Exception {
            // TODO: 实现查找养护任务的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.println("请选择查询方式:");
            System.out.println("1. 根据任务编号查询");
            System.out.println("2. 根据养护人员编号查询");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    // 根据任务编号查询
                    System.out.print("请输入任务编号(UTaskId): ");
                    String taskIdToFind = scanner.nextLine();
                    displayUpkeepTaskInfo(upkeepTaskDAO.findTaskById(taskIdToFind));
                    break;

                case 2:
                    // 根据养护人员编号查询
                    System.out.print("请输入养护人员编号(UTaskSid): ");
                    String staffIdToFind = scanner.nextLine();
                    displayUpkeepTasksForStaff(staffIdToFind);
                    break;

                default:
                    System.out.println("无效的选择。");
                    break;
            }
        }

        // 辅助方法：显示养护任务信息
        private void displayUpkeepTaskInfo(UpkeepTask upkeepTask) {
            if (upkeepTask != null) {
                System.out.println("找到以下养护任务信息:");
                System.out.println("养护对象：" + upkeepTask.getPlantId());
                System.out.println("养护人员编号：" + upkeepTask.getUTaskSid());
                System.out.println("任务描述：" + upkeepTask.getUTaskDes());
                System.out.println("任务状态:" + upkeepTask.getUTaskStatus());
            } else {
                System.out.println("未找到相关养护任务。");
            }
        }

        // 辅助方法：根据养护人员编号显示养护任务信息
        private void displayUpkeepTasksForStaff(String staffId) throws Exception {
            List<UpkeepTask> tasksForStaff = upkeepTaskDAO.findTaskByProperty("UTaskSid", staffId);

            if (!tasksForStaff.isEmpty()) {
                System.out.println("找到以下养护任务信息:");
                int count = 1;
                for (UpkeepTask task : tasksForStaff) {
                    System.out.println("第" + count + "个任务：");
                    System.out.println("养护对象：" + task.getPlantId());
                    System.out.println("养护人员编号：" + task.getUTaskSid());
                    System.out.println("任务描述：" + task.getUTaskDes());
                    System.out.println("任务状态:" + task.getUTaskStatus());
                    count++;
                }
            } else {
                System.out.println("未找到养护人员编号为 " + staffId + " 的养护任务。");
            }
        }

        private void viewTaskCompletionStatus() {
            // TODO: 实现查看养护任务完成情况的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.println("请选择查看任务完成情况:");
            System.out.println("1. 查看已完成的任务");
            System.out.println("2. 查看未完成的任务");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符

            switch (choice) {
                case 1:
                    // 查看已完成的任务
                    displayTaskStatus("已完成");
                    break;

                case 2:
                    // 查看未完成的任务
                    displayTaskStatus("未完成");
                    break;

                default:
                    System.out.println("无效的选择。");
                    break;
            }
        }

        // 辅助方法：根据任务状态显示任务信息
        private void displayTaskStatus(String status) {
            DButil dbUtil = new DButil();

            String query = "SELECT UTaskId, UTaskSid FROM UpkeepTaskView WHERE UTaskStatus = ?";
            List<TaskCompletionStatus> taskStatusList = new ArrayList<>();

            try (Connection connection = dbUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setString(1, status);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String taskId = resultSet.getString("UTaskId");
                        String staffId = resultSet.getString("UTaskSid");

                        // 创建 TaskCompletionStatus 对象并添加到列表
                        TaskCompletionStatus taskStatus = new TaskCompletionStatus(taskId, staffId);
                        taskStatusList.add(taskStatus);
                    }
                }

                // 显示任务信息
                displayTaskList(taskStatusList, status);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("查看任务完成情况时发生错误。");
            }
        }

        // 辅助方法：显示任务列表
        private void displayTaskList(List<TaskCompletionStatus> taskStatusList, String status) {
            if (!taskStatusList.isEmpty()) {
                System.out.println("找到以下" + status + "的任务:");
                for (TaskCompletionStatus taskStatus : taskStatusList) {
                    System.out.println(taskStatus);
                }
            } else {
                System.out.println("未找到" + status + "的任务。");
            }
        }
    }
