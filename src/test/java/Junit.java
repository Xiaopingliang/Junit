import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Junit {

    //方法1，直接将参数放到链接中访问
    @Test
    public void gettest(){

        get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived").prettyPeek();

    }

    //方法2，将参数存放在map中访问
    @Test
    public void gettest2(){

        Map<String,Object> map =new HashMap<String ,Object>();
        map.put("limit",2);
        map.put("offset",0);
        map.put("type","last_actived");

        given().params(map).get("https://testerhome.com/api/v3/topics.json").prettyPeek();

    }

    ////方法3，传参数访问
    @Test
    public void gettest3(){

        given().param("limit",2).param("offset",2).param("type","last_actived").get("https://testerhome.com/api/v3/topics.json").prettyPeek();


    }

    //方法4，get(url,params)方式
    @Test
    public void gettest4(){

        get("https://testerhome.com/{topics}/{topocid}","topics",12192).prettyPeek();

    }

    //使用jsonpath校验接口返回值
    @Test
    public void Response(){

        Response response = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived");

        List list = response.jsonPath().getList("topics");

        System.out.println(list.size());

        String id  =  response.jsonPath().getString("topics[1].id");

        System.out.println(id);



    }




}
