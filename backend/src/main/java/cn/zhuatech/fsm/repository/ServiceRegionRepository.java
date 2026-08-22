/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.repository; import cn.zhuatech.fsm.model.ServiceRegion; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface ServiceRegionRepository extends JpaRepository<ServiceRegion,Long>{Optional<ServiceRegion> findByCode(String code);}
