package com.bookmyshow;

import com.bookmyshow.models.BaseModel;
import com.bookmyshow.models.Region;
import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.models.ShowSeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
		System.out.println("Welcome to Book my show");
	}

}
