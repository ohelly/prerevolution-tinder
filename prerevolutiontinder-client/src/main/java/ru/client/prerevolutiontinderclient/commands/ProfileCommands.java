package ru.client.prerevolutiontinderclient.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.client.prerevolutiontinderclient.messages.Messages;
import ru.client.prerevolutiontinderclient.service.ProfileService;

@ShellComponent
public class ProfileCommands {

	private final Logger logger = LoggerFactory.getLogger(ProfileCommands.class);

	private ProfileService profileService;

	@Autowired
	public ProfileCommands(ProfileService profileService) {
		this.profileService = profileService;
	}

	@ShellMethod(value = "Просмотр и изменение анкеты", key = "Анкета")
	public String profileShow() {
		return Messages.PROFILECOMMANDS.getMessages();
	}

	@ShellMethod(value = "Создание новой анкеты", key = "Новая")
	public String signUp(@ShellOption(defaultValue = ShellOption.NULL) String sex,
						 @ShellOption(defaultValue = ShellOption.NULL) String login,
						 @ShellOption(defaultValue = ShellOption.NULL) String pswd,
						 @ShellOption(defaultValue = ShellOption.NULL) String name,
						 @ShellOption(defaultValue = ShellOption.NULL) String desc) {
		logger.debug("Попытка создания новой анкеты");
		return profileService.createProfile(sex, login, pswd, name, desc);
	}

	@ShellMethod(value = "Изменение анкеты", key = "Изменение")
	public String edit(@ShellOption(defaultValue = ShellOption.NULL) String desc) {
		logger.debug("Попытка изменения анкеты");
		return profileService.edit(desc);
	}

	@ShellMethod(value = "Удаление анкеты", key = "Удалить")
	public String delete() {
		logger.debug("Удаление анкеты");
		return profileService.delete();
	}

	@ShellMethod(value = "Вернуться к просмотру анкет", key = "Уйти")
	public String back() {
		return profileService.getProfile();
	}
}
