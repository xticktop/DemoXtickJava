# XTick Java SDK

<p align=center>
  <a href="http://www.xtick.top/">
    <img src="./doc/images/xticklogo.png" alt="实时行情报价数据接口" style="width:260px;height:120px">
  </a>
</p>

<p align=center>
   XTick提供实时行情报价数据接入解决方案 - Java版本SDK
</p>

## 项目介绍

XTick Java SDK是一个专业的金融数据API客户端库，提供了全面、准确、稳定的行情数据接入能力。本SDK基于Java语言开发，支持HTTP REST API方式，帮助开发者和研究者快速构建创新的交易和分析工具。

**主要特性：**
- 📊 提供完整的K线数据查询（多周期、复权支持）
- 💹 丰富的技术指标计算（MACD、KDJ、RSI等100+指标）
- 💰 财务数据查询（资产负债表、利润表、现金流量表等）
- 🔥 热点数据追踪（龙虎榜、市场情绪、板块概念）
- ⚡ 量化因子数据（47个量化指标字段）
- 🔄 支持数据全推和按需订阅

**官方网站：** http://www.xtick.top  
**GitHub地址：** https://github.com/xticktop/xtick  
**Gitee地址：** https://gitee.com/xtick

## 已接入数据预览

<p><strong>接入数据预览</strong></p>
<table style="width:89%;">
<colgroup>
<col style="width: 9%" />
<col style="width: 13%" />
<col style="width: 14%" />
<col style="width: 11%" />
<col style="width: 13%" />
<col style="width: 26%" />
</colgroup>
<tbody>
<tr>
<td style="text-align: left;">数据类别</td>
<td style="text-align: left;">数据细分</td>
<td style="text-align: left;">更新方式</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">获取方式</td>
<td style="text-align: left;">历史数据范围</td>
</tr>
<tr>
<td rowspan="2" style="text-align: center;">tick数据</td>
<td style="text-align: left;">tick实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
<td rowspan="2" style="text-align: left;"><p>指数和北证：2025年10月-至今</p>
<p>A股、ETF：2025年2月-至今</p></td>
</tr>
<tr>
<td style="text-align: left;">tick历史数据</td>
<td style="text-align: left;">盘后更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td rowspan="3" style="text-align: center;">竞价数据</td>
<td style="text-align: left;">竞价实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
<td rowspan="3" style="text-align: left;"><p>竞价历史：2025年7月-至今</p>
<p>竞价历史详情：2025年2月-至今</p></td>
</tr>
<tr>
<td style="text-align: left;">竞价历史数据</td>
<td style="text-align: left;">9:25分更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td style="text-align: left;">竞价历史详情数据</td>
<td style="text-align: left;">9:25分更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td rowspan="3" style="text-align: center;">分钟数据</td>
<td style="text-align: left;">1分钟实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
<td rowspan="3" style="text-align: left;"><p>分钟级别数据：2024年4月-至今</p>
<p>所有分钟周期数据，均支持复权</p></td>
</tr>
<tr>
<td style="text-align: left;">1分钟K线数据</td>
<td style="text-align: left;">按1分钟频率实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td style="text-align: left;">其它分钟K线数据</td>
<td style="text-align: left;">按1分钟频率实时更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td rowspan="2" style="text-align: center;">日线数据</td>
<td style="text-align: left;">日线实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
<td rowspan="2" style="text-align: left;"><p>日线级别数据：公司上市-至今</p>
<p>日线数据支持复权</p></td>
</tr>
<tr>
<td style="text-align: left;">日线历史数据</td>
<td style="text-align: left;">3:35分更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td rowspan="2" style="text-align: center;">量化数据</td>
<td style="text-align: left;">量化因子实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
<td rowspan="2" style="text-align: left;">2008年1月-至今</td>
</tr>
<tr>
<td style="text-align: left;">量化因子历史数据</td>
<td style="text-align: left;">3:35分更新</td>
<td style="text-align: left;">数据全推</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td style="text-align: center;">核心指标</td>
<td style="text-align: left;">核心指标实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td style="text-align: left;">参考量化数据</td>
</tr>
<tr>
<td rowspan="2" style="text-align: center;">金融指标</td>
<td style="text-align: left;">金融指标实时数据</td>
<td style="text-align: left;">实时更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td rowspan="2" style="text-align: left;"><p>分钟级别指标：2024年4月-至今</p>
<p>日线级别指标：公司上市-至今</p></td>
</tr>
<tr>
<td style="text-align: left;">金融指标历史数据</td>
<td style="text-align: left;">3:35分更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
</tr>
<tr>
<td style="text-align: center;">财务数据</td>
<td style="text-align: left;">盘后更新</td>
<td style="text-align: left;">盘后更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td style="text-align: left;">2008年1月-至今</td>
</tr>
<tr>
<td rowspan="3" style="text-align: center;">其它数据</td>
<td style="text-align: left;">交易日历</td>
<td style="text-align: left;">3:35分更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td style="text-align: left;">公司上市-至今</td>
</tr>
<tr>
<td style="text-align: left;">股票列表</td>
<td style="text-align: left;">盘后更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td style="text-align: left;">每日更新</td>
</tr>
<tr>
<td style="text-align: left;">复权变更数据</td>
<td style="text-align: left;">盘后更新</td>
<td style="text-align: left;">按个股获取</td>
<td style="text-align: left;">api调用</td>
<td style="text-align: left;">公司上市-至今</td>
</tr>
</tbody>
</table>

