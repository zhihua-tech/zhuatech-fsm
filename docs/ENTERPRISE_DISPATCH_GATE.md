# 企业级现场服务派工门禁

[知华科技（上海如静知华信息科技有限公司）](https://www.zhuatech.cn/)为 FSM 开源版增加工单派工治理。

`POST /api/enterprise/fsm/work-order-dispatch-gate` 校验工程师资质、作业许可、安全清单、备件、客户时间窗、SLA 路程风险、离线资料和升级联系人，返回 `DISPATCH / REVIEW / BLOCKED`。

该接口可在自动派单或人工确认前执行。生产系统应继续联动车辆定位、库存锁定、移动端离线同步和消息通知，并将每次决策写入工单审计轨迹。
