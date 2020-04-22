package ru.client.prerevolutiontinderclient.messages;

public enum Messages {
	SUCCESS("|| Успехъ ||\n"),
	FAILED("|| Неудача, попробуйте снова ||\n"),
	ENDPROFILES("|| На сегодня анкеты закончились ||\n"),
	STARTCOMMANDS("Влево\nВправо\nАнкета\nЛюбимцы\n"),
	NEEDAUTH("|| Авторизуйтесь для данного действия ||\n"),
	PROFILECOMMANDS("Войти\nНовая\nИзменение\nУдалить\n"),
	COMMANDNEW("|| Вы сударь или сударыня? Укажите ваше секретное имя. " +
			"Ваш секретный шифр? Ваше публичное имя. Какие вы и что вы ищете? ||\n"),
	EXAMPLEPROF("Новая сударь geroy д0л0йцарR \"Мечтательный анархистъ\" \"жеоает девушку в постель\"\n"),
	SUCCESSEDIT("|| Успехъ. Ваша анкета: ||\n"),
	SUCCESSDEL("|| Ваша анкета удалена безвозвратно ||\n"),
	MATCH("|| Вы любимы ||\n"),
	EDIT("|| Какие вы и что вы ищете? ||\n"),
	EMPTYMATCH("|| Совпадений пока нет ||\n"),
	INVALIDNUM("|| Такого номера нет ||\n");

	public final String label;

	Messages(String label) {
		this.label = label;
	}

	public String getMessages() {
		return label;
	}
}
