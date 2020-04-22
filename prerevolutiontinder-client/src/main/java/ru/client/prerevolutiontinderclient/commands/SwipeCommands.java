package ru.client.prerevolutiontinderclient.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.client.prerevolutiontinderclient.service.ProfileService;

@ShellComponent
public class SwipeCommands {

	private final Logger logger = LoggerFactory.getLogger(SwipeCommands.class);

	private ProfileService profileService;

	@Autowired
	public SwipeCommands(ProfileService profileService) {
		this.profileService = profileService;
	}

	@ShellMethod(value = "Свайп влево", key = "Влево")
	public String swipeLeft() {
		logger.debug("Свайп влево");
		return profileService.swipeLeft();
	}

	@ShellMethod(value = "Свайп вправо", key = "Вправо")
	public String swipeRight() {
		logger.debug("Свайп вправо");
		return profileService.swipeRight();
	}

}
