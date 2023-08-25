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

import java.util.List;

@SpringBootApplication
public class RoomBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomBookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo, RoomRepository roomRepo,
										BookingRepository bookingRepo) {
		return args -> {
			List<Room> rooms = roomRepo.findAll();
			if (rooms.size() == 0) {
				Room blueRoom = new Room("Blue meeting room","1st Floor");
				blueRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.BOARD,8));
				blueRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.THEATER,16));
				roomRepo.save(blueRoom);

				Room redRoom = new Room("Red meeting room","2nd Floor");
				redRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.BOARD,12));
				redRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.USHAPE,26));
				roomRepo.save(redRoom);

				Room confRoom = new Room("Main Conference Room","1st Floor");
				confRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.THEATER,80));
				confRoom.setCapacity(new LayoutCapacity(LayoutCapacity.Layout.USHAPE,40));
				roomRepo.save(confRoom);

				User user = new User("matt", "secret");
				userRepo.save(user);

				Booking booking1 = new Booking();
				booking1.setDate(new java.sql.Date(new java.util.Date().getTime()));
				booking1.setStartTime(java.sql.Time.valueOf("11:00:00"));
				booking1.setEndTime(java.sql.Time.valueOf("11:30:00"));
				booking1.setLayout(LayoutCapacity.Layout.USHAPE);
				booking1.setParticipants(8);
				booking1.setTitle("Conference call with CEO");
				booking1.setRoom(blueRoom);
				booking1.setUser(user);
				bookingRepo.save(booking1);

				Booking booking2 = new Booking();
				booking2.setDate(new java.sql.Date(new java.util.Date().getTime()));
				booking2.setStartTime(java.sql.Time.valueOf("13:00:00"));
				booking2.setEndTime(java.sql.Time.valueOf("14:30:00"));
				booking2.setLayout(LayoutCapacity.Layout.BOARD);
				booking2.setParticipants(5);
				booking2.setTitle("Sales Update");
				booking2.setRoom(redRoom);
				booking2.setUser(user);
				bookingRepo.save(booking2);
		}

	};

	}
}
