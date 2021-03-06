package com.company;

import java.util.Random;

import static com.company.TextColor.*;

public class Board {

    private int up;
    private int right;
    int yourX;
    int yourY;
    private int exitX;
    private int exitY;
    private int luck;
    private Random rnd = new Random(System.currentTimeMillis());
    private Fight newFight;

    Board() {

        this.up = rnd.nextInt(15) + 5;
        this.right = rnd.nextInt(15) + 5;
        this.yourX = right / 2 + 1;
        this.yourY = up / 2 + 1;
        this.exitX = rnd.nextInt(right) + 1;
        this.exitY = rnd.nextInt(up) + 1;
        this.luck = 15;
        this.newFight = new Fight();

    }

    //i like to move it move it!
    void move(char move) {


        switch (move) {
            case ('w'):
            case ('W'):
                checkEdgeAndMove(this.yourY,this.yourY++);
                System.out.println("Идем в " + this.yourY + " " + this.yourX);
                break;
            case ('d'):
            case ('D'):
                checkEdgeAndMove(this.yourX,this.yourX++);
                System.out.println("Идем в " + this.yourY + " " + this.yourX);
                break;
            case ('s'):
            case ('S'):
                checkEdgeAndMove(this.yourY,this.yourY--);
                System.out.println("Идем в " + this.yourY + " " + this.yourX);
                break;
            case ('a'):
            case ('A'):
                checkEdgeAndMove(this.yourX,this.yourX--);
                System.out.println("Идем в " + this.yourY + " " + this.yourX);
                break;
            case ('m'):
            case ('M'):
                System.out.println(ANSI_BLUE + "Ваши координаты: " + this.yourY + " " + this.yourX + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Выход в точке: " + this.exitY + " " + this.exitX + ANSI_RESET);
                System.out.println(ANSI_BLUE + "Размер уровня:  " + this.up + " " + this.right + ANSI_RESET);
                System.out.println(ANSI_BLUE + "HP :  "+ newFight.getHp() + ANSI_RESET);
                break;
            default:
                System.out.println(ANSI_RED + "неверный ход" + ANSI_RESET);
                break;
        }
}

    // possibility to meet the enemy
    private void checkLuck(int luck) {
        int number = rnd.nextInt(99);
        if (number <= this.luck) {
            newFight.start();
        } else {
            if (number >= (100 - this.luck)) {
                newFight.healPlayer(10);
            } else {
                System.out.println(ANSI_RED + "сектор чист" + ANSI_RESET);
            }
        }
    }

    // check for exit LvL

    public boolean scanExit() {
        return (this.yourX == this.exitX) & (this.yourY == this.exitY);
    }

    private void checkEdgeAndMove(int cell, int direction) {
        if (((this.yourY > this.up) | (this.yourY < 1)) | ((this.yourX > this.right) | (this.yourX < 1))) {
            if (this.yourY < 1) this.yourY = 1;
            if (this.yourY > this.up) this.yourY = up;
            if (this.yourX < 1) this.yourX = 1;
            if (this.yourX > this.right) this.yourX = this.right;
            System.out.println(ANSI_RED + "вы уперлись в стену" + ANSI_RESET);
        }else{
            makeStep(cell,direction);
        }
    }
    private void makeStep (int cell, int direction){
        cell=direction;
        checkLuck(this.luck);
    }

}