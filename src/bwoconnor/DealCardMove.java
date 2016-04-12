package bwoconnor;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;


public class DealCardMove extends Move {
	Deck deck;
	Pile pile;
	
	public DealCardMove(Deck deck, Pile pile){
		this.deck = deck;
		this.pile = pile;
	}

	@Override
	public boolean doMove(Solitaire game) {
		if(!valid(game)) {return false;}
		
		Card card = deck.get();
		pile.add(card);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		Card c = pile.get();
		deck.add(c);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		return !deck.empty();
	}

}
