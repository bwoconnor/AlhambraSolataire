package bwoconnor;

import java.awt.event.MouseEvent;

import junit.framework.TestCase;
import ks.client.gamefactory.GameWindow;
import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.view.Widget;
import ks.launcher.Main;

public class TestMoves extends TestCase {
	Alhambra alhambra;
	GameWindow gw;
	
	public MouseEvent createPressed (Solitaire game, Widget view, int dx, int dy) {
		MouseEvent me = new MouseEvent(game.getContainer(), MouseEvent.MOUSE_PRESSED, 
				System.currentTimeMillis(), 0, 
				view.getX()+dx, view.getY()+dy, 0, false);
		return me;
	}
	
	@Override
	protected void setUp(){
		alhambra = new Alhambra();
		gw = Main.generateWindow(alhambra, Deck.OrderBySuit);
	}
	
	@Override
	protected void tearDown(){
		
	}
	
	public void testDealCard(){
		Card topCard =alhambra.deck.peek();
		DealCardMove dcm = new DealCardMove(alhambra.deck, alhambra.wastePile);
		assertEquals(64, alhambra.deck.count());
		assertTrue(dcm.valid(alhambra));
		
		dcm.doMove(alhambra);
		
		assertEquals(63, alhambra.deck.count());
		assertEquals(topCard, alhambra.wastePile.peek());
		int value = alhambra.getNumLeft().getValue();
		assertEquals(63, alhambra.deck.count());
		assertEquals(63, value);
		
		dcm.undo(alhambra);
		assertEquals(64, alhambra.deck.count());
	}
	
	public void testFoundationMove(){
		Card card = alhambra.reserve[2].get();
		PileToKingMove PTK = new PileToKingMove(alhambra.reserve[2], card, alhambra.kingClub);
		
		assertEquals(card.getRank(), 12);
		assertEquals(card.getSuit(), 1);
		assertTrue(PTK.valid(alhambra));
		
		PTK.doMove(alhambra);
		
		assertEquals(alhambra.kingClub.count(), 2);
		
		PTK.undo(alhambra);
		
		assertEquals(alhambra.kingClub.count(), 1);
		
		
		card = alhambra.reserve[6].get();
		PileToAceMove PTA = new PileToAceMove(alhambra.reserve[6], card, alhambra.aceHeart);
		assertTrue(PTA.valid(alhambra));
		
		PTA.doMove(alhambra);
		
		assertEquals(alhambra.aceHeart.count(), 2);
		
		PTA.undo(alhambra);
		
		assertEquals(alhambra.aceHeart.count(), 1);
	}
	
	public void testReserveToWaste(){
		DealCardMove dcm = new DealCardMove(alhambra.deck, alhambra.wastePile);
		dcm.doMove(alhambra);
		Card card = alhambra.reserve[7].get();
		ReserveToWasteMove RTM = new ReserveToWasteMove(alhambra.reserve[7], card, alhambra.wastePile);
		
		assertTrue(RTM.valid(alhambra));
		
		RTM.doMove(alhambra);
		
		assertEquals(alhambra.wastePile.count(), 2);
		
		RTM.undo(alhambra);
		
		assertEquals(alhambra.wastePile.count(), 1);
	}
	
	public void testReconstituteDeckMove(){
		for(int i = 0; i <=64; i++){
			DealCardMove dcm = new DealCardMove(alhambra.deck, alhambra.wastePile);
			dcm.doMove(alhambra);
		}
		assertEquals(0, alhambra.deck.count());
		
		ReconstituteDeckMove RDM = new ReconstituteDeckMove(alhambra.deck, alhambra.wastePile, 0);
		
		assertTrue(RDM.valid(alhambra));
		
		RDM.doMove(alhambra);
		
		RDM.undo(alhambra);
	}
	
	public void testDeckController() {

		// first create a mouse event
		MouseEvent pr = createPressed (alhambra, alhambra.deckView, 0, 0);
		alhambra.deckView.getMouseManager().handleMouseEvent(pr);
		
		assertEquals (new Card(Card.SEVEN, Card.HEARTS), alhambra.wastePile.peek());
		assertTrue (alhambra.undoMove());
		assertTrue (alhambra.wastePile.empty());
		
	}
}
