package characterdatabase;

public class HashTable {
    private final int MAX = 10000; //Subject to change
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfCharacters; //tracks number of characters in hashtable

    public void put(String key, Character charObj){
        if (isTableFull()){
            System.out.println("\nERROR: DATABASE FULL");
            return;
        }
        HashEntry newEntry = new HashEntry(key, charObj);
        int index = hashFunction(key);
        if (TABLE[index] == null){
            TABLE[index] = newEntry;
            numOfCharacters += 1;
            return;
        }
        if (isEqual(index, key)){
            System.out.println("\nERROR: ENTER NEW KEY");
            return;
        }
        int initialIndex = index;
        index = putLinearProbing(index, key);
        if (index == initialIndex){
            return;
        }
        else {
            TABLE[index] = newEntry;
            numOfCharacters += 1;
        }
    }
    
    public Character get(String key){
        int index = hashFunction(key);
        if (TABLE[index] == null){
            System.out.println("\nERROR: ENTRY NOT FOUND");
            return null;

        }
        if (isEqual(index, key)){
            return TABLE[index].getCharacter();
        }
        int tracker = index;
        index = linearProbing(index, key);

        return TABLE[index].getCharacter();
    }

    public void delete(String key){
        int index = hashFunction(key);
        if (TABLE[index] == null){
            System.out.println("\nERROR: ENTRY NOT FOUND");
            return;
        }
        if (isEqual(index, key)){
            TABLE[index] = null;
            return;
        }
        int tracker = index;
        index = linearProbing(index, key);

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

    private int linearProbing(int index, String key){ //for delete() and get()
        int tracker = index;
        ++index;
        while (TABLE[index] == null){

        }
    }

    private int putLinearProbing(int index, String key){ //for put() method. It searches for a null element in characterTable
        int initialIndex = index;
        ++index;
        while (TABLE[index] != null){ //linear probing
            if (index == initialIndex) {
                System.out.println("\nERROR: DATABASE IS FULL");
                return initialIndex;
            }
            if (isEqual(index, key)){
                System.out.println("\nERROR: ENTER NEW KEY");
                return initialIndex;
            }
            else if (index == MAX - 1){ //Problem: Unable to insert a character in a available position before the index. Realistically should never happen
                index = 0;
            }
            else {
                ++index;
            }
        }
        return index;
    }
    private boolean isEqual(int index, String key){
        return TABLE[index].getKey().equals(key);
    }

    private boolean isTableFull(){
        return numOfCharacters == MAX;
    }
}
