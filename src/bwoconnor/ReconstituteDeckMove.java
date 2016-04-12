package bwoconnor;

import ks.common.games.Solitaire;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.Move;
import ks.common.model.MutableInteger;
import ks.common.model.Pile;

public class ReconstituteDeckMove extends Move{
	Deck deck;
	Pile pile;
	int deckFlips;
	Card c;
	int num;
	
	public ReconstituteDeckMove(Deck deck, Pile pile,int deckflips, int numInWaste){
		this.deck = deck;
		this.pile = pile;
		this.deckFlips = deckflips;
		this.num = numInWaste;
	}
	
	@Override
	public boolean doMove(Solitaire game) {
		if(!valid(game)) {return false;}
		if(deck.empty() && deckFlips<=3){
			for(int i = num; i < num; i++){
				c = pile.get();
				deck.add(c);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean undo(Solitaire game) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean valid(Solitaire game) {
		// TODO Auto-generated method stub
		return true;
	}

}
