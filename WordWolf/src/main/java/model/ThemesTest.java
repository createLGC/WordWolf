package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ThemesTest {

	@Test
	void testThemes() {
		fail("まだ実装されていません");
	}

	@Test
	void testGetTheme() {
		Themes themes = new Themes("a", "b");
		assertEquals(themes.getTheme("person"), "a");
	}

	@Test
	void testGetRole() {
	   Themes themes = new Themes("a","b");
	   assertEquals(themes.getRole("a"),"person");
	}

}
