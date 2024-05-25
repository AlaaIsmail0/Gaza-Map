package application;

import java.util.ArrayList;
import java.util.Comparator;

public class MyPriorityQueue<T> {
	private ArrayList<T> elements;
	private Comparator<T> comparator;

	public MyPriorityQueue(Comparator<T> comparator) {
		this.elements = new ArrayList<>();
		this.comparator = comparator;
	}

	public void add(T element) {
		elements.add(element);
		bubbleUp();
	}

	public T poll() {
		if (isEmpty()) {
			return null;
		}

		T root = elements.get(0);
		T lastElement = elements.remove(elements.size() - 1);

		if (!isEmpty()) {
			elements.set(0, lastElement);
			bubbleDown();
		}

		return root;
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	private void bubbleUp() {
		int index = elements.size() - 1;
		while (index > 0) {
			int parentIndex = (index - 1) / 2;
			if (comparator.compare(elements.get(index), elements.get(parentIndex)) < 0) {
				swap(index, parentIndex);
				index = parentIndex;
			} else {
				break;
			}
		}
	}

	private void bubbleDown() {
		int index = 0;
		while (true) {
			int leftChildIndex = 2 * index + 1;
			int rightChildIndex = 2 * index + 2;
			int smallest = index;

			if (leftChildIndex < elements.size()
					&& comparator.compare(elements.get(leftChildIndex), elements.get(smallest)) < 0) {
				smallest = leftChildIndex;
			}

			if (rightChildIndex < elements.size()
					&& comparator.compare(elements.get(rightChildIndex), elements.get(smallest)) < 0) {
				smallest = rightChildIndex;
			}

			if (smallest != index) {
				swap(index, smallest);
				index = smallest;
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		T temp = elements.get(i);
		elements.set(i, elements.get(j));
		elements.set(j, temp);
	}
}
