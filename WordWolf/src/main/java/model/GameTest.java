package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.ThemeDAO;

class GameTest {
	private String[] playerNames = new String[]{"a", "b", "c"};

	@Test
	void testGame() {
	}
	
	void testGetPlayers() {
	}

	@Test
	void testSetWolves() {
		Game game = new Game(this.playerNames);
		game.setWolves(3);
		List<Player> players = game.getPlayers();
		int numOfWolves = 0;
		for(Player player: players) {
			if(player.getRole().equals("wolf"))
				numOfWolves++;
		}
		assertEquals(numOfWolves, 3);
	}

	@Test
	void testSetTalkTime() {
		Game game = new Game();
		game.setTalkTime("10:20");
		assertEquals(game.getTalkTime(), 620);
	}

	@Test
	void testSetThemes() {
		List<String> themes = ThemeDAO.find("ブランド");
		Game game = new Game(this.playerNames);
		game.setWolves(3);
		game.setThemes(themes);
		for(Player player: game.getPlayers()) {
			if(player.getRole().equals("person"))
				assertEquals(themes.get(0), player.getTheme());
			else if(player.getRole().equals("wolf"))
				assertEquals(themes.get(1), player.getTheme());
			else
				fail("player.role is invalid.");
		}
	}

}
