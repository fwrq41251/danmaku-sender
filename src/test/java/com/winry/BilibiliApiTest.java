package com.winry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.winry.dto.PublicKey;
import com.winry.form.LoginForm;
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
    public void keyTest()  throws IOException{
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
        url = url + "?userid=" + userId + "&pwd=" + pwd + "&captcha=&keep=1";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        System.out.println(result);
    }

}
