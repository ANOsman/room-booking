package com.anosman.roombooking;

import com.anosman.roombooking.data.BookingRepository;
import com.anosman.roombooking.data.RoomRepository;
import com.anosman.roombooking.data.UserRepository;
import com.anosman.roombooking.entities.Booking;
import com.anosman.roombooking.entities.LayoutCapacity;
import com.anosman.roombooking.entities.Room;
import com.anosman.roombooking.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.LoadTimeWeavingConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@SpringBootApplication
public class RoomBookingApplication {
	public static void main(String[] args) {
		SpringApplication.run(RoomBookingApplication.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo, RoomRepository roomRepo,
										BookingRepository bookingRepo, PasswordEncoder encoder) {
		return args -> {

			Room blueRoom = new Room("Blue meeting room","1st Floor");
			blueRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.BOARD.getDescription(),8));
			blueRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.THEATER.getDescription(),16));
			roomRepo.save(blueRoom);

			Room redRoom = new Room("Red meeting room","2nd Floor");
			redRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.BOARD.getDescription(),12));
			redRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.USHAPE.getDescription(),26));
			roomRepo.save(redRoom);

			Room confRoom = new Room("Main Conference Room","1st Floor");
			confRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.THEATER.getDescription(),80));
			confRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.USHAPE.getDescription(),40));
			roomRepo.save(confRoom);


			User user1 = new User("jane",encoder.encode("secret"), "ROLE_ADMIN", "ROLE_USER");
			User user2 = new User("joe", encoder.encode("secret"), "ROLE_USER");
			userRepo.save(user1);
			userRepo.save(user2);



			Booking booking1 = new Booking();
			booking1.setDate(LocalDate.now());
			booking1.setStartTime(LocalTime.parse("11:00:00"));
			booking1.setEndTime(LocalTime.parse("12:30:00"));
			booking1.setParticipants(8);
			booking1.setTitle("Conference call with CEO");
			booking1.setRoom(blueRoom);
			booking1.setUser(user1);
			booking1.setLayout(LayoutCapacity.Layout.USHAPE.getDescription());
			bookingRepo.save(booking1);

			Booking booking2 = new Booking();
			booking2.setDate(LocalDate.now());
			booking2.setStartTime(LocalTime.parse("13:00:00"));
			booking2.setEndTime(LocalTime.parse("14:30:00"));
			booking2.setParticipants(5);
			booking2.setTitle("Sales Update");
			booking2.setRoom(redRoom);
			booking2.setUser(user2);
			booking2.setLayout(LayoutCapacity.Layout.BOARD.getDescription());
			bookingRepo.save(booking2);

			Booking booking3 = new Booking();
			booking3.setDate(LocalDate.now());
			booking3.setStartTime(LocalTime.parse("15:00"));
			booking3.setEndTime(LocalTime.parse("16:00"));
			booking3.setParticipants(5);
			booking3.setTitle("Staff meeting");
			booking3.setRoom(redRoom);
			booking3.setUser(user2);
			booking3.setLayout(LayoutCapacity.Layout.THEATER.getDescription());
			bookingRepo.save(booking3);

			Booking booking4 = new Booking();
			booking4.setDate(LocalDate.now());
			booking4.setStartTime(LocalTime.parse("15:00"));
			booking4.setEndTime(LocalTime.parse("16:00"));
			booking4.setParticipants(5);
			booking4.setTitle("Executive meeting");
			booking4.setRoom(redRoom);
			booking4.setUser(user2);
			booking4.setLayout(LayoutCapacity.Layout.THEATER.getDescription());
			bookingRepo.save(booking4);

		};

	}
}
