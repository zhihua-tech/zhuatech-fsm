# ZhuaTech FSM Community Edition


## 本次升级：现场服务工时与备件双限容量

核对每单维修及往返时长、可用技师工时和实际齐套备件，按完整工单计算本班服务能力；不自动派单。

新增已认证的结构化录入工作台（`/operations.html`），支持批量核算、逐条结果依据、分级复核和CSV/完整JSON导出。结果由真实后端算法计算，不是预置演示数字。所有数值精确传递，不调用外部模型。

这是**只读核算**：不写库、不审批、不自动执行财务、签署、销毁或设备控制。输入条件满足不是执行授权。[算法、输入边界与接口说明](docs/OPERATIONS-REVIEW.md)。
## 2026-10-23 功能更新：现场服务到场与备件时窗核验

按工单预约时窗、工程师可出发时间、交通时长和备件就绪时间计算最早到场、预计完成与违约分钟，不自动派单。

登录真实后端后，点击右下角“业务计算工具”，可输入结构化数据、执行计算和导出完整结果。该功能已实现后端算法，并非静态示例响应。

接口：`POST /api/workbench/field-visit-feasibility`（沿用当前登录 JWT）。计算仅处理本次输入，不读取其他用户记录，不写入业务单据、不自动审批、不调用外部模型。算法口径、输入示例、边界和测试方法见[本次更新说明](docs/AI_WORKFLOW_UPGRADE_20261023.md)。

[简体中文](README.md) | [English](README.en.md)

## 现场服务管理系统

[![Java 21](https://img.shields.io/badge/Java-21-2f6f75)](backend/pom.xml) [![Vue 3](https://img.shields.io/badge/Vue-3-42b883)](frontend/package.json) [![MySQL 8](https://img.shields.io/badge/MySQL-8-4479a1)](compose.yaml) [![个人非商用](https://img.shields.io/badge/license-personal%20non--commercial-b47b3a)](LICENSE)

连接服务受理、智能派工、现场执行、安全检查与客户验收。 本仓库是[知华科技](https://www.zhuatech.cn/)（上海如静知华信息科技有限公司）面向技术学习与交流发布的社区源码工程。

## 一眼了解业务

- **流程**：服务受理 → 工单分级 → 工程师派工 → 到场执行 → 安全验收 → 服务归档
- **用户**：现场工程师、服务调度主管、验收人员、系统管理员
- **终端**：后台管理端 + 响应式 H5 岗位端
- **特点**：可运行 API、MySQL 迁移、JWT 权限、领域化演示数据

1. 服务区域、工单优先级与派工计划
2. 工程师技能、路线排期和移动执行
3. 安全检查、服务报告、验收与 SLA 分析

## 产品实景

### 现场服务运营驾驶舱

![现场服务运营驾驶舱](docs/images/fsm-service-dashboard.png)

### 工单调度与履约台账

![工单调度与履约台账](docs/images/fsm-work-order-register.png)

### 现场工程师工作台

![现场工程师工作台](docs/images/fsm-technician-workbench.png)

## 派单适配评估

新增 `POST /api/admin/dispatch-fit`：综合工程师技能匹配度、服务距离、当前负载、备件准备、预计到场时间和紧急程度，输出适配分数、`DISPATCH / REVIEW / BLOCK` 派单结论及原因，帮助调度人员减少无效派单与 SLA 违约。

## 架构选型

| 部分 | 技术与职责 |
| --- | --- |
| 后端 | Java 21、Spring Boot、Spring Security、JPA、Flyway |
| 前端 | Vue 3、Pinia、Vue Router、Axios、Vite，响应式管理端与 H5 岗位端 |
| 数据 | MySQL 8；H2 集成测试 |
| 交付 | Docker Compose、Nginx、环境变量配置 |

Java 工程包名为 `cn.zhuatech.fsm`，数据库名为 `zhuatech_fsm`。角色覆盖现场工程师、服务调度主管、验收人员、系统管理员。

## 本地启动手册

仅看演示界面：

```bash
cd frontend
npm install
npm run dev:demo
```

打开 `http://localhost:5173`。管理端账号 `planner / Demo@2026`，岗位端账号 `operator / Demo@2026`。

完整启动：

```bash
cp .env.example .env
# 修改数据库密码与 JWT_SECRET
docker compose up --build
```

## 从演示到生产

仓库中的账号、客户、指标、工单和经营数据均为虚构演示数据。正式落地时应更换默认密码与 JWT 密钥，配置 HTTPS、最小权限、数据库备份、操作审计、脱敏策略，并按照所在行业完成安全与合规评估。

## 个人学习许可与企业授权

本工程仅允许个人、非商业性的学习、研究和技术交流，**不得商用**。企业内部使用、生产部署、SaaS、客户交付、收费培训、咨询实施及品牌替换，均须事先取得上海如静知华信息科技有限公司书面授权。完整条款见 [LICENSE](LICENSE)。

需要深度开发、私有化部署、系统集成或商业授权，请访问[知华科技官网](https://www.zhuatech.cn/)，也可扫码添加微信咨询：

| 微信咨询 1 | 微信咨询 2 |
| --- | --- |
| ![知华科技微信咨询二维码 1](docs/images/zhuatech-wechat-consulting.png) | ![知华科技微信咨询二维码 2](docs/images/zhuatech-wechat-consulting-2.png) |

搜索收录建议：FSM 源码、现场服务系统、工单派工、移动服务、Java FSM、Vue FSM、企业级 FSM、知华科技、上海如静知华信息科技有限公司。

## 一次修复准备度

新增 `POST /api/fsm/insights/first-time-fix-readiness`，综合症状匹配、工程师技能、备件、远程诊断置信度、安全许可和路程输出 `DISPATCH / PREPARE / BLOCK`。派工前即可发现备件、技能和安全条件缺口，提升首次上门解决率。

## 企业级现场服务派工门禁

新增 `POST /api/enterprise/fsm/work-order-dispatch-gate`，覆盖资质、许可、安全、备件、客户时间窗、SLA、离线资料和升级责任，输出 `DISPATCH / REVIEW / BLOCKED`。详见 [派工门禁说明](docs/ENTERPRISE_DISPATCH_GATE.md)。
