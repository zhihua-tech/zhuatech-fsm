/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.fsm.repository; import cn.zhuatech.fsm.model.SafetyInspection; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface SafetyInspectionRepository extends JpaRepository<SafetyInspection,Long>{List<SafetyInspection> findTop10ByOrderByIdDesc();long countByResult(SafetyInspection.Result result);}
