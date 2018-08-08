import com.google.gson.JsonObject;
import com.wiwide.encryptsdk.*;
import io.restassured.response.Response;


import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class UnionCoin {
    Test test = new Test();
    Map map =new HashMap();
    String trade_no;
    String out_trade_no;


    public void Encry(){

        //  商户密钥
        String mch_secret = "a91b7a78M50364F46752G5113fb7d8bT";
        //  会话私钥   32位
        String secret = "abcdefg123456789abcdefg123456789";

        // 初始向量   16位
        String iv = "abcdefg123456789";

        //  商户公钥
        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAvh8j/zagfxQdnSh5OIicMzN+MuRuWQJPjgu4Gza4+gX3j5Ln2xNDBOTjpwyuLBjh/JcBd1cGO3lAaKCwcaixsmhTq56wVXXUMgDiAChu4ud8FSvRc8G8tdZAirKVAIi3NW+/pYgpWBs/0wnF8hz48no4pyJHl9Jc1LH3VNIMz8vqzKUPc4ack4pFUXlcNj6C+sBlaurmI4/vwLqNxBGs7/zyM7dv6oy3DSU/Y1qBArM1YPjfL2dNun8rmtPgJvlPwXqA7uoHPwQ2Ym3aUn59pkS7QI6IE8uuqNkfSte8BXLd2nIqPLFxLYLDmdll7eoyRblHcHqAYSj8stK6StC7DNryNKEjTEwbgf9trUI0uvF1pfgTy2gpclnY69FtD/m0+FvLyorMq+nmBqYMjka5K0txDQJPOa7gsi//uXd/cJW2SAXY9MSO1AfMi8Xq/YKRQzN9FW5iapskXFHca7uXg5NhH7flr6DW+QInFlpoN6WIEAuDF1aj4O49Ikm3WxwhTqnvEkdSCfivpYQkp9Sh4kQ/SQdxuT7VX+Nz6k+uMx2z4cySk33bHi0KoHbA9QFGg/54Qd0+eU4qZnd4mrghhH7/QQhL7Z9eF1U5UPrsHq2Vq3rEnN+tYQ26AuKeU8vzTxBrC/SxC6C/SMFt3f/YnuFh1UnNJZleZwyQt+ZdGO0CAwEAAQ==";
        String data = test.getData(mch_secret,secret,iv);
        String sc = test.getSc(secret,publicKey);

        map.put("data",data);
        map.put("sc",sc);
        map.put("iv",iv);
        map.put("mch_secret",mch_secret);
    }

    @org.junit.Test
    public void CreateOrder(){

        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(r.nextInt(10));
        }

        Test.clearParams();
        Test.addParam("return_url","http://baidu.com");
        Test.addParam("out_trade_no",sb.toString());
        Test.addParam("coin_type","TCC");
        Test.addParam("coin_total","0.01");
        Test.addParam("coin_address","0x25f714674f64bb0ee09d96e6694aad7e8c9bf2a9");
        Test.addParam("time_start","201807190524");
        Test.addParam("time_end","201807202355");
        Test.addParam("goods_desc","一卡通充值");
        Test.addParam("goods_detail","高飞");
        Test.addParam("rand","${__RandomString(16,,)}");
        Encry();
        Response response = given().params(map).post("https://app.tccpay.com/cashier/payment").prettyPeek();
        HashMap<String,String> return_data = response.jsonPath().getJsonObject("return_data");
        String trade_no =  return_data.get("trade_no");
        String out_trade_no = return_data.get("out_trade_no");

        Test.clearParams();

        System.out.println("a123");
        System.out.println("a456");
        System.out.println("a789");

    }

    @org.junit.Test()
    public void CheckOrder(){

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }

        test.clearParams();
        test.addParam("rand",sb.toString());
        test.addParam("out_trade_no",out_trade_no);
        test.addParam("trade_no",trade_no);
        System.out.println(out_trade_no);
        System.out.println(trade_no);
        Encry();

        given().params(map).post("https://app.tccpay.com/cashier/payquery").prettyPeek();


        System.out.println("123");
        System.out.println("456");
        System.out.println("789");



    }




}
