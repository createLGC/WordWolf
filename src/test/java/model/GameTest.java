package model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.ThemeDAO;

class GameTest {
	private String[] playerNames = new String[]{"a", "b", "c", "d", "e"};

	@Test
	void testGame() {
	}

	@Test
	void testSetWolves() {
		Game game = new Game(this.playerNames);
		game.setWolves(2);
		List<Player> players = game.getPlayers();
		int numOfWolves = 0;
		for(Player player: players) {
			if(player.getRole().equals("wolf"))
				numOfWolves++;
		}
		assertEquals(numOfWolves, 2);
	}

	@Test
	void testSetTalkTime() {
		Game game = new Game();
		game.setTalkTime("10:20");
		assertEquals(game.getTalkTime(), 620);
	}

	@Test
	void testSetThemes() throws SQLException {
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
	
	@Test
	void testDecideWinner() throws SQLException {
		Game game = new Game(this.playerNames);
		game.setWolves(2);
		game.setThemes(ThemeDAO.find("ブランド"));
		List<String> wolfNames = Arrays.asList(this.playerNames);
		assertEquals(game.decideWinner(wolfNames), "person");
	}
}
