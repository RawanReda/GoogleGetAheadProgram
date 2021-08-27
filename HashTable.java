import java.util.ArrayList;
import java.util.HashMap;

// In an interview you can generally omit the import statements,
// however if you decide to use some library it's a good idea to
// mention it to the Interviewer and ask if it's allowed.

public class HashTable<K, V> {

	public class Node {
		K node;
		V value;

		public Node(K n, V v) {
			node = n;
			value = v;
		}
	}

	HashMap<K, V> hm;
	ArrayList<ArrayList<Node>> hm_arr;
	int nslots;
	float max_load;
	int num_entries;

	// Hash table constructor
	// Arguments:
	// nslots - number of slots, or 'buckets', the data structure should be
	// initialized with
	// max_load - maximum allowed load factor (= num_entries / num_slots);
	// a positive floating-point number
	public HashTable(int nslots, float max_load) {
		this.nslots = nslots;
		this.max_load = max_load;
		this.num_entries = 0;
		hm_arr = new ArrayList<ArrayList<Node>>();

	}

	// Returns current number of elements in the data structure.
	public int size() {
		return num_entries;
	}

	// Returns current number of slots/buckets used by the implementation.
	public int nslots() {
		return nslots;
	}

	// Returns the value associated with the given key, or null if there
	// isn't one.
	public V get(K key) {
		return hm.get(key);
	}

	// Associates given value with the given key. Returns previous value
	// associated with the given key, or null if there wasn't one.
	public V put(K key, V value) {
		if ((size() + 1) / nslots() > max_load) {
			nslots++;
			num_entries++;
		} else
			num_entries++;
		int bucketIndex = key.hashCode() % nslots;
		if (hm_arr.get(bucketIndex) == null)
			hm_arr.add(bucketIndex, new ArrayList<>());
		hm_arr.get(bucketIndex).add(new Node(key, value));
		V val = hm.get(key);
		hm.put(key, value);
		return val;

	}

	// If present, removes an existing mapping of the given key to a value and
	// returns the value. Otherwise returns null.
	public V remove(K key) {
		num_entries--;
		V val = hm.get(key);
		hm.remove(key);
		return val;

	}
}