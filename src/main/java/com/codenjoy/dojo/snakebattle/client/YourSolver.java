package com.codenjoy.dojo.snakebattle.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;

import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;

/**
 * User: your name
 * Это твой алгоритм AI для игры. Реализуй его на свое усмотрение.
 * Обрати внимание на {@see YourSolverTest} - там приготовлен тестовый
 * фреймворк для тебя.
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;
    int headX = 0;
    int headY = 0;
    int length;
    int i;
    char[] snakeBodyParts = {'═', '║', '╗', '╝', '╔', '╚'};
    private static List stoneList = new ArrayList();
    String USER_NAME = "misha89ua@gmail.com";

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        if (board.isGameOver()) return "";

        String strBoard = board.toString();
        char [] arrayBoard = strBoard.toCharArray();
        char [][] doubleArrayBoard = new char[30][31];

        length = 2;
        i = 7;

        for (int x = 0; x < 30; x++){
            for (int y = 0; y < 31; y++){
                doubleArrayBoard[x][y] = arrayBoard[i];
                for (char p : snakeBodyParts){
                    if (doubleArrayBoard[x][y] == p){
                        length = length + 1;
                    }
                }
                i = i+1;
                if ((doubleArrayBoard[x][y] == '◄')||(doubleArrayBoard[x][y] == '►')||
                        (doubleArrayBoard[x][y] == '▼')||(doubleArrayBoard[x][y] == '▲')){
                    headX = x;
                    headY = y;
                }
            }
        }


        System.out.println(length);


       // MOOVING TO RIGHT
        if (doubleArrayBoard[headX][headY] == '►'){

            if ((doubleArrayBoard[headX][headY + 1] == '☼')||((doubleArrayBoard[headX][headY + 1] == '●')&&(length < 6))){
                if ((doubleArrayBoard[headX + 1][headY] == '☼')||((doubleArrayBoard[headX + 1][headY] == '●')&&(length < 6))){
                    return Direction.UP.toString();
                }else {
                    return Direction.DOWN.toString();
                }
            }

            if ((doubleArrayBoard[headX - 1][headY] == '○')||(doubleArrayBoard[headX - 1][headY] == '$')||
                    ((doubleArrayBoard[headX - 1][headY] == '●')&&(length > 6))){
                return Direction.UP.toString();
            }
            if ((doubleArrayBoard[headX + 1][headY] == '○')||(doubleArrayBoard[headX + 1][headY] == '$')||
                    ((doubleArrayBoard[headX + 1][headY] == '●')&&(length > 6))){
                return Direction.DOWN.toString();
            }
            return Direction.RIGHT.toString();
        }


        //MOOVING TO LEFT
        if (doubleArrayBoard[headX][headY] == '◄') {

            if ((doubleArrayBoard[headX][headY - 1] == '☼')||((doubleArrayBoard[headX][headY - 1] == '●')&&(length < 6))){
                if ((doubleArrayBoard[headX - 1][headY] == '☼')||((doubleArrayBoard[headX - 1][headY] == '●')&&(length > 6))){
                    return Direction.DOWN.toString();
                }else {
                    return Direction.UP.toString();
                }
            }
            if ((doubleArrayBoard[headX - 1][headY] == '○')||(doubleArrayBoard[headX - 1][headY] == '$')||
                    ((doubleArrayBoard[headX - 1][headY] == '●')&&(length > 6))){
                return Direction.UP.toString();
            }
            if ((doubleArrayBoard[headX + 1][headY] == '○')||(doubleArrayBoard[headX + 1][headY] == '$')||
                    ((doubleArrayBoard[headX + 1][headY] == '●')&&(length > 6))){
                return Direction.DOWN.toString();
            }
            return Direction.LEFT.toString();
        }


        //MOOVING TO UP
        if (doubleArrayBoard[headX][headY] == '▲'){

            if ((doubleArrayBoard[headX - 1][headY] == '☼')||((doubleArrayBoard[headX - 1][headY] == '●')&&(length < 6))){
                if ((doubleArrayBoard[headX][headY + 1] == '☼')||((doubleArrayBoard[headX][headY + 1] == '●')&&(length < 6))){
                    return Direction.LEFT.toString();
                }else {
                    return Direction.RIGHT.toString();
                }
            }
            if ((doubleArrayBoard[headX][headY - 1] == '○')||(doubleArrayBoard[headX][headY - 1] == '$')||
                    ((doubleArrayBoard[headX][headY - 1] == '●')&&(length > 6))){
                return Direction.LEFT.toString();
            }
            if ((doubleArrayBoard[headX][headY + 1] == '○')||(doubleArrayBoard[headX][headY + 1] == '$')||
                    ((doubleArrayBoard[headX][headY + 1] == '●')&&(length > 6))){
                return Direction.RIGHT.toString();
            }
            return Direction.UP.toString();
        }

        //MOOVING TO DOWN
        if (doubleArrayBoard[headX][headY] == '▼'){

            if ((doubleArrayBoard[headX + 1][headY] == '☼')||((doubleArrayBoard[headX + 1][headY] == '●')&&(length < 6))){
                if ((doubleArrayBoard[headX][headY - 1] == '☼')||((doubleArrayBoard[headX][headY - 1] == '●')&&(length < 6))){
                    return Direction.RIGHT.toString();
                }else {
                    return Direction.LEFT.toString();
                }
            }
            if ((doubleArrayBoard[headX][headY - 1] == '○')||(doubleArrayBoard[headX][headY - 1] == '$')||
                    ((doubleArrayBoard[headX][headY - 1] == '●')&&(length > 6))){
                return Direction.LEFT.toString();
            }
            if ((doubleArrayBoard[headX][headY + 1] == '○')||(doubleArrayBoard[headX][headY + 1] == '$')||
                    ((doubleArrayBoard[headX][headY + 1] == '●')&&(length > 6))){
                return Direction.RIGHT.toString();
            }
            return Direction.DOWN.toString();
        }






        return "";
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "https://game2.epam-bot-challenge.com.ua/codenjoy-contest/board/player/misha89ua@gmail.com?code=12219209101879681688",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
