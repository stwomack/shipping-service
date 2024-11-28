package com.demo.shippingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
@RestController
public class ShippingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingServiceApplication.class, args);

	}

	@PostMapping("/shipping")
	public String processOrder(@RequestBody Order order) {
		// Validate order (e.g., 90% success rate)
		if (new Random().nextInt(10) > 1) {
			// Select shipping provider
			String shippingProvider = selectRandomShippingProvider();

			// Enrich order with shipping provider
			order.setShippingProvider(shippingProvider);

			// Send enriched order to Order Completion Service
			RestTemplate restTemplate = new RestTemplate();
//			restTemplate.postForObject("http://completion-service/completions", order, String.class);
			return "Order processed successfully" + order.toString();
		} else {
			return "Order failed validation" +  order.toString();
		}
	}
	@GetMapping("/")
	public String respondBack() {
		return "Shipping Service!";
	}

	private String selectRandomShippingProvider() {
		String[] providers = {"FedEx", "UPS", "USPS"};
		Random random = new Random();
		return providers[random.nextInt(providers.length)];
	}
}
