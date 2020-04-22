package ru.client.prerevolutiontinderclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.client.prerevolutiontinderclient.formatter.OutputProfileDescription;
import ru.client.prerevolutiontinderclient.messages.Messages;
import ru.client.prerevolutiontinderclient.pojo.Profile;
import ru.client.prerevolutiontinderclient.pojo.User;
import ru.client.prerevolutiontinderclient.service.main.TinderService;

import java.util.ArrayList;

@Component(value = "swipeService")
@ConfigurationProperties(prefix = "endpoint.profiles")
public class ProfileService extends TinderService {

	@Autowired
	private ServiceRequests requests;

	private String url;
	private Long incr = 0L;
	private Profile profile = new Profile();

	public String getProfile() {
		ResponseEntity<Profile> response = requests.getProfile(url, incr, httpEntity(null));
		if ((response.getBody()) != null) {
			profile = response.getBody();
			incr = profile.getId() + 1;
			return OutputProfileDescription.out(profile) + Messages.STARTCOMMANDS.getMessages();
		}
		return Messages.ENDPROFILES.getMessages() + Messages.STARTCOMMANDS.getMessages();
	}

	public String swipeLeft() {
		requests.swipe(url, profile.getId(), httpEntity(null), "dislike");
		return getProfile();
	}

	public String swipeRight() {
		ResponseEntity<Profile> response = requests.swipe(url, profile.getId(), httpEntity(profile), "like");
		if ((response.getBody()) != null) {
			if (haveMatch())
				return Messages.MATCH.getMessages() + getProfile();
			return getProfile();
		}
		return Messages.NEEDAUTH.getMessages() + Messages.STARTCOMMANDS.getMessages();
	}

	public boolean haveMatch() {
		ResponseEntity<String> response = requests.haveMatch(url, profile.getId(), httpEntity(profile));
		return response.getBody() != null;
	}

	public String createProfile(String sex, String login, String pswd, String name, String desc) {
		User user = createUser(sex, login, pswd, name, desc);
		ResponseEntity<User> response = requests.createUser(url, httpEntity(user));
		if (response.getBody() != null)
			return Messages.SUCCESSEDIT.getMessages() + OutputProfileDescription.out(response.getBody().getProfile());
		return Messages.COMMANDNEW.getMessages() + Messages.EXAMPLEPROF.getMessages();
	}

	public String edit(String desc) {
		if (desc == null)
			return Messages.EDIT.getMessages();
		ResponseEntity<User> response = requests.edit(url, httpEntity(desc));
		if (response.getBody() != null) {
			return Messages.SUCCESSEDIT.getMessages() + OutputProfileDescription.out(response.getBody().getProfile());
		}
		return Messages.NEEDAUTH.getMessages();
	}

	public String delete() {
		ResponseEntity<String> response = requests.delete(url, httpEntity(null));
		if (response.getBody() != null) {
			reset();
			headers = new HttpHeaders();
			return Messages.SUCCESSDEL.getMessages();
		}
		return Messages.NEEDAUTH.getMessages();
	}

	public User createUser(String sex, String login, String pswd, String name, String desc) {
		return new User(login, pswd, new Profile(name, desc, sex), new ArrayList<>());
	}

	public void reset() {
		incr = 0L;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
