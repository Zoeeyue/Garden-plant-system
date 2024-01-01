

import DAO.Impl.UpkeepDAOImpl;
import DAO.Impl.UpkeepTaskDAOImpl;
import DAO.UpkeepDAO;
import DAO.UpkeepTaskDAO;
import bean.Upkeep;
import bean.UpkeepTask;
import java.util.List;
import java.util.Scanner;

public class upkeep_service {

    public UpkeepTaskDAO upkeepTaskDAO = new UpkeepTaskDAOImpl();
    public UpkeepDAO upkeepDAO = new UpkeepDAOImpl();


    public void upkeepMenu() throws Exception {
        // 创建 UpkeepTaskDAO 实例
        UpkeepTaskDAO upkeepTaskDAO = new UpkeepTaskDAOImpl();

        // 创建 UpkeepDAO 实例
        UpkeepDAO upkeepDAO = new UpkeepDAOImpl();

        // 创建养护人员菜单

        // 启动养护人员菜单
        displayMenu();
    }



        public void displayMenu() throws Exception {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. 查看任务列表");
                System.out.println("2. 登记完成任务");
                System.out.println("3. 修改养护信息");
                System.out.println("4. 删除养护信息");
                System.out.println("5. 查找养护信息");
                System.out.println("6. 退出");
                System.out.print("请选择操作:");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewTaskList();
                        break;
                    case 2:
                        completeTaskRegistration();
                        break;
                    case 3:
                        updateUpkeepInfo();
                        break;
                    case 4:
                        deleteUpkeepInfo();
                        break;
                    case 5:
                        findUpkeepInfo();
                        break;
                    case 6:
                        System.out.println("退出养护人员菜单，谢谢使用！");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        break;
                }
            }
        }

        private void viewTaskList() throws Exception {
            // TODO: 实现查看任务列表的逻辑
            Scanner scanner = new Scanner(System.in);

            System.out.print("请输入人员ID(UTaskSid): ");
            String staffId = scanner.nextLine();

            // 查询属于该人员的任务
            List<UpkeepTask> tasksForStaff = upkeepTaskDAO.findTaskByProperty("UTaskSid", staffId);

            if (!tasksForStaff.isEmpty()) {
                System.out.println("找到以下属于人员" + staffId + " 的任务:");
                int count = 1;
                for (UpkeepTask task : tasksForStaff) {
                    System.out.println("第" + count + "个任务：");
                    System.out.println("任务编号："+task.getUTaskId());
                    System.out.println("养护对象：" + task.getPlantId());
                    System.out.println("养护人员编号：" + task.getUTaskSid());
                    System.out.println("任务描述：" + task.getUTaskDes());
                    System.out.println("任务状态:" + task.getUTaskStatus());
                    count++;
                }
            } else {
                System.out.println("未找到属于人员 " + staffId + " 的任务。");
            }
        }

        private void completeTaskRegistration() throws Exception {
            // TODO: 实现登记完成任务的逻辑
            Scanner scanner = new Scanner(System.in);

            // 步骤 1: 用户输入任务ID
            System.out.print("请输入任务ID(UTaskId): ");
            String taskId = scanner.nextLine();

            // 步骤 2: 验证任务ID在 UpkeepTask 表中存在
            while (upkeepTaskDAO.findTaskById(taskId) == null) {
                System.out.println("任务ID不存在，请重新输入。");
                System.out.print("请输入任务ID(UTaskId): ");
                taskId = scanner.nextLine();
            }

            // 步骤 3: 用户输入完成任务的其他相关信息
            // 这里需要根据实际情况输入其他信息，例如养护时间、地点等
            Upkeep upkeep = new Upkeep();
            upkeep.setUTaskId(taskId);

            // 设置 plant_id
            System.out.print("请输入植物编号: ");
            String plantId = scanner.nextLine();
            upkeep.setPlant_id(plantId);

            // 设置 UpkeepName
            System.out.print("请输入养护名称: ");
            String upkeepName = scanner.nextLine();
            upkeep.setUpkeepName(upkeepName);

            // 设置 UpkeepDes
            System.out.print("请输入养护描述: ");
            String upkeepDes = scanner.nextLine();
            upkeep.setUpkeepDes(upkeepDes);

            // 设置 UpkeepTime
            System.out.print("请输入养护时间（格式：yyyy-MM-dd HH:mm:ss）: ");
            String upkeepTimeString = scanner.nextLine();

            // 直接设置 Upkeep 实体类中的 UpkeepTime 属性为字符串形式的时间
            upkeep.setUpkeepTime(upkeepTimeString);


            // 设置 UpkeepPlace
            System.out.print("请输入完成任务的地点: ");
            String upkeepPlace = scanner.nextLine();
            upkeep.setUpkeepPlace(upkeepPlace);

            // 设置 UpkeepSiD
            System.out.print("请输入养护人员编号: ");
            String upkeepSiD = scanner.nextLine();
            upkeep.setUpkeepSiD(upkeepSiD);

            // 调用 UpkeepDAO 进行插入操作
            upkeepDAO.addUpkeep(upkeep);

            // 步骤 5: 更新 UpkeepTask 表中对应任务的状态为已完成
            UpkeepTask taskToUpdate = upkeepTaskDAO.findTaskById(taskId);
            if (taskToUpdate != null) {
                taskToUpdate.setUTaskStatus("已完成");
                upkeepTaskDAO.updateTask(taskToUpdate);
                System.out.println("UpkeepTask 表中对应任务状态更新成功。");
            } else {
                System.out.println("UpkeepTask 表中对应任务不存在，状态更新失败。");
            }

        }

        private void updateUpkeepInfo() throws Exception {
            // TODO: 实现修改养护信息的逻辑
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入要更新的养护编号（UpkeepId）: ");
            String upkeepIdToUpdate = scanner.nextLine();

            // 根据输入的养护编号查询数据库
            Upkeep existingUpkeep = upkeepDAO.findUpkeepById(upkeepIdToUpdate);

            if (existingUpkeep != null) {
                // 显示当前养护信息
                System.out.println("当前养护信息:");
                displayUpkeepInfo(existingUpkeep);

                // 用户选择要更新的字段
                System.out.println("请选择要更新的字段（1.养护名称 2.养护描述 3.养护时间 4.养护地点 5.养护人员编号 0.退出）: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 消耗掉输入缓冲区中的换行符

                switch (choice) {
                    case 1:
                        System.out.print("请输入新的养护名称: ");
                        String newUpkeepName = scanner.nextLine();
                        existingUpkeep.setUpkeepName(newUpkeepName);
                        break;
                    case 2:
                        System.out.print("请输入新的养护描述: ");
                        String newUpkeepDes = scanner.nextLine();
                        existingUpkeep.setUpkeepDes(newUpkeepDes);
                        break;
                    case 3:
                        System.out.print("请输入新的养护时间（格式：yyyy-MM-dd HH:mm:ss）: ");
                        String newUpkeepTimeString = scanner.nextLine();

                        // 直接设置 Upkeep 实体类中的 UpkeepTime 属性为字符串形式的时间
                        existingUpkeep.setUpkeepTime(newUpkeepTimeString);
                        break;
                    case 4:
                        System.out.print("请输入新的养护地点: ");
                        String newUpkeepPlace = scanner.nextLine();
                        existingUpkeep.setUpkeepPlace(newUpkeepPlace);
                        break;
                    case 5:
                        System.out.print("请输入新的养护人员编号: ");
                        String newUpkeepSiD = scanner.nextLine();
                        existingUpkeep.setUpkeepSiD(newUpkeepSiD);
                        break;
                    case 0:
                        System.out.println("退出更新养护信息。");
                        return;
                    default:
                        System.out.println("无效选择，请重新输入。");
                        break;
                }

                // 执行更新操作

                upkeepDAO.updateUpkeep(existingUpkeep);
                System.out.println(existingUpkeep.getUpkeepName());
                System.out.println("养护信息更新成功。");
            } else {
                System.out.println("未找到养护编号为 " + upkeepIdToUpdate + " 的记录。");
            }
        }

        private void displayUpkeepInfo(Upkeep upkeep) {
            // 在控制台显示养护信息
            System.out.println("养护编号: " + upkeep.getUpkeepId());
            System.out.println("任务编号: " + upkeep.getUTaskId());
            System.out.println("植物编号: " + upkeep.getPlant_id());
            System.out.println("养护名称: " + upkeep.getUpkeepName());
            System.out.println("养护描述: " + upkeep.getUpkeepDes());
            System.out.println("养护时间: " + upkeep.getUpkeepTime());
            System.out.println("养护地点: " + upkeep.getUpkeepPlace());
            System.out.println("养护人员编号: " + upkeep.getUpkeepSiD());
        }

        private void deleteUpkeepInfo() throws Exception {
            // TODO: 实现删除养护信息的逻辑
            Scanner scanner = new Scanner(System.in);

            // 从用户输入获取要删除的 UpkeepId
            System.out.println("请输入要删除的养护信息的 UpkeepId:");
            String upkeepIdToDelete = scanner.nextLine();

            // 根据 UpkeepId 查找养护信息
            UpkeepDAO upkeepDAO = new UpkeepDAOImpl();
            Upkeep upkeepToDelete = upkeepDAO.findUpkeepById(upkeepIdToDelete);

            if (upkeepToDelete != null) {
                // 显示养护信息供用户确认
                System.out.println("找到养护信息:");
                displayUpkeepInfo(upkeepToDelete);

                // 确认删除
                System.out.println("是否确认删除？(Y/N):");
                String confirmation = scanner.nextLine().trim();

                if (confirmation.equalsIgnoreCase("Y")) {
                    // 删除养护信息

                    UpkeepTask taskToUpdate = upkeepTaskDAO.findTaskById(upkeepToDelete.getUTaskId());
                    if (taskToUpdate != null) {
                        taskToUpdate.setUTaskStatus("未完成");
                        upkeepTaskDAO.updateTask(taskToUpdate);
                        System.out.println("UpkeepTask 表中对应任务状态更新成功。");
                    } else {
                        System.out.println("UpkeepTask 表中对应任务不存在，状态更新失败。");
                    }

                    upkeepDAO.deleteUpkeep(upkeepIdToDelete);
                    System.out.println("养护信息已删除。");
                } else {
                    System.out.println("删除操作已取消。");
                }
            } else {
                System.out.println("未找到养护信息，删除操作无效。");
            }


        }

        private void findUpkeepInfo() throws Exception {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("查找养护信息菜单:");
                System.out.println("1. 根据ID查找");
                System.out.println("2. 根据对象查找");
                System.out.println("3. 根据人员查找");
                System.out.println("4. 返回上级菜单");
                System.out.print("请选择操作:");

                int subChoice = scanner.nextInt();

                switch (subChoice) {
                    case 1:
                        findUpkeepById();
                        break;
                    case 2:
                        findUpkeepByPlantId();
                        break;
                    case 3:
                        findUpkeepByStaffId();
                        break;
                    case 4:
                        return; // 返回上级菜单
                    default:
                        System.out.println("无效的选择，请重新输入！");
                        break;
                }
            }

        }

        private void findUpkeepById() throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入养护ID:");
            String upkeepId = scanner.nextLine();

            UpkeepDAO upkeepDAO = new UpkeepDAOImpl();
            Upkeep foundUpkeep = upkeepDAO.findUpkeepById(upkeepId);

            if (foundUpkeep != null) {
                System.out.println("找到养护信息:");
                displayUpkeepInfo(foundUpkeep);
            } else {
                System.out.println("未找到养护信息。");
            }
        }

        private void findUpkeepByPlantId() throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入植物ID:");
            String plantId = scanner.nextLine();

            UpkeepDAO upkeepDAO = new UpkeepDAOImpl();
            List<Upkeep> foundUpkeepList = upkeepDAO.findUpkeepByProperty("plant_id", plantId);

            if (!foundUpkeepList.isEmpty()) {
                System.out.println("找到以下养护信息:");
                for (Upkeep upkeep : foundUpkeepList) {
                    displayUpkeepInfo(upkeep);
                }
            } else {
                System.out.println("未找到养护信息。");
            }
        }

        private void findUpkeepByStaffId() throws Exception {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入养护人员ID:");
            String staffId = scanner.nextLine();

            UpkeepDAO upkeepDAO = new UpkeepDAOImpl();
            List<Upkeep> foundUpkeepList = upkeepDAO.findUpkeepByProperty("UpkeepSiD", staffId);

            if (!foundUpkeepList.isEmpty()) {
                System.out.println("找到以下养护信息:");
                for (Upkeep upkeep : foundUpkeepList) {
                    displayUpkeepInfo(upkeep);
                }
            } else {
                System.out.println("未找到养护信息。");
            }
        }
    }

