package io.hexlet.xo.view;

import io.hexlet.xo.controllers.CurrentMoveController;
import io.hexlet.xo.controllers.MoveController;
import io.hexlet.xo.controllers.WinnerController;
import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;
import java.util.Scanner;

public class ConsoleView {
    private final CurrentMoveController currentMoveController = new CurrentMoveController();
    private final WinnerController winnerController = new WinnerController();
    private final MoveController moveController = new MoveController();
    public void show(final Game game){
        System.out.format("Game name=%S\n",game.getName());
        final Field field = game.getField();
        for (int x =0; x < field.getSize(); x++) {
            if (x != 0) printSeparator();
            printline(field, x);
        }
    }

    public boolean move(final Game game){
        final Field field = game.getField();
        final Figure currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            final Figure winner = winnerController.getWinner(field);
            if (winner == null) {
                System.out.println("No Winner, No Movs left");
                return false;
            } else {
                System.out.format("WINNER IS %S", winner);
                return false;
            }
        }
        System.out.format("Please enter new point for %S", currentFigure);
        final Point point = askPoint();
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (InvalidPointException | AlreadyOccupiedException e) {
            e.printStackTrace();
            System.out.println("Point is invalid!");
        }
        return false;
    }
    private Point askPoint() {
        return new Point(askCoordinate("X")-1, askCoordinate("Y")-1);
    }
    private int askCoordinate (final String coordinateName) {
        System.out.format("Please input %S",coordinateName);
        final Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    private void printline(final Field field, final int x) {
        Figure figure = null;
        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0) System.out.print('|');
            System.out.print(' ');
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }
                System.out.print(figure != null ? figure : " ");
                System.out.print(' ');
        }
        System.out.println();
    }
    private void printSeparator() {
        System.out.println("~~~~~~~~~~~~");
    }
}
