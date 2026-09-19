/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.controller;

import cn.zhuatech.fsm.common.ApiResponse;
import cn.zhuatech.fsm.service.FieldServiceWorkOrderGateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/fsm")
public class FieldServiceWorkOrderGateController {
    private final FieldServiceWorkOrderGateService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public FieldServiceWorkOrderGateController(FieldServiceWorkOrderGateService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/work-order-dispatch-gate")
    public ApiResponse<FieldServiceWorkOrderGateService.Assessment> assess(
        @Valid @RequestBody FieldServiceWorkOrderGateService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
