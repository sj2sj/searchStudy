package kr.co.searchstudy.member;

import org.springframework.stereotype.Service;

import com.google.gson.*;

import java.io.*;
import java.net.*;

@Service
public class MemberService {
	
	//사용자가 카카오 계정으로 로그인하면 accessToken을 반환해준다.
	public String getKaKaoAccessToken(String code) throws Exception {
        String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=61425084922181b33c5234237ec7b1ff"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost/searchstudy/oauth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
	
	
	
	
	//반환받은 accessToken으로 사용자 정보를 확인하여 회원가입한다.
	public void createKakaoUser(String token) throws Exception {

		String reqURL = "https://kapi.kakao.com/v2/user/me";

	    //access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

	       //결과 코드가 200이라면 성공
	       int responseCode = conn.getResponseCode();
	       System.out.println("responseCode : " + responseCode);

	       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       String result = "";

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       System.out.println("response body : " + result);

	       //Gson 라이브러리로 JSON파싱
	       JsonParser parser = new JsonParser();
	       JsonElement element = parser.parse(result);

	       int id = element.getAsJsonObject().get("id").getAsInt();
	       boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
	       String email = "";
	       if(hasEmail){
	           email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
	       }

	       System.out.println("id : " + id);
	       System.out.println("email : " + email);

	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	 }
}
