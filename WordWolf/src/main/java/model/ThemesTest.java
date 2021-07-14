package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ThemesTest {

	@Test
<<<<<<< HEAD
=======
	void testThemes() {
		Themes themes = new Themes("a", "b");
		assertEquals(themes.getTheme("person"), "a");
		assertEquals(themes.getTheme("wolf"), "b");
	}

	@Test
>>>>>>> branch 'master' of https://github.com/Kota-T/WordWolf.git
	void testGetTheme() {
		Themes themes = new Themes("a", "b");
		assertEquals(themes.getTheme("person"), "a");
	}
}