## API接口文档

XTick Java SDK提供了完整的API接口封装，主要分为以下几个模块：

### 核心类说明

- **XTickStockApiClient** - HTTP API客户端主类，提供所有REST API接口的示例调用
- **XTickMarketApi** - 行情数据API（K线、股票信息、交易日历等）
- **XTickIndicatorApi** - 技术指标API（100+金融指标计算）
- **XTickWatchApi** - 盯盘数据API（深度行情、日K线实时、成交统计、日K线复权等）
- **XTickCoreApi** - 核心数据API（分时成交、分价表、竞价数据等）
- **XTickHotApi** - 热点数据API（板块概念、资金流向、新闻舆情等）
- **XTickQuantApi** - 量化数据API（47个量化因子字段）

### 接口分类

#### 1. 行情数据接口 (XTickMarketApi)

```java
XTickMarketApi marketApi = new XTickMarketApi();

// 参数说明：
// type: 标的类型 (1=沪深京A股, 2=沪深指数, 3=港股, 4=ETF基金, 5=沪深可转债)
// code: 股票代码
// period: K线周期 (1m/5m/15m/30m/1h/1d/1w/1mon/1q/1y)
// fq: 复权类型 (1=不复权, 2=前复权, 3=后复权, 4=等比前复权, 5=等比后复权)
// startDate: 起始日期 (yyyy-MM-dd)
// endDate: 结束日期 (yyyy-MM-dd)
String result = marketApi.getKlineMarket(
    1, "000001", "1d", "2", 
    "2025-01-01", "2025-12-31", 
    token, MethodType.POST);

List<XTickKlineData> klines = JsonUtil.jsonToList(result, XTickKlineData.class);
```

**获取实时分钟数据**

```java
// 获取当天实时1分钟K线数据
String result = marketApi.getKlineMinute(1, "000001", "2", token, MethodType.POST);
```

**获取股票列表**

```java
// symbol可选值：all/sz/sh/bj/hk/index/bond/cyb/kcb/etf/st/ts
String result = marketApi.getStockInfo("sz", token, MethodType.POST);
List<XTickStockInfo> stocks = JsonUtil.jsonToList(result, XTickStockInfo.class);
```

**获取交易日历**

```java
String result = marketApi.getCalendar(
    "000001", "2025-01-01", "2025-12-31", 
    token, MethodType.POST);
List<XTickStockCalendar> calendars = JsonUtil.jsonToList(result, XTickStockCalendar.class);
```

#### 2. 技术指标接口 (XTickIndicatorApi)

支持100+金融技术指标计算，包括：

**常用指标示例：**

```java
XTickIndicatorApi indicatorApi = new XTickIndicatorApi();

// MACD指标
String result = indicatorApi.macd(
    1, "000001", "1d", "2",
    "2025-01-01", "2025-12-31",
    token, 
    2,   // inReal: 2=收盘价
    12,  // optInFastPeriod
    26,  // optInSlowPeriod
    9,   // optInSignalPeriod
    MethodType.POST);

// KDJ指标 (STOCH)
result = indicatorApi.stoch(
    1, "000001", "1d", "2",
    "2025-01-01", "2025-12-31",
    token,
    9,  // optInFastK_Period
    5,  // optInSlowK_Period
    2,  // optInSlowK_MAType (2=EMA)
    5,  // optInSlowD_Period
    2,  // optInSlowD_MAType (2=EMA)
    MethodType.POST);

// RSI指标
result = indicatorApi.rsi(
    1, "000001", "1d", "2",
    "2025-01-01", "2025-12-31",
    token,
    2,   // inReal: 2=收盘价
    14,  // optInTimePeriod
    MethodType.POST);
```

