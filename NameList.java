
public class NameList {
	// Node clas to set all nodes in the linked list
	public class Node{
		Node next;
		String data;
		
		public Node(String data) {
			this.data = data;
			next = null;
		}
	}
	
	//Have a head node to use
	private Node head;
	
	// The add method will determine to see if the list is empty or has a letter in the list
	// If the list is empty, it will add the letter as the head of the list then add the name as the 
	// next node in the list
	
	//It will call the availbleLetter method to check to see if there is a letter already in the list that matches 
	// with the name. If there is already a letter, then it will call the 
	// add name method to add the name, otherwise if the method returns false, then it 
	// will add the letter by calling in the addLetter method and add the name as well
	public void add(String name) {
		String letterValue = String.valueOf(name.charAt(0)); // get the letter of the name that has no position
		// name has to be greater than length of one
		if(name.length() <= 1) {
			System.out.println("The Name " + name + " is too short, name has to be greater than length of one\n");
		}
		else {
			Boolean availableLetter = isLetterThereLetter(name);
	
			if(head == null) { // empty list
				Node newNode = new Node(letterValue);
				head = newNode;
				addName(name);
				
			}
			
			// if the letter of the string is already in the 
			else if(availableLetter == true) {
				addName(name);
			}
			
			else if(availableLetter == false) {
				addLetter(name);
				addName(name);
			}
		}
	}
	
	// The add name will add the name into the list.
	// It will check to see if the name in the list has to be in the header
	// position, and if it is, then it will become the new header
	
	// otherwise, it will navigate the list and place the name in the right
	// order using the compareTo function with the current data in the nodes it is checking
	// it will then place the node in the correct position before returning
	public void addName(String name) {
		int compare; // used to compare
		Node newNode = new Node(name); // Creates a new Node with the Data already inside
		Node nextNode = head.next; // used to navigate the list
		Node current = head;	// the previous node in the list
		Boolean nameIsThere = isNameInList(name); // to check if the name is already there
		
		// check to see if the name is already in the list
		if(nameIsThere == true) {
			System.out.println("The name " + name + " is already in the list");
			return;
		}
		
		// if the name is in the head
		if(name.compareTo(head.data) < 0) {
			newNode.next = head;
			head = newNode;
			return;
		}
		
		// find the correct postion to place the name
		while(nextNode != null) {
			compare = name.compareTo(nextNode.data); // will either return a positive, negative, or zero
			// if greater than zero, then place the node in the correct position in the list, otherwise, move on to the next node in the list
			if(compare < 0) {
				newNode.next = nextNode;
				current.next = newNode;
				break;
			}
			else {
				current = nextNode;
				nextNode = nextNode.next;
			}
		}
		current.next = newNode;
	}
	
	// This method will add a letter if the list does not already contain that letter, it will take the 
	// the first character of the name that doesn't have a place in the list, and will convert it to a string.
	// Then it will move through the list to place the letter in the correct position
	public void addLetter(String name) {
		String letterValue = String.valueOf(name.charAt(0)); // get the letter of the name that has no position
		int compare;
		Node newNode = new Node(letterValue); // create a new node

		if(newNode.data.compareTo(head.data) < 0) {
			newNode.next = head;
			head = newNode;
			return;
		}
		
		// use to navigate the list
		Node nextNode = head.next;
		Node current = head;
		
		// transverse the list
		while(nextNode != null) {
			compare = newNode.data.compareTo(nextNode.data);
			// if greater than zero, then place the node in the correct postition in the list, otherwise, move on to the next node in the list
			if(compare < 0) {
				newNode.next = nextNode;
				current.next = newNode;
				break;
			}
			else {
				current = nextNode;
				nextNode = nextNode.next;
			}
		}
		current.next = newNode;
	}
	
	// This method will move through the linked list to find the name to remove, 
	// If the name is there, it will remove the name, otherwise, it will let the user know that the
	// name is not in the list
	public void remove(String name) {
		String letterValue = String.valueOf(name.charAt(0));
		Boolean nameIsThere = isNameInList(name);
		int counter = 0;
		
		if(head == null) {
			System.out.println("The list is empty, nothing can be removed");
			return;
		}
		
		if(head.data.compareTo(name) == 0) {
			head = head.next;
			return;
		}
		
		if(nameIsThere == true) {
			Node current = head;
			
			while(current != null) {
				if(current.data.charAt(0) == name.charAt(0)){
					++counter;
				}
				current = current.next;
			}
			
			if(counter == 2) {
				removeLetter(letterValue);
			}
			
			current = head;
			while(current.next != null) {
				if(current.next.data.compareTo(name) == 0) {
					current.next = current.next.next;
					return;
				}
				current = current.next;
			}
		}
		else {
			System.out.println("The name " + name + " does not exist in the list");
		}
	}
	
