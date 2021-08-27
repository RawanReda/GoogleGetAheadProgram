import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class RearrangeCars {
	private RearrangeCars() {
	}

	public static class Move {
		public int car;
		public char from, to;

		public Move(int car, char from, char to) {
			this.car = car;
			this.from = from;
			this.to = to;
		}

		// We need to be able to compare moves in tests
		@Override
		public boolean equals(Object o) {
			Move other = (Move) o;
			if (car == other.car && from == other.from && to == other.to)
				return true;
			return false;
		}
	}

	public static class Parking {
		public Map<Integer, Character> carToLotMapping = new HashMap<>();
		public Map<Character, Integer> lotToCarMapping = new HashMap<>();
		public char emptyslot;

		public Parking(Map<Integer, Character> carToLotMapping) {
			this.carToLotMapping.putAll(carToLotMapping);
			lotToCarMapping = new HashMap<Character, Integer>();
			for (Entry<Integer, Character> entry : carToLotMapping.entrySet()) {
				lotToCarMapping.put(entry.getValue(), entry.getKey());
			}
			emptyslot = (char) findInitialEmptySlot();
		}

		public char findInitialEmptySlot() {
			for (int i = 'A'; i <= 'Z'; i++) {
				if (!lotToCarMapping.containsKey((char) i)) {
					return (char) i;
				}
			}
			return emptyslot;

		}

		void updateParking(int carToBePlacedInTheInitialEmptySlot, int initialParking_EmptySlot) {
			char initialLot = carToLotMapping.get(carToBePlacedInTheInitialEmptySlot);
			this.carToLotMapping.put(carToBePlacedInTheInitialEmptySlot, emptyslot);
			this.lotToCarMapping.put(emptyslot, carToBePlacedInTheInitialEmptySlot);
			emptyslot = initialLot;

		}

	}

	public static Iterable<Move> rearrangeCars(Parking initialParking, Parking finalParking) {
		Map<Integer, Character> initialParking_carToLotMapping = initialParking.carToLotMapping;
		Map<Character, Integer> initialParking_lotToCarMapping = initialParking.lotToCarMapping;
		Map<Integer, Character> finalParking_carToLotMapping = finalParking.carToLotMapping;
		Map<Character, Integer> finalParking_lotToCarMapping = initialParking.lotToCarMapping;

		List<Move> moves = new ArrayList<>();
		char initialParking_EmptySlot = initialParking.emptyslot;
		char finalParking_EmptySlot = finalParking.emptyslot;
//		System.out.println(initialParking_EmptySlot+" "+finalParking_EmptySlot);
		boolean solved = false;
		while (!solved) {
			if (initialParking_EmptySlot == finalParking_EmptySlot) {
				solved = true;
				for (Entry<Integer, Character> entry : initialParking_carToLotMapping.entrySet()) {
					if ( entry.getValue() != finalParking_carToLotMapping.get(entry.getKey())) {
						solved = false;
						int carToBePlacedInTheInitialEmptySlot = entry.getKey();
						char initialLot = initialParking_carToLotMapping.get(carToBePlacedInTheInitialEmptySlot);
						initialParking.updateParking(carToBePlacedInTheInitialEmptySlot, initialParking_EmptySlot);
						moves.add(new Move(carToBePlacedInTheInitialEmptySlot, (char) initialLot,
								(char) initialParking_EmptySlot));
						break;
					}

				}
				initialParking_EmptySlot = initialParking.emptyslot;
			}
			if (!solved) {
				int carToBePlacedInTheInitialEmptySlot = finalParking.lotToCarMapping.get(initialParking_EmptySlot);
				char initialLot = initialParking_carToLotMapping.get(carToBePlacedInTheInitialEmptySlot);
				initialParking.updateParking(carToBePlacedInTheInitialEmptySlot, initialParking_EmptySlot);
				moves.add(new Move(carToBePlacedInTheInitialEmptySlot, initialLot, initialParking_EmptySlot));
			}
			initialParking_EmptySlot = initialParking.emptyslot;
		}
		return moves;
	}

	public static void main(String[] args) {
		HashMap<Integer, Character> map = new HashMap<Integer, Character>();// Creating HashMap
		map.put(1, 'A'); // Put elements in Map
		map.put(2, 'B');
		map.put(3, 'D');

		HashMap<Integer, Character> map1 = new HashMap<Integer, Character>();// Creating HashMap
		map1.put(1, 'B'); // Put elements in Map
		map1.put(2, 'C');
		map1.put(3, 'A');
		Parking initialParking = new RearrangeCars.Parking(Map.of(1, 'A', 2, 'B', 3, 'C'));
		Parking finalParking = new RearrangeCars.Parking(Map.of(1, 'B', 2, 'C', 3, 'A'));
//		Parking p = new Parking(map);
//		Parking p2 = new Parking(map1);

		Iterable<Move> result = rearrangeCars(initialParking, finalParking);
		for (Move s : result) {
			System.out.println(s.car + " " + s.from + " " + s.to);
		}
		HashMap<Integer, Character> result1 = (HashMap<Integer, Character>) initialParking.carToLotMapping;
		for (Entry<Integer, Character> s : result1.entrySet()) {
			System.out.println(s.getKey() + " " + s.getValue());
		}
	}

}