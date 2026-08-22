/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.controller;

import cn.zhuatech.fsm.common.ApiResponse;
import cn.zhuatech.fsm.service.FieldDispatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/dispatch-fit")
public class FieldDispatchController {
    private final FieldDispatchService service;
    public FieldDispatchController(FieldDispatchService service) { this.service = service; }
    @PostMapping
    ApiResponse<FieldDispatchService.DispatchFit> evaluate(
        @Valid @RequestBody FieldDispatchService.DispatchFitRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
