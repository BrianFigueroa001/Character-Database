package characterdatabase;

public class HashTable {
    private final int MAX = 3; //Subject to change
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfChar = 0; //tracks number of characters in table

    public void put(String key, Character charObj){ //Will never run if isFull() returns true
        HashEntry newEntry = new HashEntry(key, charObj);
        int index = hashFunction(key);
        //Check the initial index and insert character if empty
        if (TABLE[index] == null){
            TABLE[index] = newEntry;
            numOfChar += 1;
            return;
        }
        if (TABLE[index].getKey().equals(key)){ //Forces user to create unique keys.
            System.out.println("\nERROR: ENTER ANOTHER KEY");
            return;
        }
        index = linearProbing(index, key, true);
        if (index == -1) {
            System.out.println("\nERROR: ENTER ANOTHER PASSWORD");
            return;
        }
        else {
            TABLE[index] = newEntry;
            numOfChar += 1;
        }
    }
    
    public Character get(String key){
        int index = hashFunction(key);
        if (TABLE[index] != null){
            if (TABLE[index].getKey().equals(key)){
                return TABLE[index].getCharacter(); // Runs if the index isn't null and has the character (by key)
            }
        }
        //Linear probing begins
        index = linearProbing(index, key, false);
        if (index < 0) {
            System.out.println("\nERROR: CHARACTER NOT FOUND");
            return null;
        }
        else {
            return TABLE[index].getCharacter();
        }
    }

    public void delete(String key){
        int index = hashFunction(key);
        if (TABLE[index] != null){
            if (TABLE[index].getKey().equals(key)){
                TABLE[index] = null;
                numOfChar -= 1;
                System.out.println("\nEntry Deleted From Database");
                return;
            }
        }
        index = linearProbing(index, key, false);
        if (index < 0) {
            return;
        }
        else {
            TABLE[index] = null;
            numOfChar -= 1;
            System.out.println("\nEntry Deleted From Table");
        }

    }

    public String printNumberOfCharacters(){ //Used for testing purposes
        return "\nTotal Number Of Entries In Database: " + numOfChar;
    }

    public boolean isFull(){
        if (numOfChar == MAX){
            System.out.println("\nERROR: TABLE IS FULL!");
        }
        return numOfChar == MAX;
    }

    private int hashFunction(String key){
        return Math.abs(key.hashCode() % MAX);
    }

    private int linearProbing(int initialIndex, String key, boolean isPut) {
        int index = initialIndex;
        ++index;
        if (index == MAX) {
            index = 0;
        }
        //If-statement runs if put() calls linearProbing()
        if (isPut) {
            while (TABLE[index] != null) { //linear probing
                if (TABLE[index].getKey().equals(key)) {
                    System.out.println("\nERROR: ENTER NEW PASSWORD");
                    return -1;
                } else {
                    ++index;
                    if (index == MAX) {
                        index = 0;
                    }
                }
            }
            return index;
        }
        //If-statement runs if get() or delete() calls linearProbing()
        else {
            boolean done = false;
            while (!done) {
                if (index == initialIndex) {
                    done = true;
                }
                if (TABLE[index] != null) {
                    if (TABLE[index].getKey().equals(key)) {
                        return index;
                    }
                }
                ++index;
                if (index == MAX) {
                    index = 0;
                }
            }
            return -1;
        }
    }

}
