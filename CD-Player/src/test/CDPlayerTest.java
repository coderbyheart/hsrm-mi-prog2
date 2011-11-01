package test;

import player.CD;
import player.CDPlayer;
import player.Song;

/**
 * Einfacher Test für den CD-Player
 * 
 * @author Markus Tacker <m@tacker.org>
 */
public class CDPlayerTest 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		for (int i = 1; i < 3; i++) {
			CD myCD = new CD();
			myCD.setTitel("CD " + i);
			for (int j = 1; j < 15; j++) {
				myCD.addSong(
					((Song)new Song().setTitel("Song " + j))
				);
			}
		}
		CDPlayer myPlayer = new CDPlayer();
	}
}
