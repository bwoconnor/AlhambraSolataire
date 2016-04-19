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

public class ReserveToWasteMove extends Move {
	Pile from;
	Card cardBeingDragged;
	Pile waste;
	
	public ReserveToWasteMove(Pile from, Card cardBeingDragged, Pile to){
		this.from = from;
		this.cardBeingDragged = cardBeingDragged;
		this.waste = to;
	}

	@Override
	public boolean doMove(Solitaire game) {
		if(!valid(game)) {return false;}
		waste.add(cardBeingDragged);
		return true;
	}

	@Override
	public boolean undo(Solitaire game) {
		from.add(waste.get());
		return true;
	}

	@Override
	public boolean valid(Solitaire game) {
		if(waste.suit() == cardBeingDragged.getSuit()){
		if((waste.rank()-1) == cardBeingDragged.getRank() || (waste.rank()+1) == cardBeingDragged.getRank()){
				return true;
			}
		}
		//not a match
		return false;
	}

}