**支持的指标列表（部分）：**

- **趋势指标：** ADX, ADXR, APO, AROON, MACD, MACDEXT, PPO, TRIX等
- **震荡指标：** CCI, CMO, MFI, RSI, STOCH, STOCHF, WILLR等
- **成交量指标：** AD, ADOSC, OBV, MFI等
- **价格指标：** AVGPRICE, MEDPRICE, TYPPRICE, WCLPRICE等
- **希尔伯特变换：** HTDCPERIOD, HTDCPHASE, HTPHASOR, HTSINE等
- **其他指标：** BOP, DX, MOM, PLUSDI, MINUSDI, ROC, ROCP等

详细指标文档请访问：http://www.xtick.top/indicator

#### 3. 财务数据接口 (XTickMarketApi)

```java
XTickMarketApi marketApi = new XTickMarketApi();

// 股东数
String result = marketApi.getHolderNum("000001", "2024-01-01", "2025-12-31", token, MethodType.POST);

// 财务指标
result = marketApi.getCoreFinancial("000001", "2024-01-01", "2025-12-31", token, MethodType.POST);

// 十大股东
result = marketApi.getTopHolder("000001", "2024-01-01", "2025-12-31", token, MethodType.POST);

// 十大流通股东
result = marketApi.getTopFlowHolder("000001", "2024-01-01", "2025-12-31", token, MethodType.POST);
```

**财务报表类型：**

- Balance - 资产负债表
- Income - 利润表
- CashFlow - 现金流量表
- Capital - 股本表
- Holdernum - 股东数
- Top10holder - 十大股东
- Top10flowholder - 十大流通股东
- Pershareindex - 每股指标

#### 4. 盯盘数据接口 (XTickWatchApi)

```java
XTickWatchApi watchApi = new XTickWatchApi();

// 日K线-实时数据（支持批量参数，支持ALL参数，适合盯盘）
String result = watchApi.getOrderDay(1, "000001", token, MethodType.POST);

// 分钟K线-实时数据（支持批量参数，支持ALL参数）
result = watchApi.getOrderMinute(1, "000001", token, MethodType.POST);

// 深度行情-实时数据
result = watchApi.getDeep(1, "000001", token, MethodType.POST);

// 深度行情-历史数据（盘后更新）
result = watchApi.getTickHistory(1, "000001", "2025-01-15", token, MethodType.POST);

// 成交统计-实时接口（全市场成交额统计）
result = watchApi.getAmount("2025-01-15", token, MethodType.POST);

// 日K线复权-更新接口（盘后更新，仅支持近一周增量数据）
// fq: 1=不复权, 2=前复权, 3=后复权, 4=等比前复权, 5=等比后复权
result = watchApi.getFqKline(1, "2", "2025-01-15", token, MethodType.POST);
```

#### 5. 核心数据接口 (XTickCoreApi)

```java
XTickCoreApi coreApi = new XTickCoreApi();

// 分时成交数据
String result = coreApi.getCoreFenbi(1, "000001", "2025-01-15", token, MethodType.POST);
List<XTickTimeDeal> fenbiDatas = JsonUtil.jsonToList(result, XTickTimeDeal.class);

// 分价表数据
result = coreApi.getCoreFenjia(1, "000001", "2025-01-15", token, MethodType.POST);
List<XTickTimePrice> fenjiaDatas = JsonUtil.jsonToList(result, XTickTimePrice.class);

// 竞价数据-实时
result = coreApi.getCoreBidTime(1, "000001", "", token, MethodType.POST);
List<XTickBidTime> bidTimes = JsonUtil.jsonToList(result, XTickBidTime.class);

// 停牌股票
result = coreApi.getCoreTingpai(1, "603311", "2025-01-01", "2025-12-31", token, MethodType.POST);

// ST股票历史
result = coreApi.getCoreSt(1, "600636", "2025-01-01", "2025-12-31", token, MethodType.POST);

// 复权变更数据
result = coreApi.getCoreChuQuan(1, "000001", "2025-01-01", "2025-12-31", token, MethodType.POST);

// 历史价格
result = coreApi.getCorePrice(1, "000001", 1, "2025-01-01", "2025-12-31", token, MethodType.POST);
```

#### 6. 热点数据接口 (XTickHotApi)

