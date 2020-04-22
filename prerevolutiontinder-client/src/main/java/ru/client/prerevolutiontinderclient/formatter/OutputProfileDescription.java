package ru.client.prerevolutiontinderclient.formatter;

import org.springframework.util.StringUtils;
import ru.client.prerevolutiontinderclient.pojo.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputProfileDescription {

	public static String preview(List<Profile> profiles) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < profiles.size(); i++) {
			str.append("|| ")
					.append(i + 1)
					.append(". ")
					.append(profiles.get(i).getName())
					.append(" ||\n");
		}
		return str.toString();
	}


	public static String out(Profile profile) {
		StringBuilder str = new StringBuilder();
		str.append("- ")
				.append(profile.getName())
				.append(" ")
				.append(profile.getDescription())
				.append(" -\n");
		int len = str.length();
		str.insert(0, getHead(len))
				.append(getHead(len));
		return str.toString();
	}

	private static String getHead(Integer len) {
		return IntStream.range(0, len).mapToObj(i -> "-").collect(Collectors.joining("")) + "\n";
	}
}
