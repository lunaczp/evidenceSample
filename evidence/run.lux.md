# 示例运行

## 说明
按照文档
- 部署单节点区块链
- 编译代码`gradle run`，生成包(或者直接使用自带生成好的，在evidence_toolkit)
- 按照文档一步步执行即可。


## 遇到的问题

### 区块链部署
- 当区块链和客户端不在一台机器的时候，注意节点`config.json`的ip配置
    - 'Peerip','listenip'，不要填错

### AMOP
这个存证示例使用了AMOP。

- `applicationContext.xml`中，要填写连接节点的`name@ip:port`，
`name`可以随意命名，注意`port`对应的是节点`config.json`的`channelPort`，即AMOP端口。

- AMOP是加密通信，客户端需要有服务端的CA证书。
代码自带的ca.cert是和[示例节点部署代码](https://github.com/FISCO-BCOS/FISCO-BCOS/tree/master/doc/manual)一致的。

所以如果你在部署节点的时候，使用了自己生成的ca，那么需要把客户端的ca.cert也替换成你生成的那个。

- AMOP的加密通信，并不依赖于节点的ssl配置，即节点的`config.json`中的`ssl:0`不影响AMOP //todo 配置的意义，即作用范围
