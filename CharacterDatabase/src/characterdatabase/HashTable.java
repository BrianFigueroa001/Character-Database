package characterdatabase;

public class HashTable {
    private final int MAX = 6; //Subject to change
    private final HashEntry[] TABLE = new HashEntry[MAX];
    private int numOfCharacters = 0; //tracks number of characters in hashtable

    public void put(String key, Character charObj){
        if (isTableFull()){ //Checks if the table is full
            return;
        }
        HashEntry newEntry = new HashEntry(key, charObj);
        int index = hashFunction(key);
        System.out.println(index + " " + key);
        //Following two if-statements check the initial index
        if (TABLE[index] == null){
            TABLE[index] = newEntry;
            numOfCharacters += 1;
            return;
        }
        if (TABLE[index].getKey().equals(key)){
            System.out.println("\nERROR: ENTER ANOTHER KEY");
            return;
        }
        //Linear probing begins
        int initialIndex = index;
        ++index;
        if (index == MAX){
            index = 0;
        }
        while (TABLE[index] != null){ //linear probing
            if (TABLE[index].getKey().equals(key)){
                System.out.println("\nERROR: ENTER NEW PASSWORD");
                return;
            }
            else {
                ++index;
                if (index == MAX ){
                    index = 0;
                }
            }
        }
        System.out.println(index);
        TABLE[index] = newEntry;
        numOfCharacters += 1;
    }
    
    public Character get(String key){
        int index = hashFunction(key);
        if (TABLE[index] != null){
            if (TABLE[index].getKey().equals(key)){
                return TABLE[index].getCharacter(); // Runs if the index isn't null and has the character (by key)
            }
        }
        //Linear probing begins
        int initialIndex = index;
        boolean done = false;
        ++index;
        if (index == MAX){
            index = 0;
        }
        while (!done) { //linear probing
            if (index == initialIndex){
                done = true;
            }
            if (TABLE[index] != null){
                if (TABLE[index].getKey().equals(key)){
                    return TABLE[index].getCharacter(); // Runs if the index isn't null and has the character (by key)
                }
            }
            ++index;
            if (index == MAX){
                index = 0;
            }
        }
        System.out.println("\nERROR: Character Not Found");
        return null;
    }

    public void delete(String key){
        int index = hashFunction(key);
        if (TABLE[index] != null){
            if (TABLE[index].getKey().equals(key)){
                TABLE[index] = null;
                numOfCharacters -= 1;
                System.out.println("\nEntry Deleted From Database");
                return;
            }
        }
        //Linear probing begins
        int tracker = index;
        boolean done = false;
        ++index;
        if (index == MAX){
            index = 0;
        }
        while (!done) {
            if (index == tracker){
                done = true;
            }
            if (TABLE[index] != null){
                if (TABLE[index].getKey().equals(key)){
                    TABLE[index] = null;
                    numOfCharacters -= 1;
                    System.out.println("\nEntry Deleted From Database");
                    return;
                }
            }
            ++index;
            if (index == MAX){
                index = 0;
            }
        }
        System.out.println("\nERROR: CHARACTER NOT FOUND");
    }

    public String printNumberOfCharacters(){ //Used for testing purposes
        return "\nTotal Number Of Entries In Database: " + numOfCharacters;
    }

    public boolean isTableFull(){
        if (numOfCharacters == MAX){ //Checks if the table is full
            System.out.println("\nERROR: DATABASE FULL");
            return true;
        }
        return false;
    }
    private int hashFunction(String key){
        int index = hash(key.hashCode());
        return index;
    }

    private int hash(int key){
        return Math.abs(key % MAX);
    }
}
