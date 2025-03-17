package ec.com.technoloqie.message.api.commons.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class TelegramUtils {

	// objeto que sera instanciado
	private static TelegramUtils instance = new TelegramUtils();

	private TelegramUtils() {

	}

	public static TelegramUtils getInstance() {
		if (null == instance) {
			instance = new TelegramUtils();
		}
		return instance;
	}

	public KeyboardRow createButton(String textButton) {
		// First keyboard row
		KeyboardRow keyboardFirstRow = new KeyboardRow();
		// Add buttons to the first keyboard row
		keyboardFirstRow.add(new KeyboardButton(textButton));
		return keyboardFirstRow;
	}
	
	public KeyboardRow createRowButtons(String ...buttonsName ) {
		//keyboard row
		KeyboardRow keyboardRow = new KeyboardRow();
		for (String nameButton : buttonsName) {
			// Add buttons to the first keyboard row
			keyboardRow.add(new KeyboardButton(nameButton));
		}
		return keyboardRow;
	}

	public KeyboardButton getButtonContact(String text) {
		KeyboardButton keyboardButton = new KeyboardButton();
		keyboardButton.setText(text);
		keyboardButton.setRequestContact(true);
		return keyboardButton;
	}

	public KeyboardButton getButtonLocation(String text) {
		KeyboardButton keyboardButton = new KeyboardButton();
		keyboardButton.setText(text);
		keyboardButton.setRequestLocation(true);
		return keyboardButton;
	}
}
