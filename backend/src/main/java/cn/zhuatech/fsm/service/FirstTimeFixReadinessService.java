/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FirstTimeFixReadinessService {
    public Result evaluate(Request request) {
        int score = 0;
        if (request.symptomMatched()) score += 20;
        score += Math.round(request.technicianSkillScore() * .25F);
        if (request.partsAvailable()) score += 25;
        score += Math.round((float) request.remoteDiagnosisConfidence() * 20);
        if (request.safetyPermitReady()) score += 20;
        if (request.travelMinutes() > 120) score -= 10;
        score = Math.max(0, Math.min(100, score));
        String decision = !request.safetyPermitReady() ? "BLOCK"
            : score >= 75 ? "DISPATCH" : "PREPARE";

        List<String> actions = new ArrayList<>();
        if (!request.symptomMatched()) actions.add("补充远程诊断并匹配历史故障知识");
        if (!request.partsAvailable()) actions.add("锁定备件或调整具备备件的工程师");
        if (request.technicianSkillScore() < 70) actions.add("安排高技能工程师协同或远程支持");
        if (!request.safetyPermitReady()) actions.add("完成现场许可和安全作业条件确认");
        if (actions.isEmpty()) actions.add("允许派工并携带诊断结论与备件清单");
        return new Result(request.workOrderNo(), score, decision,
            request.partsAvailable() && request.safetyPermitReady(), actions);
    }

    public record Request(@NotBlank String workOrderNo, boolean symptomMatched,
                          @Min(0) @Max(100) int technicianSkillScore,
                          boolean partsAvailable,
                          @DecimalMin("0") @DecimalMax("1") double remoteDiagnosisConfidence,
                          boolean safetyPermitReady, @Min(0) int travelMinutes) {}

    public record Result(String workOrderNo, int readinessScore, String decision,
                         boolean dispatchPackageReady, List<String> actions) {}
}
