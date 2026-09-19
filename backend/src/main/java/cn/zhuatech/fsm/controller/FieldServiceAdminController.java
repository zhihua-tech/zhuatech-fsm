/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.controller; import cn.zhuatech.fsm.common.ApiResponse; import cn.zhuatech.fsm.dto.FsmDto.*; import cn.zhuatech.fsm.service.FsmService; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.web.bind.annotation.*; import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/admin") @PreAuthorize("hasAnyRole('SERVICE_DISPATCHER','QUALITY','ADMIN')") public class FieldServiceAdminController {private final FsmService service;/**
                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                              */
public FieldServiceAdminController(FsmService service){this.service=service;}/**
                                                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                           */
@GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.adminDashboard());}/**
                                                                                                                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                */
@GetMapping("/work-orders") public ApiResponse<List<WorkOrderView>> orders(){return ApiResponse.ok(service.workOrders());}}
