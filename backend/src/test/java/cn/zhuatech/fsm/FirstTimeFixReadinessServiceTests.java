/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm;

import cn.zhuatech.fsm.service.FirstTimeFixReadinessService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class FirstTimeFixReadinessServiceTests {
    private final FirstTimeFixReadinessService service = new FirstTimeFixReadinessService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void dispatchesReadyTechnicianWithParts() {
        var result = service.evaluate(new FirstTimeFixReadinessService.Request(
            "WO-2026-1001", true, 88, true, .90, true, 45));

        assertEquals(100, result.readinessScore());
        assertEquals("DISPATCH", result.decision());
        assertTrue(result.dispatchPackageReady());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void blocksUnsafeDispatch() {
        var result = service.evaluate(new FirstTimeFixReadinessService.Request(
            "WO-2026-1002", true, 90, true, .90, false, 30));

        assertEquals("BLOCK", result.decision());
    }
}
