package com.nickylabs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class CollectionsTest {

	@Test
	public void testCollectionInterface() {
		System.out.println("Begin testCollectionInterface");
		// Create an empty collection.
		Collection<String> c = new ArrayList<String>();
		
		//validate if the collection is empty
		assertTrue(c.isEmpty());
		assertEquals(0,c.size());
		assertEquals("[]", c.toString());
		assertFalse(c.contains("Nihilson"));

		// add 2 elements to the collection
		c.add("Nihilson");
		c.add("Ria");
		
		// Iterate and verify the elements are not null
		Iterator<String> iter = c.iterator();
		
		// lambda expression (Java 8 and above)
			iter.forEachRemaining( System.out::println );
		
		Iterator<String> iter2 = c.iterator();
		// normal usage
		while (iter2.hasNext()) {
			String s = iter2.next();
			System.out.println(s);
			assertNotNull(s);			
		}
		
		// For-Each construct
		for (String str : c)
			System.out.println(str);
		
		// For compatibility reasons let us convert it to an Array.
		Object[] str = c.toArray();
		assertEquals(2, str.length);
		
		// validate the collection is not empty and has 2 elements.
		assertFalse(c.isEmpty());
		assertEquals(2, c.size());
		assertEquals("[Nihilson, Ria]", c.toString());
		assertTrue(c.contains("Nihilson"));
		
		//remove one element from the collection
		c.remove("Nihilson");
		
		//validate that the collection just has 1 element
		assertEquals(1, c.size());
		assertEquals("[Ria]", c.toString());
		assertFalse(c.contains("Nihilson"));
		
		System.out.println("End testCollectionInterface : passed");
	}
	
	
	@Test
	public void testSetInterface() {
		
		Set<String> s = new HashSet<String>();
		
		//validate that the Set collection is empty
		assertTrue(s.isEmpty());
		assertEquals(0,s.size());
		assertEquals("[]", s.toString());
		assertFalse(s.contains("Nihilson"));	
		
		
		s.add("Nihilson");
		s.add("Riana");
		s.add("Mini");
		s.add("Ryan");
		s.add("Nihilson");
		
		assertFalse(s.isEmpty());
		assertEquals(4,s.size());
		assertEquals("[Mini, Ryan, Riana, Nihilson]", s.toString()); // Bad test as the order can change
		System.out.println(s.toString());
		
		assertTrue(s.contains("Nihilson"));	
		
		//Create a TreeSet from unordered HashSet
		Set<String> treeSet = new TreeSet<String>(s);
		//verify if it orders the elements ?
		assertEquals("[Mini, Nihilson, Riana, Ryan]", treeSet.toString()); 
		System.out.println(treeSet.toString());
		
		for (String str : treeSet)
			System.out.println(str);
		
		Iterator<String> itr = treeSet.iterator();		
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
	
	@Test
	public void testSetInterfaceBulkOperations() {
		
		Set<String> s1= new HashSet<String>();
		s1.add("Nihilson");
		s1.add("Mini");
		
		Set<String> s2= new HashSet<String>();
		s2.add("Riana");
		s2.add("Ryan");
		s2.add("Nihilson");
		
		// UNION
		Set<String> union = new HashSet<String>(s1);
		union.addAll(s2);
		
		Assert.assertEquals("[Mini, Ryan, Riana, Nihilson]", union.toString());
		System.out.println(union.toString());
		
		//INTERSECTION
		Set<String> intersection = new HashSet<String>(s1);
		intersection.retainAll(s2);
		
		Assert.assertEquals("[Nihilson]", intersection.toString());
		System.out.println(intersection.toString());
		
		// DIFFERENCE
		Set<String> difference = new HashSet<String>(s1);
		difference.removeAll(s2);
		
		Assert.assertEquals("[Mini]", difference.toString());
		System.out.println(difference.toString());
		
	}
	
	@Test
	public void testSetImplPerformance() {
		
		// Test the performance of these 3 different Set implementations
		
		Set<String> hashSet = new HashSet<String>();
		Set<String> treeSet = new TreeSet<String>();
		Set<String> linkedHashSet = new LinkedHashSet<String>();

		Instant start = Instant.now();
		
		for(int i=0;i<100000;i++) {
			hashSet.add("Nihilson-"+i);
		}
		
		Instant end = Instant.now();
		long elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for HashSet.add() :" + elapsedTime + "ms");
	
		// ADD into TreeSet
		start = Instant.now();
		
		for(int i=0;i<100000;i++) {
			treeSet.add("Nihilson-"+i);
		}
		
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for TreeSet.add() :" + elapsedTime + "ms");
		
		// ADD to LinkedHashSet
		start = Instant.now();
		
		for(int i=0;i<100000;i++) {
			linkedHashSet.add("Nihilson-"+i);
		}
		
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for LinkedHashSet.add() :" + elapsedTime + "ms");
		
		
		// Test Contains Method for HashSet		
		start = Instant.now();		
		hashSet.contains("Nihilson-55555");		
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for HashSet.contains() :" + elapsedTime + "ms");
	
		//  Test Contains Method for TreeSet
		start = Instant.now();
		treeSet.contains("Nihilson-55555");			
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for TreeSet.contains() :" + elapsedTime + "ms");
		
		// Test Contains Method for LinkedHashSet
		start = Instant.now();
		linkedHashSet.contains("Nihilson-55555");				
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for LinkedHashSet.contains() :" + elapsedTime + "ms");
		
		// Test Contains Method for HashSet		
		start = Instant.now();		
		hashSet.remove("Nihilson-55555");		
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for HashSet.remove() :" + elapsedTime + "ms");

		//  Test Contains Method for TreeSet
		start = Instant.now();
		treeSet.remove("Nihilson-55555");			
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for TreeSet.remove() :" + elapsedTime + "ms");

		// Test Contains Method for LinkedHashSet
		start = Instant.now();
		linkedHashSet.remove("Nihilson-55555");		
		end = Instant.now();
		elapsedTime= Duration.between(start, end).toMillis();
		System.out.println("Time taken for LinkedHashSet.remove() :" + elapsedTime + "ms");		
				
	}
	
	@Test
	public void testCollectionsShuffleMethod() {
		List<String> list = new ArrayList<String>();
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("Four");
		list.add("Five");
				
		System.out.println(list.toString());
		Collections.shuffle(list, new Random());
		Assert.assertNotEquals("[One, Two, Three, Four, Five]", list.toString());
		System.out.println(list);	
		
	}
	
	@Test
	public void testListAlgorithms() {
		
		//1. Test the sort method
		List<String> list = new ArrayList<String>();
		list.add("10");
		list.add("30");
		list.add("20");
		list.add("50");
		list.add("40");
		
		// Sample Anonymous Class Creation.
		Comparator<String> cmp = new Comparator<String>() {
	        public int compare(String o1, String o2) {
	            return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
	        }
	    };
	    
	    System.out.println(list.toString());
		list.sort(cmp);
	    //Collections.sort(list);
		Assert.assertEquals("[10, 20, 30, 40, 50]", list.toString());
		System.out.println(list.toString());
		
		//2. Test the reverse method
		
		Collections.reverse(list);
		Assert.assertEquals("[50, 40, 30, 20, 10]", list.toString());
		System.out.println(list.toString());
		
		// 3. Test the rotate method		
		Collections.rotate(list, 3);
		Assert.assertEquals("[30, 20, 10, 50, 40]", list.toString());
		System.out.println(list.toString());
		
		// 4. Test the swap method
		Collections.swap(list, 0, 4);
		Assert.assertEquals("[40, 20, 10, 50, 30]", list.toString());
		System.out.println(list.toString());
		
		//5. Test the binarySearch		
		int pos = Collections.binarySearch(list, "50");
		Assert.assertEquals(3, pos);
		
		//6. Test the frequency
		list.add("50");
		list.add("50");
		int count = Collections.frequency(list, "50");
		Assert.assertEquals(3, count);
	}
	
	@Test
	public void testQueueInterface() {
		
		// Create a queue and add 3 elements
		Queue<String> queue = new LinkedList<String>();
		queue.add("One");
		queue.add("Two");
		queue.add("Three");
		
		System.out.println(queue.toString());
		
		String head = queue.peek();
		Assert.assertEquals("One", head);
		
		String remove = queue.poll();
		Assert.assertEquals("One", remove);
		Assert.assertEquals("[Two, Three]", queue.toString());
		System.out.println(queue.toString());

		// Validate that the Queue is not empty
		boolean empty = queue.isEmpty();
		Assert.assertFalse(empty);
				
		// isEmpty can be used for blocking and processing in a loop
		while(!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
		
		// Validate the queue is empty now.
		Assert.assertTrue(queue.isEmpty());
		System.out.println(queue);		
	}
	
	@Test
	public void testDequeueInterface() {
		
		// Test the Insert, Examine and Remove operations on Deque 
		// addFirst, addLast & offerFirst
		// getFirst, getLast & peekFirst & peekLast
		// removeFirst, removeLast & pollFirst & pollLast 

		// Create a Deque with ArrayDeque implementation
		Deque<String> deck1 = new ArrayDeque<String>();
				
		// addFirst() & addLast()
		deck1.addFirst("One");
		deck1.addFirst("Two");
		deck1.addFirst("Three");
		deck1.addLast("Zero");
		
		System.out.println(deck1.toString());
		
		//getFirst & getLast
		Assert.assertEquals("Three",deck1.getFirst());
		Assert.assertEquals("Zero",deck1.getLast());
		
		// removeFirst & removeLast
		Assert.assertEquals("Three",deck1.removeFirst());
		Assert.assertEquals("Zero",deck1.removeLast());
		Assert.assertEquals("[Two, One]",deck1.toString());
		System.out.println(deck1.toString());
		
		// Create Deque with LinkedList implementation
		Deque<String> deck2 = new LinkedList<String>();
		// offerFirst
		deck2.offerFirst("One");
		deck2.offerFirst("Two");
		deck2.offerFirst("Three");
		deck2.offerLast("Zero");
		
		System.out.println(deck2.toString());
		
		// peekFirst & peekLast		
		Assert.assertEquals("Three",deck2.peekFirst());
		Assert.assertEquals("Zero",deck2.peekLast());
					
		//pollFirst & pollLast
		Assert.assertEquals("Three",deck2.pollFirst());
		Assert.assertEquals("Zero",deck2.pollLast());
		Assert.assertEquals("[Two, One]",deck1.toString());
		System.out.println(deck2.toString());
	
	}
}
