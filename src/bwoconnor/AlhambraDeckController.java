package bwoconnor;

import heineman.Klondike;
import heineman.klondike.DealCardMove;
import ks.common.controller.SolitaireReleasedAdapter;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;

public class AlhambraDeckController extends SolitaireReleasedAdapter {
	/** The game. */
	protected Alhambra theGame;

	/** The WastePile of interest. */
	protected Pile wastePile;

	/** The Deck of interest. */
	protected Deck deck;

	/**
	 * KlondikeDeckController constructor comment.
	 */
	public AlhambraDeckController(Alhambra theGame, Deck d, Pile wastePile) {
		super(theGame);

		this.theGame = theGame;
		this.wastePile = wastePile;
		this.deck = d;
	}
	
	/**
	 * Coordinate reaction to the beginning of a Drag Event. In this case,
	 * no drag is ever achieved, and we simply deal upon the press.
	 */
	public void mousePressed (java.awt.event.MouseEvent me) {
		Move m1 = new ReconstituteDeckMove(deck, wastePile, theGame.deckFlips);
		if(m1.doMove(theGame)){
			theGame.updateNumberCardsLeft(theGame.deck.count());
			theGame.deckFlips++;
			theGame.pushMove (m1);
			theGame.refreshWidgets();
		}else{
		// Attempting a DealCardMove
		Move m2 = new DealCardMove (deck, wastePile);
		if (m2.doMove(theGame)) {
			theGame.pushMove (m2);     // Successful DealFour Move
			theGame.refreshWidgets(); // refresh updated widgets.
		}
		}
	}
}
