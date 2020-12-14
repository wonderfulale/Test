package did;

import java.util.Scanner;

import static did.HelloWorld.deploy;

public class test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入拥有的信息m：");
        String m = input.next();
        //初始化JAVA SDK
        String configFile = "config-example.toml";
        BcosSDK sdk =  BcosSDK.build(configFile);
        // 为群组1初始化client
        Client client = sdk.getClient(Integer.valueOf(1));

        // 获取群组1的块高
        BlockNumber blockNumber = client.getBlockNumber();

        // 向群组1部署HelloWorld合约
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        HelloWorld helloWorld = HelloWorld.deploy(client, cryptoKeyPair);
        /* 调用HelloWorld合约的get接口 */
        RemoteCall<String> getValue = helloWorld.get();

        // 调用HelloWorld合约的set接口
        TransactionReceipt receipt = helloWorld.set(m);
    }
}

