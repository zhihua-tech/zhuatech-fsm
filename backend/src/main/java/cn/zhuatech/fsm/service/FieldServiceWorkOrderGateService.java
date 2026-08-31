/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldServiceWorkOrderGateService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.requiredSkillCertified()) blockers.add("工程师缺少工单所需有效资质");
        if (!request.sitePermitValid()) blockers.add("现场作业许可无效");
        if (!request.safetyChecklistComplete()) blockers.add("安全检查清单未完成");
        if (!blockers.isEmpty()) {
            actions.add("禁止派工，补齐资质与安全控制后重新评估");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.partsReserved() || !request.customerWindowConfirmed()
            || request.travelMinutes() >= request.slaRemainingMinutes()
            || !request.offlinePackReady() || !request.escalationContactAssigned()) {
            if (!request.partsReserved()) actions.add("锁定所需备件或确认替代方案");
            if (!request.customerWindowConfirmed()) actions.add("与客户确认上门时间窗");
            if (request.travelMinutes() >= request.slaRemainingMinutes()) actions.add("升级 SLA 风险并重新优化路线");
            if (!request.offlinePackReady()) actions.add("同步离线工单、图纸和设备历史");
            if (!request.escalationContactAssigned()) actions.add("指定现场升级联系人");
            return new Assessment(Decision.REVIEW, blockers, actions);
        }
        actions.add("允许派工并锁定工程师、备件和客户时间窗");
        return new Assessment(Decision.DISPATCH, blockers, actions);
    }

    public record Request(@NotBlank String workOrderNo, @NotBlank String technicianId,
                          boolean requiredSkillCertified, boolean sitePermitValid,
                          boolean safetyChecklistComplete, boolean partsReserved,
                          boolean customerWindowConfirmed, @Min(0) int travelMinutes,
                          @Min(1) int slaRemainingMinutes, boolean offlinePackReady,
                          boolean escalationContactAssigned) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { DISPATCH, REVIEW, BLOCKED }
}
