# Web3j
web3j是java和Ethereum的桥梁，自身实现了Ethereum的协议，可以作为库提供给其他Java服务使用。
> web3j is a highly modular, reactive, type safe Java and Android library for working with Smart Contracts and integrating with clients (nodes) on the Ethereum network:

## 生成Java Wrapper
- 编写contract，a.sol
- solc编译，生成abi和bin文件`solc a.sol --bin --abi --optimize -o <output-dir>/`
- web3j生成java wrapper类`web3j solidity generate a.bin a.abi -o /path/to/java/src -p com.package.name`

如此，其他业务就可以把这些代码加入自己的项目中，直接使用。


## Java Wrapper使用
- 提供了部署合约功能
```
Web3j web3 = Web3j.build(new HttpService());  // defaults to http://localhost:8545/
Credentials credentials = WalletUtils.loadCredentials("password", "/path/to/walletfile");

YourSmartContract contract = YourSmartContract.deploy(
        <web3j>, <credentials>,
        GAS_PRICE, GAS_LIMIT,
        <param1>, ..., <paramN>).send();  // constructor params

```
会生成一个合约地址，用于以后的调用

- 提供了合约加载、调用功能
```
//根据合约地址，加载一个合约
YourSmartContract contract = YourSmartContract.load(
        "0x<address>|<ensName>", <web3j>, <credentials>, GAS_PRICE, GAS_LIMIT);
//利用加载的合约对象，调用合约方法
//transaction
TransactionReceipt transactionReceipt = contract.someMethod(
             <param1>,
             ...).send();
//call
Type result = contract.someMethod(<param1>, ...).send();
```
