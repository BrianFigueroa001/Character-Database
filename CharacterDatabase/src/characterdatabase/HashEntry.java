package characterdatabase;

public class HashEntry {
    private Character character;
    private String key; //the character's name which can be hashed for a key in HashTable
    
    public HashEntry(Character character){
        this.character = character;
        this.key = character.getName();
    }
    
    public Character getCharacter(){
        return character;
    }
    
    public String getKey(){
        return key;
    }
    
    public void setCharacter(Character character){
        this.character = character;
    }
    
    public void setKey(String key){
        this.key = key;
    }
}
