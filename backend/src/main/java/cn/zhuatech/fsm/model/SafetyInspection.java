/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="fsm_safety_inspection") public class SafetyInspection extends BaseEntity {
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String safetyInspectionNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private WorkOrder workOrder;
    @Column(nullable=false,length=30) private String safetyInspectionType; @Column(nullable=false) private int safetyInspectionQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    protected SafetyInspection(){} public SafetyInspection(String safetyInspectionNo,WorkOrder workOrder,String safetyInspectionType,int safetyInspectionQty,int defectQty,Result result,String inspector){this.safetyInspectionNo=safetyInspectionNo;this.workOrder=workOrder;this.safetyInspectionType=safetyInspectionType;this.safetyInspectionQty=safetyInspectionQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    public String getSafetyInspectionNo(){return safetyInspectionNo;} public WorkOrder getWorkOrder(){return workOrder;} public String getSafetyInspectionType(){return safetyInspectionType;} public int getSafetyInspectionQty(){return safetyInspectionQty;} public int getDefectQty(){return defectQty;} public Result getResult(){return result;} public String getInspector(){return inspector;}
}
