# FISCO-BCOS 开发使用流程

## 使用

### 流程(Java)
- 区块链节点部署
- Contract开发(Solidity)
- 生成Contract Java SDK
- 利用上步生成的SDK，开发Java接入代码，与区块链交互
    - 合约部署功能
    - 其他对Contract的调用
        - 数据变更
        - 数据读取
- 项目上线

### 开发说明
- 核心业务逻辑和功能都在Contract代码内。也就是说Contract体现了能做什么，做不了什么。
- Java只是给外围对接的API层，基本不承担业务逻辑。实际上，接入层可以用Nodejs或其他来实现都可以，看团队现有架构以及需要。（当然目前FISCO-BCOS只提供了Java和Nodejs支持）。

### 问题
- 测试、生产流程

