package ajou.gram.moim.service;

import ajou.gram.moim.dto.KakaoDto;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class OAuthService {

    public String getKakaoAccessToken(String code, HttpServletRequest request) {
        String hostDomain = request.getRequestURL().toString();
        hostDomain = hostDomain.substring(0, hostDomain.lastIndexOf("/"));
        String accessToken = null;
        String refreshToken = null;
        String requestUrl = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            kakaoBuilder(code, hostDomain, sb);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            Object object = parser.parse(result);
            JSONObject jsonObject = (JSONObject) object;

            accessToken = (String) jsonObject.get("access_token");
            refreshToken = (String) jsonObject.get("refresh_token");

            br.close();
            bw.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    public KakaoDto getKakaoUserInfo(String token) {
        String requestUrl = "https://kapi.kakao.com/v2/user/me";
        KakaoDto kakaoDto = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            Object object = parser.parse(result);
            JSONObject jsonObject = (JSONObject) object;
            JSONObject properties = (JSONObject) jsonObject.get("properties");

            long id = (long) jsonObject.get("id");
            String profileImage = (String) properties.get("profile_image");
            String nickName = (String) properties.get("nickname");
            kakaoDto = new KakaoDto(id, nickName, profileImage);

            br.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return kakaoDto;
    }

    private static void kakaoBuilder(String code, String hostDomain, StringBuilder sb) {
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=27769c331d08ceb2033e090a83e1e212");
        sb.append("&redirect_uri=" + hostDomain + "/kakaologin");
        sb.append("&code=" + code);
    }
}