	// This method will remove the letter from the list the user has chosen to remove
	// If the letter exist, then the letter will be removed and call in another function to remove
	// the letter, if the letter does not exit, then it will display that the letter does not
	// exist in the list
	public void removeLetter(String letterValue) {
		Boolean availableLetter = isLetterThereLetter(letterValue); // to check to see if there is a letter there
		// check to see if the letter is available in the list
		if(availableLetter == false) {
			System.out.println("The Letter " + letterValue + " is not in the list");{
				return;
			}
		}
		
		// if the head is null, then the list will display that the list is empty
		if(head == null) {
			System.out.println("The list is empty, nothing can be removed");
			return;
		}
		
		// if the head is the same as the letterValue, then the data will remove the letter from the 
		// head and call in another method that will help the remove the names containing the letters
		if(head.data.compareTo(letterValue) == 0) {
			head = head.next; // head is next head
			removeLetterContain(letterValue); // remove the letter value
			return;
		}
		
		// set node to be head
		Node currentNode = head;
		
		while(currentNode.next != null) {
			if(currentNode.next.data.compareTo(letterValue) == 0) {
				currentNode.next = currentNode.next.next;
				removeLetterContain(letterValue);
				return;
			}
			currentNode = currentNode.next;
		}
	}
	
	// This helper method will help make remove any names that contain the letter being removed
	// it will go through the list and call the remove method to remove any names that begin with the letter 
	// that is being removed
	public void removeLetterContain(String containLetter) {
		String tempData;
		Node currentNode = head;
		
		while(currentNode != null) {
			if(head.data.charAt(0) == containLetter.charAt(0)) {
				head = head.next;
				continue;
			}
			currentNode = currentNode.next;
		}
		
		currentNode = head;
		
		while(currentNode != null) {
			if(currentNode.data.charAt(0) == containLetter.charAt(0)) {
				tempData = currentNode.data;
				remove(tempData);
			}
			currentNode = currentNode.next;
		}
		
	}
	
	// This method will transverse the list in order to find the name the user has requested
	// If the name is in the list, then the it will return the name is in the list, otherwise, 
	// If the name is not in the list, then it will return the name is not in the list
	public void find(String name) {
		Node current = head; // current node is head
		
		// loop the list. If the name is in the list, then return the "name" is in the list, 
		// otherwise return the name is not in the list
		while(current != null) {
			if(current.data.compareTo(name) == 0) {
				System.out.println("The name " + name + " is in the list");
				return;
			}
			current = current.next;
		}
		
		System.out.println("The name " + name + " is not in the list");
	}
	
	// This method will check to see if there is a a letter, such as as 
	// A or B for the the name Arnie or Bob, and it will determine to 
	// either add a letter or to just add the name when it is called in the add method
	public Boolean isLetterThereLetter(String name) {
		Boolean letterIsThere = false; // default to false
		Node currentNode = head; // current node is the head
		
		// will traverse the list until it finds a letter that matches
		// once it matches, letterIsThere will be true and will return true
		// otherwise return false
		while(currentNode != null) {
			if(currentNode.data.charAt(0) == name.charAt(0)) {
				letterIsThere = true;
				break;
			}
			currentNode = currentNode.next;
		}
		
		return letterIsThere;
	}
	
	// will check to see if name is in the list
	public Boolean isNameInList(String name) {
		Boolean nameIsThere = false; // default to false
		Node currentNode = head; // current node is the head
		
		// will traverse the list until it finds a letter that matches
		// once it matches, nameIsThere will be true and will return true
		// otherwise return false
		while(currentNode != null) {
			if(currentNode.data.equals(name)) {
				nameIsThere = true;
				return nameIsThere;
			}
			currentNode = currentNode.next;
		}
		
		return nameIsThere;
	}

	// Create a string method that will display the string in the order described in the assignment
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node p = head;
		
		while(p != null) {
			if(p.data.length() == 1) {
				sb.append(p.data + "\n");
			}
			else {
				sb.append("   " + p.data + "\n");
			}
			p = p.next;
		}
		
		sb.append("\n");

		return new String(sb);
	}
	
	public static void main(String[] args) {
		NameList list = new NameList(); // new empty list
		
		System.out.println("Here is of adding names to an empty list: ");
		System.out.println(list);
		
		
		list.add("Max");
		list.add("Bevelery");
		list.add("Arnulfo");
		list.add("Alferdo");
		list.add("Arnie");
		list.add("Bilbo");
		list.add("Caster");
		list.add("Maria");
		list.add("Erick");
		list.add("Mark");
		list.add("Alfred");
		list.add("Alfanzo");
		list.add("Bulma");
		list.add("Nicho");
		list.add("D");
		
		System.out.print(list);
		
		System.out.println("Here is of removing the names Arnie, Cater, and Maria: ");
		list.remove("Arnie");
		list.remove("Cater");
		list.remove("Maria");
		System.out.println(list);

		System.out.println("Here Trying to find the names Arnulfo and Arnie in the list: ");
		list.find("Arnulfo");
		list.find("Arnie");
		
		System.out.println("\n\nHere is removing the letter 'B' and 'A' from the list: ");
		list.removeLetter("B");
		list.removeLetter("A");
		System.out.println(list);

		System.out.println("Here is removing Caster from the list: ");
		list.remove("Caster");
		System.out.println(list);
		
		System.out.println("Adding the name Mark in the list again");
		list.add("Mark");
		System.out.println(list);



	}


	
	
}
