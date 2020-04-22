package ru.client.prerevolutiontinderclient.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.client.prerevolutiontinderclient.service.AuthService;

@ShellComponent
public class AuthCommands {

	private final Logger logger = LoggerFactory.getLogger(AuthCommands.class);

	private AuthService authService;

	@Autowired
	public AuthCommands(AuthService authService) {
		this.authService = authService;
	}

	@ShellMethod(value = "Вход", key = "Войти")
	public String login(@ShellOption(defaultValue = ShellOption.NULL) String login,
						@ShellOption(defaultValue = ShellOption.NULL) String pswd) {
		logger.debug("Попытка авторизации");
		return authService
				.login(login, pswd);
	}

	@ShellMethod(value = "Выйти из приложения", key = "Выйти")
	public String logOut() {
		logger.debug("Попытка логаута");
		return authService.logOut();
	}
}
