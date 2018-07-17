package characterdatabase;

public class HashTable {
    private final int MAX = 100000;
    private final HashEntry[] TABLE = new HashEntry[MAX];
    
    public void put(Character charObj){
        HashEntry newEntry = new HashEntry(charObj);
        int index = hashFunction(charObj);
        while (TABLE[index] != null){
            ++index;
        }
        TABLE[index] = newEntry;
    }
    
    public Character get(){
        
        return null; // fixLater
    }
    
    private int hashFunction(Character character){
        int index = character.getName().hashCode();
        while (index >= MAX || index < 0){
            if (index < 0){
                index = Math.abs(index);
            }
            index = index/2;
        }
        return index;
    }
    
    private int linearProbe(int index, String charName){
        
        return 1;//fixlater
    }

}
