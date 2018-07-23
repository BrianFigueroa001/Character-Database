package characterdatabase;

public class HashTable {
    private final int MAX = 10000; //Subject to change
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfCharacters; //tracks number of characters in hashtable

    public void put(String key, Character charObj){
        HashEntry newEntry = new HashEntry(key, charObj);
        int index = hashFunction(key);
        while (TABLE[index] != null){ //linear probing
            if (TABLE[index].getKey().equals(key)){
                System.out.println("\nERROR: ENTRY INSERTION FAILED");
                return;
            }
            else if (index == MAX - 1){ //Problem: Unable to insert a character in a available position before the index. Realistically should never happen
                System.out.println("\nERROR: ENTRY INSERTION FAILED");
                return;
            }
            else {
                ++index;
            }
        }
        TABLE[index] = newEntry;
        numOfCharacters += 1;
    }
    
    public Character get(String key){
        int index = hashFunction(key);
        if (TABLE[index] == null){
            return null;
        }
        while (!TABLE[index].getKey().equals(key)) { //linear probing
            ++index;
            if (index == MAX) {
                System.out.println("\nERROR: ENTRY NOT FOUND");
                return null;
            }
        }
        return TABLE[index].getCharacter();
    }

    public void delete(String key){
        int index = hashFunction(key);
        if (TABLE[index] == null){
            System.out.println("\nERROR: ENTRY NOT FOUND");
            return;
        }
        while (!TABLE[index].getKey().equals(key)){
            ++index;
            if (index == MAX){
                System.out.println("\nERROR: ENTRY NOT FOUND");
                return;
            }
        }
        TABLE[index] = null;
        System.out.println("\nEntry Deleted From Database");
        numOfCharacters -= 1;
    }

    public String printNumberOfCharacters(){ //Used for testing purposes
        return "\nTotal Number Of Entries In Database: " + numOfCharacters;
    }

    private int hashFunction(String key){
        int index = hash(key.hashCode());
        return index;
    }

    private int hash(int key){
        return Math.abs(key % MAX);
    }

    
}