```java
XTickHotApi hotApi = new XTickHotApi();

// 连板天梯-实时接口（flag: 1=涨停, 2=跌停, 3=炸板）
String result = hotApi.getHotBoard(1, 1, "2025-01-15", token, MethodType.POST);

// 市场情绪-实时接口
result = hotApi.getHotEmotion(1, "2025-01-15", token, MethodType.POST);

// 资金流向-实时接口
result = hotApi.getHotTimeMoney(1, "000001", token, MethodType.POST);

// 资金流向-历史接口
result = hotApi.getHotHistoryMoney(1, "000001", "2025-01-01", "2025-01-15", token, MethodType.POST);

// 竞价数据-历史接口
result = hotApi.getHotBidHistory(1, "000001", "2025-01-15", "2025-01-15", token, MethodType.POST);

// 竞价详情-实时接口
result = hotApi.getHotBidDetail(1, "000001", "2025-01-15", token, MethodType.POST);

// 新闻资讯-实时接口
result = hotApi.getHotNews(30, "2025-01-15", token, MethodType.POST);

// 日内分时-实时接口
result = hotApi.getHotTimekline(1, "000001", token, MethodType.POST);

// 概念板块成分股数据（symbol如：sw3/thshy/gn/dy等）
result = hotApi.getHotBk("sw3", token, MethodType.POST);

// 股票关联概念板块数据
result = hotApi.getHotGainian("000001", token, MethodType.POST);

// 增量更新-热点数据
result = hotApi.getHotDayUpdate("bid", "all", "2026-07-15", token, MethodType.POST);
```

#### 7. 量化数据接口 (XTickQuantApi)

```java
XTickQuantApi quantApi = new XTickQuantApi();

// 获取量化因子实时数据
String result = quantApi.getQunatData(1, "all", token, MethodType.POST);
QuantPacket quantData = JsonUtil.jsonToObj(result, QuantPacket.class);

// 转换为字段Map（按指标字段组织数据）
Map<String, Object> fieldMap = quantData.toFieldMap();

// 转换为股票Map（按个股组织数据）
Map<String, Object> stockMap = quantData.toStockMap();

// 获取量化因子历史数据
result = quantApi.getQuantHistory("2025-01-15", token, MethodType.POST);
```

**量化因子字段定义（47个字段）：**

| 字段代码 | 字段名称 | 字段代码 | 字段名称 |
|---------|---------|---------|----------|
| x001 | 昨收价 | x025 | 流通市值 |
| x002 | 最新价 | x026 | 市净率 |
| x003 | 开盘价 | x027 | 换手率 |
| x004 | 最高价 | x028 | 实际换手率 |
| x005 | 最低价 | x029 | 涨幅 |
| x006 | 成交量 | x030 | 5日涨幅 |
| x007 | 成交额 | x031 | 10日涨幅 |
| x008 | 涨跌 | x032 | 20日涨幅 |
| x009 | 振幅 | x033 | 5日均线 |
| x010 | 均价 | x034 | 10日均线 |
| x011 | 现均差 | x035 | 20日均线 |
| x012 | 涨停价 | x036 | 30日均线 |
| x013 | 跌停价 | x037 | 60日均线 |
| x014 | 涨停板(-1/1) | x038 | 120日均线 |
| x015 | 涨速 | x039 | MACD-DIF |
| x016 | 1分钟涨速 | x040 | MACD-DEA |
| x017 | 2分钟涨速 | x041 | MACD-MACD |
| x018 | 3分钟涨速 | x042 | KDJ-K |
| x019 | 4分钟涨速 | x043 | KDJ-D |
| x020 | 5分钟涨速 | x044 | KDJ-J |
| x021 | 静态市盈率 | x045 | RSI |
| x022 | 动态市盈率 | x046 | WR |
| x023 | TTM市盈率 | x047 | CCI |
| x024 | 总市值 |  |  |

## 项目结构

```
src/main/java/org/xtick/
├── XTickStockApiClient.java      # HTTP API客户端主类（包含所有示例）
├── api/                           # API接口模块
│   ├── XTickMarketApi.java       # 行情数据API
│   ├── XTickIndicatorApi.java    # 技术指标API
│   ├── XTickWatchApi.java        # 盯盘数据API
│   ├── XTickCoreApi.java         # 核心数据API
│   ├── XTickHotApi.java          # 热点数据API
│   ├── XTickQuantApi.java        # 量化数据API
├── bean/                          # 数据模型
│   ├── base/                      # 基础数据模型
│   ├── core/                      # 核心数据模型
│   ├── finance/                   # 财务数据模型
│   ├── hot/                       # 热点数据模型
│   └── ...                        # 其他数据模型
├── constant/                      # 常量定义
│   ├── XTickConst.java           # 系统常量
│   └── MethodType.java           # 请求方法类型
├── http/                          # HTTP工具
│   ├── HttpClientRest.java       # HTTP客户端封装
│   └── ...
└── util/                          # 工具类
    ├── JsonUtil.java             # JSON工具
    └── XTickUtil.java            # XTick通用工具
```

