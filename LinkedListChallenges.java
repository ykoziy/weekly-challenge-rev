import java.util.HashSet;

/*

a) Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the intersecting node. Note that the intersection is defined based on reference, not value. That is, if the kth node of the first linked list is the exact same node (by reference) as the jth node of the second linked list, then they are intersecting.

b) Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop.
DEFINITION:
Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
EXAMPLE
Input: A -> B -> C -> D -> E -> C [the same C as earlier]
Output: C

*/

public class LinkedListChallenges {

	static class Node<T> {
		private T data;
		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		public String toString() {
			return data + "";
		}
	}

	static class LinkedList<T> {
		public Node<T> head;

		public LinkedList() {
			this.head = null;
		}

		public Node<T> add(T item) {
			if (head == null) {
				head = new Node<T>(item, head);
				return head;
			} else {
				Node<T> last = head;
				while (last.next != null) {
					last = last.next;
				}
				last.next = new Node<T>(item, null);
				return last.next;
			}
		}

		public void addLoop(Node<T> anotherNode) {
			Node<T> last = head;
			while (last.next != null) {
				last = last.next;
			}

			last.next = anotherNode;
		}

		public String toString() {
			StringBuffer result = new StringBuffer();
			Node<T> currNode = head;
			while (currNode != null) {
				result.append(currNode + " ");
				currNode = currNode.next;
			}
			return result.toString();
		}

		// b) Loop Detection: return the node at the beginning of the loop
		public Node<T> getLoopNode() {
			Node<T> slow = head, fast = head;
			while (slow != null && fast != null && fast.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (slow == fast) {
					// we detect a loop,
					// but must get the Node where it begins
					slow = head;
					while (slow != fast) {
						slow = slow.next;
						fast = fast.next;
					}
					return slow;
				}
			}
			System.out.println("NULL");
			return null;
		}

	}

	// a) Intersection: find intersection node or return null
	public static <T> Node<T> intersectionNode(Node<T> headA, Node<T> headB) {
		if(headA == null || headB == null) return null;
		Node<T> p1 = headA;
		Node<T> p2 = headB;
		// when one list ends, switch that pointer to the other list
		while (p1 != p2) {
			if (p1 == null) {
				p1 = headB;
			} else {
				p1 = p1.next;
			}
			if (p2 == null) {
				p2 = headA;
			} else {
				p2 = p2.next;
			}
		}
		return p1;
	}

	private static void testLoop() {
		LinkedList<String> list = new LinkedList<>();
		list.add("A");
		list.add("B");
		Node<String> nodeC = list.add("C");
		list.add("D");
		list.add("E");
		list.addLoop(nodeC);

		Node<String> loopNode = list.getLoopNode();

		System.out.println(loopNode);
	}

	private static void testIntersection() {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(9);
		list.add(1);
		Node<Integer> join = list.add(2);
		list.add(4);

		LinkedList<Integer> list2 = new LinkedList<>();
		list2.add(3);
		list2.addLoop(join);

		Node<Integer> intersectNode = LinkedListChallenges.intersectionNode(list.head, list2.head);
		
		System.out.println(intersectNode);
		
		LinkedList<Integer> listA = new LinkedList<>();
		listA.add(1);

		LinkedList<Integer> listB = new LinkedList<>();
		listB.add(3);

		Node<Integer> intersectNodeB = LinkedListChallenges.intersectionNode(listA.head, listB.head);
		
		System.out.println(intersectNodeB);

	}

	public static void main(String[] args) {
		testLoop();
		testIntersection();
	}

}
