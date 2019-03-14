package edu.dmacc.codedsm.hw11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) {

		System.out.println("Game Begins...");

		List<Card> savedCards = new ArrayList<>();

		Map<String, List<Integer>> deck = createDeck();

		List<Card> chosenCards = DeckRandomizer.chooseRandomCards(deck, 2);

		savedCards.addAll(chosenCards);

		System.out.println("Initial Card1: " + chosenCards.get(0));
		System.out.println("Initial Card2: " + chosenCards.get(1));

		for (int j = 0; j < chosenCards.size(); j++) {
			String KeySuit = chosenCards.get(j).suit;
			Integer KeyRank = chosenCards.get(j).value;

			deck.get(KeySuit).remove(KeyRank);

		}

		int switchHitStand = 1;

		do {
			System.out.println("Enter 1 for HIT and 2 for Stand");
			try {
				Scanner scan1 = new Scanner(System.in);
				switchHitStand = scan1.nextInt();
				if (switchHitStand == 1) {
					
					List<Card> nextCard = DeckRandomizer.chooseRandomCards(
							deck, 1);
					System.out.println("next Random Card :" + nextCard.get(0));
					String KeySuit = nextCard.get(0).suit;
					Integer KeyRank = nextCard.get(0).value;
					deck.get(KeySuit).remove(KeyRank);

					savedCards.add(nextCard.get(0));

					// System.out.println("Cards at this time: "+ savedCards);

				} else
					endGame(savedCards);

			} catch (InputMismatchException e) {

				System.err.println("Enter valid Input");
				break;
			}
		} while (switchHitStand == 1);

		// System.out.println(deck);

	}

	private static void endGame(List<Card> savedCards) {

		int hand = 0;

		for (Card card : savedCards) {
			System.out.println(card.suit + " - " + card.value + " , ");
			hand = hand + card.value;

		}
		System.out.println(String.format("Player's hand was : %d", hand));
	}

	private static Map<String, List<Integer>> createDeck() {
		Map<String, List<Integer>> deck = new HashMap<>();
		deck.put("Clubs", createCards());
		deck.put("Diamonds", createCards());
		deck.put("Spades", createCards());
		deck.put("Hearts", createCards());
		return deck;
	}

	private static List<Integer> createCards() {
		List<Integer> cards = new ArrayList<>();
		for (int i = 1; i < 14; i++) {
			cards.add(i);
		}
		return cards;
	}
}