package characterdatabase;

public class HashTable {
    private final int MAX = 10; //Maximum amount of characters that can be stored.
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfChar = 0; //tracks number of characters occupying the table

    /**
     * Inserts a character object and a password (key) to access it into the hashtable.
     * @param key The password entered by the user.
     * @param charObj The character the user wants to store.
     * @return A message stating if operation was successful or if there was an error.
     */
    public String put(String key, Character charObj){ //Will never run if isFull() returns true
        HashEntry newEntry = new HashEntry(key, charObj);
        int index = hashFunction(key);
        //Check the initial index and insert character if empty
        if (TABLE[index] == null){
            TABLE[index] = newEntry;
            numOfChar += 1;
            return "\nEntry successful";
        }

//        if (TABLE[index].getKey().equals(key)){ //Forces user to create unique keys.
//            return "ERROR: ENTER ANOTHER KEY";
//        }

        index = linearProbing(index, key, true);

        if (index == -1) {
            return "\nERROR: ENTRY NOT SUCCESSFUL";
        }
        else {
            TABLE[index] = newEntry;
            numOfChar += 1;
            return "\nEntry successful.";
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
            return null;
        }
        else {
            return TABLE[index].getCharacter();
        }
    }

    public String delete(String key){
        int index = hashFunction(key);
        if (TABLE[index] != null){
            if (TABLE[index].getKey().equals(key)){
                TABLE[index] = null;
                numOfChar -= 1;
                return "\nEntry deleted.";
            }
        }

        index = linearProbing(index, key, false);

        if (index < 0) {
            return "\nERROR: Character not found";
        }
        else {
            TABLE[index] = null;
            numOfChar -= 1;
            return "\nEntry deleted";
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
                    return -1;
                }
                else {
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
