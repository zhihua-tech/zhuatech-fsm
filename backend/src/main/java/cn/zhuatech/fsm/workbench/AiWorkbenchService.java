/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fsm.workbench;

import static cn.zhuatech.fsm.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);

    keys(r, "visits");
    Set<String> ids = new HashSet<>();
    List<Map<String, Object>> out = new ArrayList<>();
    for (var v : rows(r, "visits", 1, 200)) {
      keys(
          v,
          "id",
          "technicianReady",
          "partsReady",
          "travelMinutes",
          "serviceMinutes",
          "windowStart",
          "windowEnd");
      String id = id(v, "id");
      unique(ids, id);
      var ready = local(text(v, "technicianReady", 30));
      var parts = local(text(v, "partsReady", 30));
      var start = local(text(v, "windowStart", 30));
      var end = local(text(v, "windowEnd", 30));
      if (!start.isBefore(end)) throw new IllegalArgumentException("预约时窗必须正向");
      int travel = integer(v, "travelMinutes", 0, 1440),
          service = integer(v, "serviceMinutes", 1, 1440);
      var depart = ready.isAfter(parts) ? ready : parts;
      var arrival = depart.plusMinutes(travel);
      var begin = arrival.isAfter(start) ? arrival : start;
      var completion = begin.plusMinutes(service);
      long late = Math.max(0, java.time.Duration.between(end, completion).toMinutes());
      out.add(
          Map.of(
              "id",
              id,
              "departure",
              depart.toString(),
              "arrival",
              arrival.toString(),
              "serviceStart",
              begin.toString(),
              "completion",
              completion.toString(),
              "lateMinutes",
              late,
              "feasible",
              late == 0));
    }
    return Map.of("visits", out, "automaticDispatch", false, "timeBasis", "ORGANIZATION_LOCAL");
  }
}
