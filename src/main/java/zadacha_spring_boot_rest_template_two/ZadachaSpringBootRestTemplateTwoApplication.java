package zadacha_spring_boot_rest_template_two;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import zadacha_spring_boot_rest_template_two.model.User;



//@SpringBootApplication
public class ZadachaSpringBootRestTemplateTwoApplication {


    public static void main(String[] args) {

        User newUser = new User(3L, "Kit", "Bob", (byte) 23);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<User[]> response = restTemplate.getForEntity("http://91.241.64.178:7081/api/users",User[].class);
        response.getBody();
        HttpHeaders getHeaders = response.getHeaders();
        String JSESSIONID = getHeaders.getFirst(HttpHeaders.SET_COOKIE);
        System.out.println(response);


        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, JSESSIONID);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", JSESSIONID);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> result = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.POST, requestBody, String.class);
        String сode = (String) result.getBody();


        newUser.setName("Tom");
        newUser.setLastName("Rok");
        ResponseEntity<String> responsePut = restTemplate.exchange("http://91.241.64.178:7081/api/users", HttpMethod.PUT, requestBody, String.class);
        сode += (String) responsePut.getBody();


        ResponseEntity<String> responseDel = restTemplate.exchange("http://91.241.64.178:7081/api/users/3", HttpMethod.DELETE, requestBody, String.class);
        сode += (String) responseDel.getBody();


        System.out.println(сode);

   }
}
