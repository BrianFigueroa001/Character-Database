package characterdatabase;

public class HashTable {
    private final int MAX = 10000; //Subject to change
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfCharacters; //tracks number of characters in hashtable

    public void put(Character charObj){
        HashEntry newEntry = new HashEntry(charObj);
        int index = hashFunction(charObj.getName());
        while (TABLE[index] != null){ //linear probing
            if (TABLE[index].getCharacter().getName().equals(charObj.getName())){
                System.out.println("\nERROR: CHARACTER FOUND IN DATABASE.");
                return;
            }
            else if (index == MAX - 1){ //Problem: Unable to insert a character in a available position before the index.
                System.out.println("\nCHARACTER INSERTION FAILED");
                return;
            }
            else {
                ++index;
            }
        }
        TABLE[index] = newEntry;
        numOfCharacters += 1;
    }
    
    public Character get(String charName){
        int index = hashFunction(charName);
        if (TABLE[index] == null){
            return null;
        }
        while (!TABLE[index].getCharacter().getName().equals(charName)) { //linear probing
            ++index;
            if (index == MAX) {
                return null;
            }
        }   
        Character character = TABLE[index].getCharacter();
        return character;
    }

    public void delete(String charName){ //ToDO: Implement into main
        int index = hashFunction(charName);
        if (TABLE[index] == null){
            System.out.println("\nERROR: CHARACTER NOT FOUND");
            return;
        }
        while (!TABLE[index].getCharacter().getName().equals(charName)){
            ++index;
            if (index == MAX - 1){
                System.out.println("\nERROR: CHARACTER NOT FOUND");
                return;
            }
        }
        TABLE[index] = null;
        System.out.println("\nCharacter Deleted From Database");
        numOfCharacters -= 1;
    }

    public String printNumberOfCharacters(){ //Used for testing purposes
        return "\nTotal Number Of Characters In Database: " + numOfCharacters;
    }

    private int hashFunction(String charName){
        int index = hash(charName.hashCode());
        return index;
    }

    private int hash(int key){
        int index = key;
        while (index >= MAX || index < 0){
            if (index < 0){
                index = Math.abs(index);
            }
            else {
                index /= 2;
            }
        }
        return index;
    }
    
    
}
