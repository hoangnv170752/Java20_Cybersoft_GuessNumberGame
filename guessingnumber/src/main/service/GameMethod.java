package guessingnumber.service;

import java.util.*;

import guessingnumber.model.*;

import static guessingnumber.service.GameConstant.*;
import static java.lang.Integer.*;
import static java.util.Collections.*;

public class GameMethod {
    // fields
    public static List<Player> players = new ArrayList<>();
    public static Player current_player;
    public static String register_name = "";
    public static String register_state = "";
    public static String check_state = "";
    public static String is_msgBox = "false";
    public static String is_dialog = "false";
    public static int guess_num = -1;

    // create player
    public static Player createPlayer(String name, int counter) {
        var player = new Player();
        player.setPlayerName(name);
        player.setCounter(counter);
        return player;
    }

    // remove player
    public static void dropPlayer(String name) {
        players.removeIf(player -> player.getPlayerName().equals(name)); // fix ConcurrentModificationException
    }

    public static void checkRegister(String name) {
        register_name = name;
        register_state = "UNREGISTERED";
        if (players.size() > 0) {
            for (var player : players) {
                if (player.getPlayerName().equals(register_name)) {
                    register_state = "REGISTERED";
                }
            }
        }
    }

    public static void checkGamePlay(int num, int x) {
        check_state = num == x ? "CORRECT" : num == guess_num ? "EXIST" : "";
        is_dialog = check_state == "" ? "false" : "true";
        guess_num = num;
    }

    public static String strAnswer(int num, int x) {
        return num > x ? BIGGER : num < x ? SMALLER : CORRECT;
    }

    public static void mainReset() {
        register_name = "";
        register_state = "";
        is_msgBox = "false";
    }

    public static void subReset() {
        check_state = "";
        is_dialog = "false";
        guess_num = -1;
    }

    public static void updateCurrentPlayer(int counter) {
        current_player.setCounter(counter);
        players.add(current_player);
    }

    public static void playersRanking() {
        sort(players, (o1, o2) -> compare(o1.getCounter(), o2.getCounter()));
    }
}
