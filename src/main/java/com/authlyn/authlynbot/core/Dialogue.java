package com.authlyn.authlynbot.core;

public class Dialogue {

    /*
    Takes the entire dialogue list from a specific NPC, and then returns
    a random dialogue string
     */
    public static String getDialogue(String[] dialogueList) {
        // Grabs the length of the list
        int listLength = dialogueList.length;

        // Random int based on the length of the list (example: 0-10)
        int number = (int) Math.floor(Math.random() * listLength);
        return dialogueList[number];
    }

    public static void main(String[] args) {
        String[] curseWords = {"Fuck", "Shit", "Bitch", "Scheisse", "Pfpfprrfprrfpr"};

        System.out.println(getDialogue(curseWords));
    }
}
