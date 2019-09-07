package com.isagiongo.meetupsjavagirls;

import com.isagiongo.meetupsjavagirls.enums.TipoEventoEnum;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.Talk;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MeetupsJavaGirlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetupsJavaGirlsApplication.class, args);
	}
}
