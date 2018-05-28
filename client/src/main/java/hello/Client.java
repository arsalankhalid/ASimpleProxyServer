package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class Client {

	private static final Logger log = LoggerFactory.getLogger(Client.class);

	public static void main(String args[]) {
    //allow user to input resource URL

        SpringApplication.run(Client.class);

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        //  prompt for the user's name
        System.out.print("Enter your name: ");

        // get their input as a String
        String username = scanner.next();

        // prompt for their age
        System.out.print("Enter your age: ");

        // get the age as an int
        int age = scanner.nextInt();

        //output what is returned from the server

        System.out.println(String.format("%s, your age is %d", username, age));
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Quote quote = new Quote();
            HttpEntity<Quote> entity = new HttpEntity<>(quote, headers);
            ResponseEntity<Quote> responseEntity = restTemplate.exchange("http://localhost:8978/greeting", HttpMethod.GET, entity, Quote.class);
            log.info("responseEntity: " + responseEntity);

//			Quote quote = restTemplate.getForObject(
//					"http://localhost:8978/greeting", Quote.class);
//            System.out.println(quote.toString());
//			log.info(quote.toString());

		};
	}




}