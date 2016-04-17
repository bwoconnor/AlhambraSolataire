package bwoconnor;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.Pile;
/**
 * 
 * @author Brendan
 * Move card from wastePile to the top of an aceFoundationPile.
 */

public class WasteToKingMove extends Move {
	Pile wastePile;
	Card cardBeingDragged;
	Pile foundation;
	
	public WasteToKingMove(Pile from, Card cardBeingDragged, Pile to){
		this.wastePile = from;
		this.cardBeingDragged = cardBeingDragged;
		this.foundation = to;
	}

	@Override
	public boolean doMove(Solitaire game) {
		if(!valid(game)) {return false;}
		foundation.add(cardBeingDragged);
		game.updateScore(1);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		wastePile.add(foundation.get());
		game.updateScore(-1);
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		if(wastePile.empty()){return false;}
		if((foundation.rank()-1) == cardBeingDragged.getRank()){
			if(foundation.peek(0).getRank() == 13){
				if(foundation.suit() == cardBeingDragged.getSuit()){
					return true;
				}
			}
		}
		//not a match
		return false;
	}

}
