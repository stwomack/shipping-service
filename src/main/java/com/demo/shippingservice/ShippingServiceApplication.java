package com.demo.shippingservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final Logger LOG = LoggerFactory.getLogger(ShippingServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ShippingServiceApplication.class, args);

	}

	@PostMapping("/shipping")
	public String processOrder(@RequestBody CustomOrder customOrder) {
		// Validate order (e.g., 90% success rate)
		if (new Random().nextInt(10) > 1) {
			// Select shipping provider
			String shippingProvider = selectRandomShippingProvider();

			// Enrich order with shipping provider
			customOrder.setShippingProvider(shippingProvider);

			// Send enriched order to Order Completion Service
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject("http://completion-service/completions", customOrder, String.class);
            LOG.info("completion service response: {}", response);
			return "Order processed successfully" + customOrder.toString();
		} else {
			return "Order failed validation" +  customOrder.toString();
		}
	}
	@GetMapping("/")
	public String respondBack() {
		LOG.info("Root site called");
		return "Shipping Service!";
	}

	private String selectRandomShippingProvider() {
		String[] providers = {"FedEx", "UPS", "USPS"};
		Random random = new Random();
		return providers[random.nextInt(providers.length)];
	}
}
