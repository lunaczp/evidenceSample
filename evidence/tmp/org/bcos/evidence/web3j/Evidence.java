package org.bcos.evidence.web3j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class Evidence extends Contract {
    private static final String BINARY = "606060405234156200001057600080fd5b60405162001c6138038062001c6183398101604052808051820191906020018051820191906020018051820191906020018051919060200180519190602001805191906020018051919060200180519150505b60078054600160a060020a031916600160a060020a03841617905562000097816401000000006200051681026200137d1704565b156200035e576000888051620000b2929160200190620005ae565b506001878051620000c8929160200190620005ae565b506002868051620000de929160200190620005ae565b506003805460018101620000f3838262000634565b91600052602060002090602091828204019190065b815460ff808a166101009390930a9283029202191617905550600480546001810162000135838262000671565b916000526020600020900160005b508590555060058054600181016200015c838262000671565b916000526020600020900160005b5084905550600680546001810162000183838262000671565b916000526020600020900160005b8154600160a060020a038086166101009390930a92830292021916179055507f6001b9d5afd61e6053e8a73e30790ef8240d919a754055049131521927fbe2118888888888888860405160ff851660608201526080810184905260a08101839052600160a060020a03821660c082015260e080825281906020820190604083019083018b818151815260200191508051906020019080838360005b83811015620002475780820151818401525b6020016200022c565b50505050905090810190601f168015620002755780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b83811015620002ae5780820151818401525b60200162000293565b50505050905090810190601f168015620002dc5780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620003155780820151818401525b602001620002fa565b50505050905090810190601f168015620003435780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a162000507565b7f45cb885dcdccd3bece3cb78b963aec501f1cf9756fd93584f0643c7a953343108888888888888860405160ff851660608201526080810184905260a08101839052600160a060020a03821660c082015260e080825281906020820190604083019083018b818151815260200191508051906020019080838360005b83811015620003f55780820151818401525b602001620003da565b50505050905090810190601f168015620004235780820380516001836020036101000a031916815260200191505b5084810383528a818151815260200191508051906020019080838360005b838110156200045c5780820151818401525b60200162000441565b50505050905090810190601f1680156200048a5780820380516001836020036101000a031916815260200191505b50848103825289818151815260200191508051906020019080838360005b83811015620004c35780820151818401525b602001620004a8565b50505050905090810190601f168015620004f15780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390a15b5b505050505050505062000713565b600754600090600160a060020a03166363a9c3d78383604051602001526040517c010000000000000000000000000000000000000000000000000000000063ffffffff8416028152600160a060020a039091166004820152602401602060405180830381600087803b15156200058b57600080fd5b6102c65a03f115156200059d57600080fd5b50505060405180519150505b919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620005f157805160ff191683800117855562000621565b8280016001018555821562000621579182015b828111156200062157825182559160200191906001019062000604565b5b5062000630929150620006cb565b5090565b8154818355818115116200066b57601f016020900481601f016020900483600052602060002091820191016200066b9190620006cb565b5b505050565b8154818355818115116200066b576000838152602090206200066b918101908301620006cb565b5b505050565b8154818355818115116200066b576000838152602090206200066b918101908301620006cb565b5b505050565b620006ec91905b80821115620006305760008155600101620006d2565b5090565b90565b620006ec91905b80821115620006305760008155600101620006d2565b5090565b90565b61153e80620007236000396000f3006060604052361561005c5763ffffffff60e060020a6000350416633b52ebd0811461006157806348f85bce14610090578063596f21f8146100c357806394cf795e1461033b578063c7eaf9b4146103a2578063dc58ab111461042d575b600080fd5b341561006c57600080fd5b610074610460565b604051600160a060020a03909116815260200160405180910390f35b341561009b57600080fd5b6100af60ff6004351660243560443561046f565b604051901515815260200160405180910390f35b34156100ce57600080fd5b6100d6610d08565b604051808060200180602001806020018060200180602001806020018060200188810388528f818151815260200191508051906020019080838360005b8381101561012c5780820151818401525b602001610113565b50505050905090810190601f1680156101595780820380516001836020036101000a031916815260200191505b5088810387528e818151815260200191508051906020019080838360005b838110156101905780820151818401525b602001610177565b50505050905090810190601f1680156101bd5780820380516001836020036101000a031916815260200191505b5088810386528d818151815260200191508051906020019080838360005b838110156101f45780820151818401525b6020016101db565b50505050905090810190601f1680156102215780820380516001836020036101000a031916815260200191505b5088810385528c818151815260200191508051906020019060200280838360005b8381101561025b5780820151818401525b602001610242565b5050505090500188810384528b818151815260200191508051906020019060200280838360005b8381101561029b5780820151818401525b602001610282565b5050505090500188810383528a818151815260200191508051906020019060200280838360005b838110156102db5780820151818401525b6020016102c2565b50505050905001888103825289818151815260200191508051906020019060200280838360005b8381101561031b5780820151818401525b602001610302565b505050509050019e50505050505050505050505050505060405180910390f35b341561034657600080fd5b61034e61118a565b60405160208082528190810183818151815260200191508051906020019060200280838360005b8381101561038e5780820151818401525b602001610375565b505050509050019250505060405180910390f35b34156103ad57600080fd5b6103b56112d4565b60405160208082528190810183818151815260200191508051906020019080838360005b838110156103f25780820151818401525b6020016103d9565b50505050905090810190601f16801561041f5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b341561043857600080fd5b6100af600160a060020a036004351661137d565b604051901515815260200160405180910390f35b600754600160a060020a031681565b6000805b60065481101561088757600680548290811061048b57fe5b906000526020600020900160005b9054906101000a9004600160a060020a0316600160a060020a031633600160a060020a0316141561087d578460ff166003828154811015156104d757fe5b90600052602060002090602091828204019190065b9054906101000a900460ff1660ff161480156105265750600480548591908390811061051457fe5b906000526020600020900160005b5054145b80156105505750600580548491908390811061053e57fe5b906000526020600020900160005b5054145b1561071f577fcb265a1c827beb2fd9947f2a8d4725c8918f266faf695a26a53ac662e42a2f3f60006001600288888860405160ff841660608201526080810183905260a0810182905260c080825287546002610100600183161502600019019091160490820181905281906020820190604083019060e08401908b9080156106195780601f106105ee57610100808354040283529160200191610619565b820191906000526020600020905b8154815290600101906020018083116105fc57829003601f168201915b505084810383528954600260001961010060018416150201909116048082526020909101908a90801561068d5780601f106106625761010080835404028352916020019161068d565b820191906000526020600020905b81548152906001019060200180831161067057829003601f168201915b50508481038252885460026000196101006001841615020190911604808252602090910190899080156107015780601f106106d657610100808354040283529160200191610701565b820191906000526020600020905b8154815290600101906020018083116106e457829003601f168201915b5050995050505050505050505060405180910390a160019150610cff565b7f05e85d72e83e8d2c8c877a19dd3a15c60415f315dc6d176b21537216537d2757600060028787873360405160ff851660408201526060810184905260808101839052600160a060020a03821660a082015260c08082528754600261010060018316150260001901909116049082018190528190602082019060e08301908a9080156107ec5780601f106107c1576101008083540402835291602001916107ec565b820191906000526020600020905b8154815290600101906020018083116107cf57829003601f168201915b50508381038252885460026000196101006001841615020190911604808252602090910190899080156108605780601f1061083557610100808354040283529160200191610860565b820191906000526020600020905b81548152906001019060200180831161084357829003601f168201915b50509850505050505050505060405180910390a160009150610cff565b5b5b600101610473565b6108903361137d565b15610b285760038054600181016108a783826113fa565b91600052602060002090602091828204019190065b815460ff808a166101009390930a928302920219161790555060048054600181016108e78382611434565b916000526020600020900160005b5085905550600580546001810161090c8382611434565b916000526020600020900160005b508490555060068054600181016109318382611434565b916000526020600020900160005b8154600160a060020a033381166101009390930a92830292021916179055507fbf474e795141390215f4f179557402a28c562b860f7b74dce4a3c0e0604cd97e60006001600288888860405160ff841660608201526080810183905260a0810182905260c080825287546002610100600183161502600019019091160490820181905281906020820190604083019060e08401908b9080156106195780601f106105ee57610100808354040283529160200191610619565b820191906000526020600020905b8154815290600101906020018083116105fc57829003601f168201915b505084810383528954600260001961010060018416150201909116048082526020909101908a90801561068d5780601f106106625761010080835404028352916020019161068d565b820191906000526020600020905b81548152906001019060200180831161067057829003601f168201915b50508481038252885460026000196101006001841615020190911604808252602090910190899080156107015780601f106106d657610100808354040283529160200191610701565b820191906000526020600020905b8154815290600101906020018083116106e457829003601f168201915b5050995050505050505050505060405180910390a160019150610cff565b7fc585b66a303b685f03874907af9278712998ea1acfed37bcb4277da02cddb8b46000600160028888883360405160ff851660608201526080810184905260a08101839052600160a060020a03821660c082015260e080825288546002610100600183161581026000190190921604918301829052829160208301916040840191908401908c908015610bfc5780601f10610bd157610100808354040283529160200191610bfc565b820191906000526020600020905b815481529060010190602001808311610bdf57829003601f168201915b505084810383528a54600260001961010060018416150201909116048082526020909101908b908015610c705780601f10610c4557610100808354040283529160200191610c70565b820191906000526020600020905b815481529060010190602001808311610c5357829003601f168201915b505084810382528954600260001961010060018416150201909116048082526020909101908a908015610ce45780601f10610cb957610100808354040283529160200191610ce4565b820191906000526020600020905b815481529060010190602001808311610cc757829003601f168201915b50509a505050505050505050505060405180910390a1600091505b5b509392505050565b610d10611488565b610d18611488565b610d20611488565b610d28611488565b610d30611488565b610d38611488565b610d40611488565b6000610d4a611488565b600754600090600160a060020a031663fa69efbd82604051602001526040518163ffffffff1660e060020a028152600401602060405180830381600087803b1515610d9457600080fd5b6102c65a03f11515610da557600080fd5b50505060405180519050925082604051805910610dbf5750595b908082528060200260200182016040525b509150600090505b82811015610e7857600754600160a060020a0316633ffefe4e8260006040516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b1515610e3157600080fd5b6102c65a03f11515610e4257600080fd5b50505060405180519050828281518110610e5857fe5b600160a060020a039092166020928302909101909101525b600101610dd8565b60006001600260036004600587868054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610f1a5780601f10610eef57610100808354040283529160200191610f1a565b820191906000526020600020905b815481529060010190602001808311610efd57829003601f168201915b50505050509650858054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610fb65780601f10610f8b57610100808354040283529160200191610fb6565b820191906000526020600020905b815481529060010190602001808311610f9957829003601f168201915b50505050509550848054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156110525780601f1061102757610100808354040283529160200191611052565b820191906000526020600020905b81548152906001019060200180831161103557829003601f168201915b50505050509450838054806020026020016040519081016040528092919081815260200182805480156110c257602002820191906000526020600020906000905b825461010083900a900460ff168152602060019283018181049485019490930390920291018084116110935790505b505050505093508280548060200260200160405190810160405280929190818152602001828054801561111557602002820191906000526020600020905b81548152600190910190602001808311611100575b505050505092508180548060200260200160405190810160405280929190818152602001828054801561116857602002820191906000526020600020905b81548152600190910190602001808311611153575b5050505050915099509950995099509950995099505b50505090919293949596565b611192611488565b600061119c611488565b600754600090600160a060020a031663fa69efbd82604051602001526040518163ffffffff1660e060020a028152600401602060405180830381600087803b15156111e657600080fd5b6102c65a03f115156111f757600080fd5b505050604051805190509250826040518059106112115750595b908082528060200260200182016040525b509150600090505b828110156112ca57600754600160a060020a0316633ffefe4e8260006040516020015260405160e060020a63ffffffff84160281526004810191909152602401602060405180830381600087803b151561128357600080fd5b6102c65a03f1151561129457600080fd5b505050604051805190508282815181106112aa57fe5b600160a060020a039092166020928302909101909101525b60010161122a565b8193505b50505090565b6112dc611488565b60018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156113725780601f1061134757610100808354040283529160200191611372565b820191906000526020600020905b81548152906001019060200180831161135557829003601f168201915b505050505090505b90565b600754600090600160a060020a03166363a9c3d783836040516020015260405160e060020a63ffffffff8416028152600160a060020a039091166004820152602401602060405180830381600087803b15156113d857600080fd5b6102c65a03f115156113e957600080fd5b50505060405180519150505b919050565b81548183558181151161142e57601f016020900481601f0160209004836000526020600020918201910161142e91906114d0565b5b505050565b81548183558181151161142e5760008381526020902061142e9181019083016114d0565b5b505050565b81548183558181151161142e5760008381526020902061142e9181019083016114d0565b5b505050565b60206040519081016040526000815290565b60206040519081016040526000815290565b60206040519081016040526000815290565b60206040519081016040526000815290565b61137a91905b808211156114ea57600081556001016114d6565b5090565b90565b61137a91905b808211156114ea57600081556001016114d6565b5090565b905600a165627a7a72305820958fb298003e940fa9c4add7c4ec995a2ac6119122942741a88cb6e9625368720029";

    protected Evidence(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Evidence(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<AddSignaturesEventEventResponse> getAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("addSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AddSignaturesEventEventResponse> responses = new ArrayList<AddSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddSignaturesEventEventResponse> addSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("addSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddSignaturesEventEventResponse>() {
            @Override
            public AddSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AddSignaturesEventEventResponse typedResponse = new AddSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public List<NewSignaturesEventEventResponse> getNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("newSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<NewSignaturesEventEventResponse> responses = new ArrayList<NewSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewSignaturesEventEventResponse> newSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("newSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewSignaturesEventEventResponse>() {
            @Override
            public NewSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                NewSignaturesEventEventResponse typedResponse = new NewSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public List<ErrorNewSignaturesEventEventResponse> getErrorNewSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorNewSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorNewSignaturesEventEventResponse> responses = new ArrayList<ErrorNewSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorNewSignaturesEventEventResponse> errorNewSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorNewSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorNewSignaturesEventEventResponse>() {
            @Override
            public ErrorNewSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorNewSignaturesEventEventResponse typedResponse = new ErrorNewSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public List<ErrorAddSignaturesEventEventResponse> getErrorAddSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorAddSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorAddSignaturesEventEventResponse> responses = new ArrayList<ErrorAddSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorAddSignaturesEventEventResponse> errorAddSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorAddSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorAddSignaturesEventEventResponse>() {
            @Override
            public ErrorAddSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorAddSignaturesEventEventResponse typedResponse = new ErrorAddSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(6).getValue();
                return typedResponse;
            }
        });
    }

    public List<AddRepeatSignaturesEventEventResponse> getAddRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("addRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AddRepeatSignaturesEventEventResponse> responses = new ArrayList<AddRepeatSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AddRepeatSignaturesEventEventResponse> addRepeatSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("addRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AddRepeatSignaturesEventEventResponse>() {
            @Override
            public AddRepeatSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AddRepeatSignaturesEventEventResponse typedResponse = new AddRepeatSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.info = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public List<ErrorRepeatSignaturesEventEventResponse> getErrorRepeatSignaturesEventEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("errorRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ErrorRepeatSignaturesEventEventResponse> responses = new ArrayList<ErrorRepeatSignaturesEventEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
            typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.addr = (String) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ErrorRepeatSignaturesEventEventResponse> errorRepeatSignaturesEventEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("errorRepeatSignaturesEvent", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Address>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ErrorRepeatSignaturesEventEventResponse>() {
            @Override
            public ErrorRepeatSignaturesEventEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ErrorRepeatSignaturesEventEventResponse typedResponse = new ErrorRepeatSignaturesEventEventResponse();
                typedResponse.evi = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.id = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.v = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.r = (byte[]) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.s = (byte[]) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.addr = (String) eventValues.getNonIndexedValues().get(5).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> signersAddr() {
        Function function = new Function("signersAddr", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> addSignatures(BigInteger v, byte[] r, byte[] s) {
        Function function = new Function(
                "addSignatures", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>> getEvidence() {
        final Function function = new Function("getEvidence", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Bytes32>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>>(
                new Callable<Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>>() {
                    @Override
                    public Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);;
                        return new Tuple7<String, String, String, List<BigInteger>, List<byte[]>, List<byte[]>, List<String>>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (List<BigInteger>) results.get(3).getValue(), 
                                (List<byte[]>) results.get(4).getValue(), 
                                (List<byte[]>) results.get(5).getValue(), 
                                (List<String>) results.get(6).getValue());
                    }
                });
    }

    public RemoteCall<List<String>> getSigners() {
        Function function = new Function("getSigners", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function, List<String>.class);
    }

    public RemoteCall<String> getEvidenceInfo() {
        Function function = new Function("getEvidenceInfo", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<Boolean> CallVerify(String addr) {
        Function function = new Function("CallVerify", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(addr)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evi), 
                new org.web3j.abi.datatypes.Utf8String(info), 
                new org.web3j.abi.datatypes.Utf8String(id), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.Address(addr), 
                new org.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<Evidence> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String evi, String info, String id, BigInteger v, byte[] r, byte[] s, String addr, String sender) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(evi), 
                new org.web3j.abi.datatypes.Utf8String(info), 
                new org.web3j.abi.datatypes.Utf8String(id), 
                new org.web3j.abi.datatypes.generated.Uint8(v), 
                new org.web3j.abi.datatypes.generated.Bytes32(r), 
                new org.web3j.abi.datatypes.generated.Bytes32(s), 
                new org.web3j.abi.datatypes.Address(addr), 
                new org.web3j.abi.datatypes.Address(sender)));
        return deployRemoteCall(Evidence.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static Evidence load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Evidence load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Evidence(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class AddSignaturesEventEventResponse {
        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;
    }

    public static class NewSignaturesEventEventResponse {
        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class ErrorNewSignaturesEventEventResponse {
        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class ErrorAddSignaturesEventEventResponse {
        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }

    public static class AddRepeatSignaturesEventEventResponse {
        public String evi;

        public String info;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;
    }

    public static class ErrorRepeatSignaturesEventEventResponse {
        public String evi;

        public String id;

        public BigInteger v;

        public byte[] r;

        public byte[] s;

        public String addr;
    }
}
