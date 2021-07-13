package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ThemesTest {

	@Test
	void testThemes() {
		Themes themes = new Themes("a", "b");
		assertEquals(themes.getTheme("person"), "a");
		assertEquals(themes.getTheme("wolf"), "b");
	}

	@Test
	void testGetTheme() {
		Themes themes = new Themes("a", "b");
		assertEquals(themes.getTheme("person"), "a");
	}
}
