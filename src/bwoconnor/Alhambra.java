package bwoconnor;


import ks.common.controller.SolitaireMouseMotionAdapter;
import ks.common.games.Solitaire;
import ks.common.games.SolitaireUndoAdapter;
import ks.common.model.Card;
import ks.common.model.Deck;
import ks.common.model.MultiDeck;
import ks.common.model.Pile;
import ks.common.view.CardImages;
import ks.common.view.DeckView;
import ks.common.view.IntegerView;
import ks.common.view.PileView;
import ks.launcher.Main;

public class Alhambra extends Solitaire {

	
		Deck deck;
		Pile reserve[] = new Pile[8];
		Pile wastePile;
		Pile aceSpade;
		Pile aceClub;
		Pile aceDiamond;
		Pile aceHeart;
		Pile kingSpade;
		Pile kingClub;
		Pile kingDiamond;
		Pile kingHeart;
		DeckView deckView;
		PileView reserveView[] = new PileView[8];
		PileView wastePileView;
		PileView aceSpadeView;
		PileView aceClubView;
		PileView aceDiamondView;
		PileView aceHeartView;
		PileView kingSpadeView;
		PileView kingClubView;
		PileView kingDiamondView;
		PileView kingHeartView;
		IntegerView scoreView;
		IntegerView numLeftView;
		
		public int deckFlips = 0;
		public Alhambra() {
			super();
		}
	
	public int DeckFlips(){
		return deckFlips;
	}

	@Override
	public String getName() {
		return "Bwoconnor-Alhambra";
	}

	@Override
	public boolean hasWon() {
		
		return getScore().getValue() == 96;
	}

	@Override
	public void initialize() {
		// initialize model
		initializeModel(getSeed());
		initializeView();
		initializeControllers();

		//Set up all the foundation and reserve piles
		boolean spadeAce = false;
		boolean clubAce = false;
		boolean diamondAce = false;
		boolean heartAce = false;
		boolean spadeKing = false;
		boolean clubKing = false;
		boolean diamondKing = false;
		boolean heartKing = false;
		Card c;
		Card temp[] = new Card[98];
		int j = 0;
		for(int i = 0; i<104;i++){
			c = deck.get();
			if(c.getRank()==1 && c.getSuit()==1 && !clubAce){
				aceClub.add(c);
				clubAce = true;
			}else{
			if(c.getRank()==1 && c.getSuit()==2 && !diamondAce){
				aceDiamond.add(c);
				diamondAce = true;
			}else{
			if(c.getRank()==1 && c.getSuit()==3 && !heartAce){
				aceHeart.add(c);
				heartAce = true;
			}else{
			if(c.getRank()==1 && c.getSuit()==4 && !spadeAce){
				aceSpade.add(c);
				spadeAce = true;
			}else{
			if(c.getRank()==13 && c.getSuit()==1 && !clubKing){
				kingClub.add(c);
				clubKing = true;
			}else{
			if(c.getRank()==13 && c.getSuit()==2 && !diamondKing){
				kingDiamond.add(c);
				diamondKing = true;
			}else{
			if(c.getRank()==13 && c.getSuit()==3 && !heartKing){
				kingHeart.add(c);
				heartKing = true;
			}else{
			if(c.getRank()==13 && c.getSuit()==4 && !spadeKing){
				kingSpade.add(c);
				spadeKing = true;
			}else{
				temp[j++] = c;
		}}}}}}}}}
		
		for(int i = 0; i<96; i++){
			deck.add(temp[i]);
		}
		
		for(int i =0; i<8; i++){
			for(int k = 0; k<4; k++){
				c = deck.get();
				reserve[i].add(c);
			}
		}
		updateScore(0);
		updateNumberCardsLeft (64);
	}

	private void initializeControllers() {
		deckView.setMouseAdapter(new AlhambraDeckController (this, deck, wastePile));
		deckView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		deckView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		wastePileView.setMouseAdapter(new AlhambraWastePileController(this, wastePileView));
		wastePileView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		wastePileView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		aceSpadeView.setMouseAdapter(new AlhambraFoundationController(this, aceSpadeView));
		aceSpadeView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		aceSpadeView.setUndoAdapter (new SolitaireUndoAdapter(this));

		aceClubView.setMouseAdapter(new AlhambraFoundationController (this, aceClubView));
		aceClubView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		aceClubView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		aceDiamondView.setMouseAdapter(new AlhambraFoundationController (this, aceDiamondView));
		aceDiamondView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		aceDiamondView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		aceHeartView.setMouseAdapter(new AlhambraFoundationController (this, aceHeartView));
		aceHeartView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		aceHeartView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		kingSpadeView.setMouseAdapter(new AlhambraFoundationController (this, kingSpadeView));
		kingSpadeView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		kingSpadeView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		kingClubView.setMouseAdapter(new AlhambraFoundationController (this, kingClubView));
		kingClubView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		kingClubView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		kingDiamondView.setMouseAdapter(new AlhambraFoundationController (this, kingDiamondView));
		kingDiamondView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		kingDiamondView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		kingHeartView.setMouseAdapter(new AlhambraFoundationController (this, kingHeartView));
		kingHeartView.setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
		kingHeartView.setUndoAdapter (new SolitaireUndoAdapter(this));
		
		for(int i = 0; i<8;i++){
			reserveView[i].setMouseAdapter(new AlhambraReserveController (this, reserveView[i]));
			reserveView[i].setMouseMotionAdapter (new SolitaireMouseMotionAdapter(this));
			reserveView[i].setUndoAdapter (new SolitaireUndoAdapter(this));
		}
	}

