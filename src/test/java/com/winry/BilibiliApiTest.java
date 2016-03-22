package com.winry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.winry.dto.PublicKey;
import com.winry.form.LoginForm;
import com.winry.form.SendMsgForm;
import com.winry.http.ApiService;
import com.winry.util.EncryptionUtil;
import com.winry.util.JsonUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class BilibiliApiTest {

    @Autowired
    private ApiService apiService;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Test
    public void getPublicKey() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://passport.bilibili.com/login?act=getkey").build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
        PublicKey publicKey = JsonUtil.toObject(PublicKey.class, result);
        System.out.println(publicKey);
    }

    @Test
    public void encryptPassword() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // PublicKey publicKey = mapper.readValue(json, PublicKey.class);
        // String password = publicKey.getHash() + "580685";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdScM09sZJqFPX7bvmB2y6i08J"
                + "bHsa0v4THafPbJN9NoaZ9Djz1LmeLkVlmWx1DwgHVW+K7LVWT5FV3johacVRuV98"
                + "37+RNntEK6SE82MPcl7fA++dmW2cLlAjsIIkrX+aIvvSGCuUfcWpWFy3YVDqhuHr" + "NDjdNcaefJIQHMW+sQIDAQAB";
        byte[] bytes = Base64.getDecoder().decode(publicKey.getBytes("utf-8"));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        java.security.PublicKey key = keyFactory.generatePublic(spec);
        String encrypted = EncryptionUtil.encrypt("580685", key);
        System.out.println(encrypted);
    }

    @Test
    public void jsonTest() throws IOException {
        String json = Files.toString(new File("key"), Charset.forName("utf-8"));
        ObjectMapper mapper = new ObjectMapper();
        PublicKey publicKey = mapper.readValue(json, PublicKey.class);
        System.out.println(publicKey);
    }

    @Test
    public void bese64Test() throws UnsupportedEncodingException {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdScM09sZJqFPX7bvmB2y6i08J"
                + "bHsa0v4THafPbJN9NoaZ9Djz1LmeLkVlmWx1DwgHVW+K7LVWT5FV3johacVRuV98"
                + "37+RNntEK6SE82MPcl7fA++dmW2cLlAjsIIkrX+aIvvSGCuUfcWpWFy3YVDqhuHr" + "NDjdNcaefJIQHMW+sQIDAQAB";
        byte[] bytes = Base64.getDecoder().decode(publicKey.getBytes("utf-8"));
        System.out.println(new String(bytes));
    }

    @Test
    public void keyTest() throws IOException {
        String json = Files.toString(new File("key"), Charset.forName("utf-8"));
        ObjectMapper mapper = new ObjectMapper();
        PublicKey publicKey = mapper.readValue(json, PublicKey.class);
        String key = publicKey.getKey();
        key = StringUtils.remove(key, "\n");
        key = StringUtils.removeStart(key, "-----BEGIN PUBLIC KEY-----");
        key = StringUtils.removeEnd(key, "-----END PUBLIC KEY-----");
        System.out.println(key);
    }


    @Test
    public void loginTest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        PublicKey publicKey = apiService.getPublicKey();
        String userId = this.username;
        java.security.PublicKey key = EncryptionUtil.getPublicKey(publicKey.getRawKey());
        String pwd = EncryptionUtil.encrypt(publicKey.getHash() + this.password, key);
        LoginForm form = new LoginForm();
        form.userId = userId;
        form.pwd = pwd;
        RequestBody formBody = form.build();
        String url = "https://passport.bilibili.com/ajax/miniLogin/login";
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,ja;q=0.6,en;q=0.4")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Cookie", "sid=ku9cju8b; JSESSIONID=7EF6E848FA5E37F0D6669135866DB016")
//                .addHeader("DNT", "1")
//                .addHeader("Host", "passport.bilibili.com")
//                .addHeader("Origin", "https://passport.bilibili.com")
//                .addHeader("Referer", "https://passport.bilibili.com/ajax/miniLogin/minilogin")
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 7Star/1.45.0.415 Safari/537.36")
//                .addHeader("X-Requested-With", "XMLHttpRequest")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

    @Test
    public void sendTest()  throws IOException {
        OkHttpClient client = new OkHttpClient();
        SendMsgForm form = new SendMsgForm();
        form.msg = "test2";
        form.roomId = "1004";
        RequestBody formBody = form.build();
        String url = "http://live.bilibili.com/msg/send";
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,ja;q=0.6,en;q=0.4")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Cookie", "sid=ku9cju8b; JSESSIONID=7EF6E848FA5E37F0D6669135866DB016")
//                .addHeader("DNT", "1")
//                .addHeader("Host", "passport.bilibili.com")
//                .addHeader("Origin", "https://passport.bilibili.com")
//                .addHeader("Referer", "https://passport.bilibili.com/ajax/miniLogin/minilogin")
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 7Star/1.45.0.415 Safari/537.36")
//                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader("Cookie", "sid=ku9cju8b; gourl=https%3A%2F%2Fpassport.bilibili.com%2Fajax%2FminiLogin%2Fredirect; DedeUserID=241087; DedeUserID__ckMd5=7e772bf49d639da6; SESSDATA=ed6f17cd%2C1458748747%2C28f1c5bd; LIVE_LOGIN_DATA=39466ccf4164c72115811c3b6000f11da5046f23; LIVE_LOGIN_DATA__ckMd5=7a3fb245279c519a; pgv_pvi=9374230528; pgv_si=s670007296; fts=1458662359; LIVE_BUVID=125a60263d33f984c66211420b219bbc; LIVE_BUVID__ckMd5=ea7720c1ef3760cd; user_face=http%3A%2F%2Fi2.hdslb.com%2Fbfs%2Fface%2F783abe4022f11ccda330f08006c149a18f388ea5.jpg; _dfcaptcha=71c9d1052ac603436ca7385615cc947e; CNZZDATA2724999=cnzz_eid%3D1124197609-1458662349-http%253A%252F%252Flive.bilibili.com%252F%26ntime%3D1458664379; attentionData=%7B%22code%22%3A0%2C%22msg%22%3A%22%22%2C%22data%22%3A%7B%22count%22%3A2%2C%22hb%22%3A%2272960_7065799%22%2C%22open%22%3A1%2C%22has_new%22%3A0%7D%7D")
                .addHeader("Cookie", "DedeUserID=241087; DedeUserID__ckMd5=7e772bf49d639da6; SESSDATA=ed6f17cd%2C1458751426%2C14f99d59;")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

}
