package bwoconnor;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.launcher.Main;

public class TestDealCardMove extends TestCase {
	public void testSimple(){
		Alhambra alhambra = new Alhambra();
		GameWindow gw = Main.generateWindow(alhambra, Deck.OrderBySuit);
		
		Card topCard =alhambra.deck.peek();
		DealCardMove dcm = new DealCardMove(alhambra.deck, alhambra.wastePile);
		
		assertTrue(dcm.valid(alhambra));
		
		dcm.doMove(alhambra);
		
		assertEquals(103, alhambra.deck.count());
		assertEquals(topCard, alhambra.wastePile.peek());
		int value = alhambra.getNumLeft().getValue();
		assertEquals(51, value);
		
		dcm.undo(alhambra);
		assertEquals(104, alhambra.deck.count());
	}
}