## 使用建议

### 1. 性能优化

- **批量查询：** 合理设置批量参数，避免频繁调用
- **缓存策略：** 对不常变化的数据（如股票列表、交易日历）进行缓存
- **连接池：** HTTP请求建议使用连接池管理

### 2. 错误处理

```java
try {
    String result = marketApi.getKlineMarket(1, "000001", "1d", "2", 
        "2025-01-01", "2025-12-31", token, MethodType.POST);
    List<XTickKlineData> klines = JsonUtil.jsonToList(result, XTickKlineData.class);
    // 处理数据...
} catch (IOException e) {
    System.err.println("API调用失败: " + e.getMessage());
    // 重试逻辑或错误处理
}
```

### 3. 最佳实践

- **Token安全：** 不要将Token硬编码在代码中，建议使用配置文件或环境变量
- **限流控制：** 注意API调用频率，避免触发限流
- **数据验证：** 对返回数据进行有效性验证
- **日志记录：** 记录关键API调用和错误信息

## 常见问题

### Q1: 如何获取API Token？

访问 [XTick官网](http://www.xtick.top) 注册账号，登录后在个人中心获取Token。

### Q2: 分钟数据单次请求时间跨度限制？

period为分钟类型（1m、5m、15m、30m、1h），单次请求时间跨度最大为30天。

### Q3: 如何获取全市场数据？

将code参数设置为"all"，例如：
```java
String result = marketApi.getKlineMarket(1, "all", "1d", "1", 
    "2025-01-15", "2025-01-15", token, MethodType.POST);
```

### Q4: 支持哪些市场？

- 沪深京A股 (type=1)
- 沪深指数 (type=2)
- 港股 (type=3)
- ETF基金 (type=4)
- 沪深可转债 (type=5)

## 数据更新说明

| 数据类型 | 更新频率 | 历史数据范围 |
|---------|---------|-------------|
| Tick实时数据 | 实时更新 | 2025年2月-至今（A股、ETF）<br>2025年10月-至今（指数、北证） |
| Tick历史数据 | 盘后更新 | 同上 |
| 竞价实时数据 | 实时更新 | 2025年7月-至今 |
| 竞价历史详情 | 9:25分更新 | 2025年2月-至今 |
| 1分钟K线 | 实时更新 | 2024年4月-至今 |
| 其他周期K线 | 实时更新 | 2024年4月-至今（支持复权） |
| 日线数据 | 实时更新 | 公司上市-至今（支持复权） |
| 量化因子 | 实时更新 | 2008年1月-至今 |
| 财务数据 | 盘后更新 | 2008年1月-至今 |
| 交易日历 | 3:35分更新 | 公司上市-至今 |

## Skills资源

XTick提供了AI Skills开源项目，方便AI助手更好地理解和使用API：

- **Gitee:** https://gitee.com/xtick/skills
- **GitHub:** https://github.com/xticktop/skills

Skills文档路径：`/doc/references`

## 社区与支持

- **官方网站：** http://www.xtick.top
- **GitHub:** https://github.com/xticktop/xtick
- **Gitee:** https://gitee.com/xtick
- **邮箱：** xticktop@163.com
- **QQ群：** 加群备注：XTick
- **详细文档：** [飞书文档](https://ccn9lag3l54q.feishu.cn/wiki/ABenwEvDOiShYrkaLAJcFY5gnZf)

## 贡献指南

欢迎提交Issue和Pull Request！如果您发现bug或有改进建议，请通过以下方式反馈：

1. 在GitHub/Gitee上提交Issue
2. 发送邮件至 xticktop@163.com
3. 加入QQ群交流

## 许可证

本项目仅供学习和研究使用，请勿用于商业用途。使用时请遵守XTick的使用条款。

## 致谢

感谢所有使用和支持XTick项目的开发者们！您的支持是我们持续维护和改进的动力。

---

<p align=center>
  <strong>⭐ 如果这个项目对您有帮助，请给我们一个Star！ ⭐</strong>
</p>




