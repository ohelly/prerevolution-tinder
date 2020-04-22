package ru.client.prerevolutiontinderclient.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.client.prerevolutiontinderclient.service.MatchesService;

@ShellComponent
public class MatchesCommands {

	private final Logger logger = LoggerFactory.getLogger(MatchesCommands.class);

	private MatchesService service;

	@Autowired
	public MatchesCommands(MatchesService service) {
		this.service = service;
	}

	@ShellMethod(value = "Показать матчи", key = "Любимцы")
	public String matchesShow(@ShellOption(defaultValue = ShellOption.NULL) String num) {
		logger.debug("Попытка вывода матчей");
		return service
				.showMatches(num);
	}
}
