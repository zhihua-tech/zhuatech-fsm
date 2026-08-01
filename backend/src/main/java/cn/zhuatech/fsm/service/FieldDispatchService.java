/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.fsm.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FieldDispatchService {
    public DispatchFit evaluate(DispatchFitRequest request) {
        int score = request.skillMatch()
            - Math.min(25, request.distanceKm() / 10)
            - Math.min(30, request.currentWorkload() * 6)
            - (request.sparePartReady() ? 0 : 25)
            - (request.estimatedTravelMinutes() > request.slaMinutes() ? 30 : 0)
            + (request.emergency() ? 5 : 0);
        score = Math.max(0, Math.min(100, score));
        String decision = score >= 75 ? "DISPATCH" : score >= 45 ? "REVIEW" : "BLOCK";
        List<String> reasons = new ArrayList<>();
        if (request.skillMatch() < 70) reasons.add("工程师技能匹配度不足");
        if (!request.sparePartReady()) reasons.add("服务所需备件尚未就绪");
        if (request.estimatedTravelMinutes() > request.slaMinutes()) reasons.add("预计到场时间将超过 SLA");
        if (request.currentWorkload() >= 4) reasons.add("工程师当前负载过高");
        if (reasons.isEmpty()) reasons.add("技能、距离、负载和备件满足派单条件");
        return new DispatchFit(score, decision, reasons);
    }

    public record DispatchFitRequest(@NotNull @Min(0) @Max(2000) Integer distanceKm,
        @NotNull @Min(0) @Max(100) Integer skillMatch, @NotNull Boolean sparePartReady,
        @NotNull @Positive Integer slaMinutes, @NotNull @Positive Integer estimatedTravelMinutes,
        @NotNull @Min(0) @Max(20) Integer currentWorkload, @NotNull Boolean emergency) {}
    public record DispatchFit(int score, String decision, List<String> reasons) {}
}
