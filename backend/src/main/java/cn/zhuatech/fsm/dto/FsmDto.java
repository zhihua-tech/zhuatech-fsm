/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.fsm.dto;
import jakarta.validation.constraints.*; import java.time.*; import java.util.List;
public final class FsmDto { private FsmDto(){}
    public record Metric(String label,String value,String hint,String tone){}
    public record WorkOrderView(Long id,String orderNo,String productCode,String productName,String serviceRegion,String workshop,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,String status,String batchNo,int progress){}
    public record TechnicianView(String code,String name,String serviceRegion,String status,int oee,LocalDateTime lastHeartbeat){}
    public record SafetyInspectionView(String safetyInspectionNo,String orderNo,String productName,String safetyInspectionType,int safetyInspectionQty,int defectQty,String result,String inspector){}
    public record Dashboard(List<Metric> metrics,List<WorkOrderView> workOrders,List<TechnicianView> technician,List<SafetyInspectionView> safetyInspections){}
    public record ReportRequest(@NotBlank String operationName,@Positive int goodQty,@PositiveOrZero int defectQty,@Size(max=200) String remark){}
    public record ReportResult(String orderNo,int completedQty,int defectQty,int progress,String status){}
}
