/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.controller;

import cn.zhuatech.fsm.common.ApiResponse;
import cn.zhuatech.fsm.service.FieldServiceWorkOrderGateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/fsm")
public class FieldServiceWorkOrderGateController {
    private final FieldServiceWorkOrderGateService service;
    public FieldServiceWorkOrderGateController(FieldServiceWorkOrderGateService service) { this.service = service; }

    @PostMapping("/work-order-dispatch-gate")
    public ApiResponse<FieldServiceWorkOrderGateService.Assessment> assess(
        @Valid @RequestBody FieldServiceWorkOrderGateService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
