package io.hexlet.xo.controllers;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.awt.*;

public class WinnerController {

    public Figure getWinner(final Field field) {

        final Point p = new Point();
        int countX = 0;
        int countO = 0;
        final Figure o = Figure.O;
        final Figure x = Figure.X;
        final int fSize = field.getSize();

        try {

        //        проверяю строки
        for (p.x = 0; p.x < fSize; p.x++) {
            for (p.y = 0; p.y < fSize; p.y++) {
                if (field.getFigure(p) == null) continue;
                if (field.getFigure(p) == x) countX++;
                if (field.getFigure(p) == o) countO++;
            }
            if (countX == fSize) return x;
            if (countO == fSize) return o;
            countX = 0;
            countO = 0;
        }
// тут  проверяю по столбцам
        countX = 0;
        countO = 0;
        for (p.y = 0; p.y < fSize; p.y++) {
            for (p.x = 0; p.x < fSize; p.x++) {
                if (field.getFigure(p) == null) continue;
                if (field.getFigure(p) == x) countX++;
                if (field.getFigure(p) == o) countO++;
            }
            if (countX == fSize) return x;
            if (countO == fSize) return o;
            countX = 0;
            countO = 0;
        }
// тут проверка диагоналей
        countX = 0;
        countO = 0;
        p.y = 0;
        for (p.x = 0; p.x < fSize; p.x++) {
            if (field.getFigure(p) == null) {
                p.y++;
                continue;
            }
            if (field.getFigure(p) == x) countX++;
            if (field.getFigure(p) == o) countO++;
            p.y++;
        }
        if (countX == fSize) return x;
        if (countO == fSize) return o;
        countX = 0;
        countO = 0;
        p.y = fSize - 1;
        for (p.x = 0; p.x < fSize; p.x++) {
            if (field.getFigure(p) == null) {
                p.y--;
                continue;
            }
            if (field.getFigure(p) == x) countX++;
            if (field.getFigure(p) == o) countO++;
            p.y--;
        }
        if (countX == fSize) return x;
        if (countO == fSize) return o;

        return null;

        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return null;
    }
}

       /*
        try {
            for (int i = 0, i < 3; i++){
                if (check(field, new Point(1, 0), new IPointChanger() {
                    @Override
                    public Point next(Point p) {
                        return new Point(p.x, p.y + 1);
                    }
                }))
                return field.getFigure(new Point(i, 0));
            }
//        } catch(InvalidPointException e) {
  //              e.printStackTrace();
    //        }

    }
    private boolean check (final Field field,
                           final Point startPoint,
                           final IPointChanger pointChanger){

        final Point p1 = startPoint;
        final Point p2 = pointChanger.next(p1);
        final Point p3 = pointChanger.next(p2);

        try {
            if (field.getFigure(p1) == null) return false;
            if (field.getFigure(p1) == field.getFigure(p2) &&
                    field.getFigure(p1) == field.getFigure(p3))
                return true;
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static interface IPointChanger {
        Point next(final Point p)
    }
*/