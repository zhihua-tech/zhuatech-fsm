/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.model;
import jakarta.persistence.*; import java.time.LocalDateTime;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity @Table(name="fsm_safety_inspection") public class SafetyInspection extends BaseEntity {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Result { PENDING, PASSED, FAILED }
    @Column(nullable=false,unique=true,length=32) private String safetyInspectionNo; @ManyToOne(optional=false,fetch=FetchType.LAZY) private WorkOrder workOrder;
    @Column(nullable=false,length=30) private String safetyInspectionType; @Column(nullable=false) private int safetyInspectionQty; @Column(nullable=false) private int defectQty; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Result result;
    @Column(length=50) private String inspector; @Column(nullable=false) private LocalDateTime createdAt;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected SafetyInspection(){} /**
                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                    */
public SafetyInspection(String safetyInspectionNo,WorkOrder workOrder,String safetyInspectionType,int safetyInspectionQty,int defectQty,Result result,String inspector){this.safetyInspectionNo=safetyInspectionNo;this.workOrder=workOrder;this.safetyInspectionType=safetyInspectionType;this.safetyInspectionQty=safetyInspectionQty;this.defectQty=defectQty;this.result=result;this.inspector=inspector;this.createdAt=LocalDateTime.now();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getSafetyInspectionNo(){return safetyInspectionNo;} /**
                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                       */
public WorkOrder getWorkOrder(){return workOrder;} /**
                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                          */
public String getSafetyInspectionType(){return safetyInspectionType;} /**
                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                */
public int getSafetyInspectionQty(){return safetyInspectionQty;} /**
                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                 */
public int getDefectQty(){return defectQty;} /**
                                                                                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                              */
public Result getResult(){return result;} /**
                                                                                                                                                                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                        */
public String getInspector(){return inspector;}
}
