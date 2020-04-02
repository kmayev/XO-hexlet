package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class ConsoleView {
    public void show(final Game game){
        System.out.format("Game name=%S\n",game.getName());
        final Field field = game.getField();
        for (int x =0; x < field.getSize(); x++) {
            printline(field, x);
        }
        printSeparator();
    }

    public void move(final Game game){

    }
    private void printline(final Field field, final int x) {
        Figure figure = null;
     //   figure = field.getFigure(new Point(x, y));
        printSeparator();
        System.out.print("|");
        for (int y = 0; y < field.getSize(); y++) {
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }
                System.out.print(figure != null ? figure : " ");
                System.out.print('|');
        }
        System.out.println();
    }
    private void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~");
    }
}
