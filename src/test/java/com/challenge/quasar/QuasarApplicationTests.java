package com.challenge.quasar;

import com.challenge.quasar.entities.Location;
import com.challenge.quasar.entities.Satellite;
import com.challenge.quasar.services.LocationService;
import com.challenge.quasar.services.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuasarApplicationTests {

	@Autowired
	private LocationService locationService;

	@Autowired
	private MessageService messageService;

	@Test
	public void checkLocation() throws Exception {
		List<Satellite> satelliteList = new ArrayList<Satellite>();
		satelliteList.add(new Satellite("sato", 142.7, null));
		satelliteList.add(new Satellite("skywalker", 115.5, null));
		satelliteList.add(new Satellite("kenobi", 100, null));
		Location calculatedLocation = locationService.getLocation(satelliteList);
		Location expectedLocation = new Location(new double[]{-58.3, -69.6});
		assertEquals(expectedLocation.getX(), calculatedLocation.getX());
		assertEquals(expectedLocation.getY(), calculatedLocation.getY());
	}

	@Test
	public void checkMessage() throws Exception {
		List<String[]> messageList = new ArrayList<String[]>();
		messageList.add(new String[] {"Contenido", "", "mensaje", "", "prueba"});
		messageList.add(new String[] {"", "del", "", "", ""});
		messageList.add(new String[] {"", "", "", "de", ""});
		String decodedMessage = messageService.decodeMessage(messageList);
		String expectedMessage = "Contenido del mensaje de prueba";
		assertEquals(decodedMessage, expectedMessage);
	}

}
