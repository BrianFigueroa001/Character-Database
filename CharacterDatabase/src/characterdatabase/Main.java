package characterdatabase;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nRPG Character Database Online");

        Scanner kb = new Scanner(System.in);

        HashTable characterTable = new HashTable();

        Character chaszwik = new Character("Chaszwik", "Drow", "Thief", "Minion Evil");       
        Character giblort = new Character("Giblort", "Gnorf", "Fighter", "Chaotic Good");
        Character ekade = new Character("Ekade", "Half-Orc", "Druid", "True Neutral");
        Character mousek = new Character("Mousek", "Half-Orc", "NPC", "Lawful Neutral");
        Character pike = new Character("Pike", "Human", "Ranger/Fighter", "Mystery");
        characterTable.put("XxBladeInTheDarkness234xX", chaszwik);
        characterTable.put("imahero", giblort);
        characterTable.put("PonyBoy3000", mousek);
        characterTable.put("ATragicMemory",ekade);
        characterTable.put("HisBirthDate", pike);

        boolean done = false;
        while (done != true){
            System.out.println("\nOption 1: Insert New Character\nOption 2: Print Character Information\n" +
                               "Option 3: Delete Character\nOption 4: Print Number of Characters In Database\n" +
                               "Option 5: Terminate the Program");
            System.out.print("Press 1, 2, 3, 4, or 5 for their corresponding options: ");
            String option;
            option = kb.nextLine();
            switch (option) {
                case "1":
                    if (characterTable.isFull()){
                        System.out.println("\nERROR: Database is full.");
                        break;
                    }
                    System.out.print("Enter character's name: ");
                    String name = kb.nextLine();
                    System.out.print("Enter character's race: ");
                    String race = kb.nextLine();
                    System.out.print("Enter character's class: ");
                    String charClass = kb.nextLine();
                    System.out.print("Enter character's moral alignment: ");
                    String alignment = kb.nextLine();
                    Character newCharacter = new Character(name, race, charClass, alignment);
                    System.out.print("Enter new password: ");
                    String password = kb.nextLine();

                    String message = characterTable.put(password, newCharacter);
                    System.out.println(message);
                    break;
                case "2":
                    System.out.print("Enter your character's password: ");
                    password = kb.nextLine();
                    Character character = characterTable.get(password);
                    if (character == null){
                        System.out.println("\nERROR: Character not found");
                        break;
                    }
                    else {
                        character.printInfo();
                    }
                    break;
                case "3":
                    System.out.print("Enter character's password: ");
                    password = kb.nextLine();
                    message = characterTable.delete(password);
                    System.out.println(message);
                    break;
                case "4":
                    System.out.println(characterTable.printNumberOfCharacters());
                    break;
                case "5":
                    done = true;
                    break;
                default:
                    System.out.println("\nERROR: Invalid Input");
            }
        }

        kb.close();
        System.out.println("\nRPG Character Database Shutting Down");
    }
}
