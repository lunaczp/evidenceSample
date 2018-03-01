# Web3j
web3j是java和Ethereum的桥梁，自身实现了Ethereum的协议，可以作为库提供给其他Java服务使用。
> web3j is a highly modular, reactive, type safe Java and Android library for working with Smart Contracts and integrating with clients (nodes) on the Ethereum network:

## 生成Java Wrapper
- 编写contract，a.sol
- solc编译，生成abi和bin文件`solc a.sol --bin --abi --optimize -o <output-dir>/`
- web3j生成java wrapper类`web3j solidity generate a.bin a.abi -o /path/to/java/src -p com.package.name`

如何，其他业务就可以把这些代码加入自己的项目中，直接使用。
