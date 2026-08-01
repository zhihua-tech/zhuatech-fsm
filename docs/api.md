# FSM API 摘要

版权所有 © 2026 上海如静知华信息科技有限公司。

| 服务标准 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 服务区域运营数据 |
| GET | `/api/admin/work-orders` | 现场服务任务清单 |
| GET | `/api/shopfloor/dashboard` | 工程师任务工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交现场服务结果 |
| POST | `/api/admin/dispatch-fit` | 工程师现场服务派单适配评估 |
