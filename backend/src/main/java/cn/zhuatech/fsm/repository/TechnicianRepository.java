/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.fsm.repository; import cn.zhuatech.fsm.model.Technician; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface TechnicianRepository extends JpaRepository<Technician,Long>{List<Technician> findAllByOrderByCodeAsc();long countByStatus(Technician.Status status);}
