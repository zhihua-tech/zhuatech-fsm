/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.controller;

import cn.zhuatech.fsm.common.ApiResponse;
import cn.zhuatech.fsm.service.FirstTimeFixReadinessService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/fsm/insights")
public class FirstTimeFixReadinessController {
    private final FirstTimeFixReadinessService service;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public FirstTimeFixReadinessController(FirstTimeFixReadinessService service) {
        this.service = service;
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/first-time-fix-readiness")
    public ApiResponse<FirstTimeFixReadinessService.Result> evaluate(
        @Valid @RequestBody FirstTimeFixReadinessService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
