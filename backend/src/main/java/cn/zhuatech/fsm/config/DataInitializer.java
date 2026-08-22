/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.config;

import cn.zhuatech.fsm.model.*;
import cn.zhuatech.fsm.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner seed(ServiceRegionRepository serviceRegions, WorkOrderRepository orders,
                           TechnicianRepository technicians, SafetyInspectionRepository safetyInspections,
                           UserRepository users, PasswordEncoder encoder) {
        return args -> {
            if (serviceRegions.count() > 0) return;
            ServiceRegion chemistry = serviceRegions.save(new ServiceRegion("REG-CHEM", "华东服务区域", "服务运营中心", 180));
            ServiceRegion micro = serviceRegions.save(new ServiceRegion("REG-MICRO", "华南服务区域", "研发中心", 120));
            ServiceRegion material = serviceRegions.save(new ServiceRegion("REG-MAT", "华北服务区域", "工程中心", 96));

            WorkOrder t1 = orders.save(new WorkOrder("WO-260801-018", "GB-T-228", "工业空调紧急维修", material, 24, 16, 1, LocalDate.now().plusDays(1), WorkOrder.Status.RUNNING, "S260801-A"));
            WorkOrder t2 = orders.save(new WorkOrder("WO-260801-021", "HPLC-042", "数控机床季度保养", chemistry, 18, 8, 0, LocalDate.now().plusDays(1), WorkOrder.Status.RUNNING, "S260801-C"));
            WorkOrder t3 = orders.save(new WorkOrder("WO-260802-006", "ISO-4833", "数据中心例行巡检", micro, 12, 0, 0, LocalDate.now().plusDays(3), WorkOrder.Status.RELEASED, "S260802-B"));
            WorkOrder t4 = orders.save(new WorkOrder("WO-260731-015", "ICP-017", "智能产线安装调试", chemistry, 20, 20, 1, LocalDate.now(), WorkOrder.Status.COMPLETED, "S260731-D"));

            technicians.saveAll(List.of(
                new Technician("TECH-HPLC-03", "制冷工程师 03", chemistry, Technician.Status.RUNNING, 88),
                new Technician("TECH-ICP-02", "自动化实施工程师", chemistry, Technician.Status.IDLE, 76),
                new Technician("TECH-UTM-05", "机电维修工程师", material, Technician.Status.RUNNING, 91),
                new Technician("TECH-INC-08", "数据中心工程师 08", micro, Technician.Status.ALARM, 62)
            ));
            safetyInspections.saveAll(List.of(
                new SafetyInspection("SAFE-260801-032", t1, "留样验收", 6, 0, SafetyInspection.Result.PASSED, "周妍"),
                new SafetyInspection("SAFE-260801-011", t2, "前处理验收", 3, 0, SafetyInspection.Result.PASSED, "陆承"),
                new SafetyInspection("SAFE-260801-018", t4, "结果验收", 5, 1, SafetyInspection.Result.FAILED, "周妍"),
                new SafetyInspection("SAFE-260802-003", t3, "收样确认", 4, 0, SafetyInspection.Result.PENDING, "陆承")
            ));
            String demo = encoder.encode("Demo@2026");
            users.saveAll(List.of(
                new UserAccount("operator", demo, "陆承", UserAccount.Role.FIELD_TECHNICIAN, "REG-CHEM"),
                new UserAccount("planner", demo, "周妍", UserAccount.Role.SERVICE_DISPATCHER, null),
                new UserAccount("quality", demo, "顾清", UserAccount.Role.QUALITY, null),
                new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, null)
            ));
        };
    }
}