	private void initializeView() {
		CardImages ci = getCardImages();
		int w = ci.getWidth();
		int h = ci.getHeight();
		
		deckView = new DeckView(deck);
		deckView.setBounds(40+w, 100+2*h, w, h);
		container.addWidget(deckView);
		
		wastePileView = new PileView(wastePile);
		wastePileView.setBounds(80+3*w,100+2*h,w,h);
		container.addWidget(wastePileView);
		
		
		//Ace view
		aceSpadeView = new PileView(aceSpade);
		aceSpadeView.setBounds(20,20,w,h);
		container.addWidget(aceSpadeView);
		
		aceClubView = new PileView(aceClub);
		aceClubView.setBounds(40+w,20,w,h);
		container.addWidget(aceClubView);
		
		aceDiamondView = new PileView(aceDiamond);
		aceDiamondView.setBounds(60+2*w,20,w,h);
		container.addWidget(aceDiamondView);
		
		aceHeartView = new PileView(aceHeart);
		aceHeartView.setBounds(80+3*w,20,w,h);
		container.addWidget(aceHeartView);
		
		//King views
		kingSpadeView = new PileView(kingSpade);
		kingSpadeView.setBounds(100+4*w,20,w,h);
		container.addWidget(kingSpadeView);
		
		kingClubView = new PileView(kingClub);
		kingClubView.setBounds(120+5*w,20,w,h);
		container.addWidget(kingClubView);
		
		kingDiamondView = new PileView(kingDiamond);
		kingDiamondView.setBounds(140+6*w,20,w,h);
		container.addWidget(kingDiamondView);
		
		kingHeartView = new PileView(kingHeart);
		kingHeartView.setBounds(160+7*w,20,w,h);
		container.addWidget(kingHeartView);
		
		// create reserve pile views
		for (int pileNum = 0; pileNum <8; pileNum++) {
			reserveView[pileNum] = new PileView(reserve[pileNum]);
			reserveView[pileNum].setBounds(20 + (pileNum)*ci.getWidth()+20*pileNum, ci.getHeight()+40, ci.getWidth(),ci.getHeight());
			container.addWidget (reserveView[pileNum]);
		}
		
		scoreView = new IntegerView (getScore());
		scoreView.setFontSize (14);
		scoreView.setBounds (120+5*w, 50+2*h, 100, 60);
		container.addWidget (scoreView);

		numLeftView = new IntegerView (getNumLeft());
		numLeftView.setFontSize (14);
		numLeftView.setBounds (220 + 5*w, 50+2*h, 100, 60);
		container.addWidget (numLeftView);
	}

	private void initializeModel(int seed) {
		
		deck = new MultiDeck("deck", 2);
		deck.create(seed);
		model.addElement(deck);
		
		wastePile = new Pile("waste pile");
		model.addElement(wastePile);
		
		// develop reserve piles
		for (int i = 0; i<8; i++) {
			reserve[i] = new Pile ("Reserve" + i);
			model.addElement (reserve[i]);
		}
		
		
		//AceFoundationPiles
		aceSpade = new Pile("AceSpade");
		model.addElement(aceSpade);
		aceClub = new Pile("AceClub");
		model.addElement(aceClub);
		aceDiamond = new Pile("AceDiamond");
		model.addElement(aceDiamond);
		aceHeart = new Pile("AceHeart");
		model.addElement(aceHeart);
		
		//KingFoundationPiles
		kingSpade = new Pile("KingSpade");
		model.addElement(kingSpade);
		kingClub = new Pile("KingClub");
		model.addElement(kingClub);
		kingDiamond = new Pile("KingDiamond");
		model.addElement(kingDiamond);
		kingHeart = new Pile("KingHeart");
		model.addElement(kingHeart);
	}
	/** Code to launch solitaire variation. */
	public static void main (String []args) {
		// Seed is to ensure we get the same initial cards every time.
		// Here the seed is to "order by suit."
		Main.generateWindow(new Alhambra(), Deck.OrderBySuit);
	}
}

