package com.example.bajaj.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    public void startChallenge() {
        try {
            RestTemplate restTemplate = new RestTemplate();

            String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

            // ✅ Request body as per instructions
            Map<String, String> body = new HashMap<>();
            body.put("name", "Vignesh");
            body.put("regNo", "PES1UG22CS688");
            body.put("email", "pes1202203613@pesu.pes.edu");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            System.out.println("🚀 Calling webhook generation endpoint...");
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            System.out.println("✅ Webhook generated successfully!");
            System.out.println(response.getBody());

            // 🧠 Extract webhook URL & token manually from response
            String responseBody = response.getBody();
            if (responseBody != null && responseBody.contains("accessToken")) {
                String webhookUrl = extractValue(responseBody, "webhook");
                String token = extractValue(responseBody, "accessToken");

                // 🧩 Your SQL query (Question 2 for even regNo)
                String sqlQuery = """
                                    SELECT 
                                        e1.EMP_ID,
                                        e1.FIRST_NAME,
                                        e1.LAST_NAME,
                                        d.DEPARTMENT_NAME,
                                        COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
                                    FROM 
                                        EMPLOYEE e1
                                    JOIN 
                                        DEPARTMENT d 
                                    ON e1.DEPARTMENT = d.DEPARTMENT_ID
                                    LEFT JOIN 
                                        EMPLOYEE e2 
                                    ON e1.DEPARTMENT = e2.DEPARTMENT 
                                    AND e2.DOB > e1.DOB
                                    GROUP BY 
                                        e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
                                    ORDER BY 
                                        e1.EMP_ID DESC;
                            """;


                sendFinalQuery(webhookUrl, token, sqlQuery);
            }

        } catch (Exception e) {
            System.out.println("❌ Error during challenge start: " + e.getMessage());
        }
    }

    private void sendFinalQuery(String webhookUrl, String token, String sqlQuery) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token); // ✅ Token directly (no Bearer prefix)

            Map<String, String> body = new HashMap<>();
            body.put("finalQuery", sqlQuery);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

            System.out.println("🚀 Submitting SQL query to: " + webhookUrl);
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, entity, String.class);

            System.out.println("✅ SQL Query submitted successfully!");
            System.out.println("Server Response: " + response.getBody());
        } catch (Exception e) {
            System.out.println("❌ Error submitting final query: " + e.getMessage());
        }
    }

    private String extractValue(String json, String key) {
        try {
            int start = json.indexOf("\"" + key + "\":");
            if (start == -1) return null;
            int firstQuote = json.indexOf("\"", start + key.length() + 3);
            int secondQuote = json.indexOf("\"", firstQuote + 1);
            return json.substring(firstQuote + 1, secondQuote);
        } catch (Exception e) {
            return null;
        }
    }
}
