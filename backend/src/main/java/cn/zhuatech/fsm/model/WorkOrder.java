/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.fsm.model;
import jakarta.persistence.*; import java.time.LocalDate;
@Entity @Table(name="fsm_work_order") public class WorkOrder extends BaseEntity {
    public enum Status { RELEASED, RUNNING, PAUSED, COMPLETED }
    @Column(nullable=false,unique=true,length=32) private String orderNo; @Column(nullable=false,length=40) private String productCode; @Column(nullable=false,length=120) private String productName;
    @ManyToOne(optional=false,fetch=FetchType.LAZY) private ServiceRegion serviceRegion; @Column(nullable=false) private int plannedQty; @Column(nullable=false) private int completedQty; @Column(nullable=false) private int defectQty;
    @Column(nullable=false) private LocalDate dueDate; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Status status; @Column(length=40) private String batchNo;
    protected WorkOrder(){} public WorkOrder(String orderNo,String productCode,String productName,ServiceRegion serviceRegion,int plannedQty,int completedQty,int defectQty,LocalDate dueDate,Status status,String batchNo){this.orderNo=orderNo;this.productCode=productCode;this.productName=productName;this.serviceRegion=serviceRegion;this.plannedQty=plannedQty;this.completedQty=completedQty;this.defectQty=defectQty;this.dueDate=dueDate;this.status=status;this.batchNo=batchNo;}
    public String getOrderNo(){return orderNo;} public String getProductCode(){return productCode;} public String getProductName(){return productName;} public ServiceRegion getServiceRegion(){return serviceRegion;} public int getPlannedQty(){return plannedQty;} public int getCompletedQty(){return completedQty;} public int getDefectQty(){return defectQty;} public LocalDate getDueDate(){return dueDate;} public Status getStatus(){return status;} public String getBatchNo(){return batchNo;} public void report(int good,int defect){completedQty+=good;defectQty+=defect;if(completedQty>=plannedQty)status=Status.COMPLETED;else status=Status.RUNNING;}
}
