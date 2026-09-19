/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class FieldServiceWorkOrderGateServiceTest {
    private final FieldServiceWorkOrderGateService service = new FieldServiceWorkOrderGateService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void dispatchesPreparedSafeWorkOrder() {
        var result = service.assess(new FieldServiceWorkOrderGateService.Request(
            "WO-001", "TECH-01", true, true, true, true, true, 30, 120, true, true));
        assertThat(result.decision()).isEqualTo(FieldServiceWorkOrderGateService.Decision.DISPATCH);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnsafeOrUnqualifiedDispatch() {
        var result = service.assess(new FieldServiceWorkOrderGateService.Request(
            "WO-002", "TECH-02", false, false, false, true, true, 30, 120, true, true));
        assertThat(result.decision()).isEqualTo(FieldServiceWorkOrderGateService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(3);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsOperationalReadinessGaps() {
        var result = service.assess(new FieldServiceWorkOrderGateService.Request(
            "WO-003", "TECH-03", true, true, true, false, false, 120, 90, false, false));
        assertThat(result.decision()).isEqualTo(FieldServiceWorkOrderGateService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(5);
    }
}
