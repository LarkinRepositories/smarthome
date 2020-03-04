package ru.innopolis.restServiceOut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


@RestController
public class RestOutController {

    private static final Logger logger = LoggerFactory.getLogger(RestOutController.class);

//    @RequestMapping("/restOn")
//    public String restOn() throws Exception {
//        return sendGet("192.168.1.93", true);
//    }

    @RequestMapping("/test/do/")
    public String restDo(@RequestParam(name="ip")String ip, @RequestParam(name="commandId") Long commandId) throws Exception {
        return "REST-OUT: " + sendGet(ip,commandId);
    }

//    @RequestMapping("/restOff")
//    public String restOff() throws Exception {
//        return sendGet("192.168.1.93", false);
//    }

    private String sendGet(String ip, Long idCommand) throws Exception {

        String result;

        String switcher;
        if (idCommand ==1) {
            switcher = "On";
        }
        if (idCommand==0) {
            switcher = "Off";
        } else {
            switcher = null;
        }

        String url = "http://" + ip + "/cm?cmnd=Power%20" + switcher + "";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        logger.info("\nSending 'GET' request to URL : " + url);
        logger.info("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            result = response.toString();
        }
        return result;
    }

}
