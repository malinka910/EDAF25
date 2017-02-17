package uppgift1;

/*
 * I följande exempel används null för att representera okända föräldrar. 
 * Använd Null Object-mönstret för att få en bättre design där det inte 
 * behövs några if-satser. Lösningen redovisas med ett klassdiagram med 
 * generaliseringar, attribut och metoder samt Java-kod.
 */


public class Person implements People {
	
	private String name;
	private People mother; 
	private People father;
	
	public Person(People mother, People father, String name){
		this.mother = mother;
		this.father = father;
		this.name = name;
	}
	
	public void printAncestors(){
			System.out.println(name);
			father.printAncestors(); 
			mother.printAncestors();
		} 
		

}
