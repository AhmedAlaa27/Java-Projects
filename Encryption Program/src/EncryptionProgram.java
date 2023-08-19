import java.util.*;

public class EncryptionProgram {
    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private ArrayList<Character> shuffledList;
    private char character;
    private String line;
    private char[] letters;
    EncryptionProgram () {
        scanner = new Scanner(System.in);
        random = new Random();
        list = new ArrayList<>();
        shuffledList = new ArrayList<>();
        character = ' ';

        newKey(); //Automatic generate new key when starting the program.
        askQuestion(); //Calling the main method.
    }
    private void askQuestion(){
        while (true){
            System.out.println("*************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey, (G)etKey, (E)ncrypt, (D)ecrypt, (Q)uit");
            //User should choose from these options.
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));
            switch (response){
                case 'N': newKey(); //Generating new key.
                break;
                case 'G': getKey(); //Get the key.
                break;
                case 'E': encrypt(); //Encrypt a message.
                break;
                case 'D': decrypt(); //Decrypt a message.
                break;
                case 'Q': quit(); //Quit the program.
                break;
                default:
                    System.out.print("Invalid choice -_-"); //If the user typed other thins.
            }
        }
    }
    private void newKey(){  //The generate new key method.
        character = ' ';
        list.clear(); //Clear the past letters list.
        shuffledList.clear(); //Clear the past shuffled letters list.

        for (int i = 32; i < 127; i++){ //Make a new letters list.
            list.add(Character.valueOf(character));
            character++;
        }
        shuffledList = new ArrayList<>(list);
        Collections.shuffle(shuffledList); //Make a new shuffled letters list.
        System.out.println("*A new key has been generated :)*");
    }
    private void getKey(){
        System.out.println("Key: ");
        for (Character x : list){ //Print the letters list.
            System.out.print(x);
        }
        System.out.println();
        for (Character x : shuffledList){ //Print the shuffled letters list.
            System.out.print(x);
        }
        System.out.println();
    }
    private void encrypt(){ //You need a message to encrypt
        System.out.println("Enter a message to be encrypted");
        String message = scanner.nextLine();
        letters = message.toCharArray(); //Assign the message to the letters list.

        for (int i = 0; i < letters.length; i++){ //Encrypting the message
            for (int j = 0; j < list.size(); j++){
                if (letters[i] == list.get(j)){ //Finding the corresponding letter.
                    //Converting the corresponding letter to the letter in shuffled list with same index.
                    letters[i] = shuffledList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted message: ");
        for (char x : letters){ //Print the encrypted message.
            System.out.print(x);
        }
        System.out.println();
    }
    private void decrypt(){ //--> You need the encrypted version also the same corresponding key <--
        System.out.println("Enter a message to be decrypted");
        String message = scanner.nextLine();
        letters = message.toCharArray(); //Assign the message to the letters list.

        for (int i = 0; i < letters.length; i++){
            for (int j = 0; j < shuffledList.size(); j++){
                if (letters[i] == shuffledList.get(j)){ //Finding the corresponding letter in shuffled list.
                    //Converting the corresponding shuffled letter to the letter in letters list with same index.
                    letters[i] = list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted message: ");
        for (char x : letters){  //Print the decrypted message.
            System.out.print(x);
        }
        System.out.println();
    }
    private void quit(){
        System.out.println("Thank you. Have a nice day Bro!");
        System.exit(0); //Exiting program.
    }
}
