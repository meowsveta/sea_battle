import java.util.Scanner;

public class Field { //pole
    Scanner scanner = new Scanner(System.in);
    int countCell; // кол-во ячеек. при попадании это кол-во уменьшать на 1 //если останется 0, то игрок выиграл
    int[][] playingField;  //игровое поле игрока
    int[][] opponentsField; // поле соперника
    int countShipsPlayer; // кол - во кораблей


    public Field(int[][] playingField, int countShipsPlayer, int[][] opponentsField, int countCell) {
        this.playingField = playingField;
        this.countShipsPlayer = countShipsPlayer;
        this.countCell = countCell;
        this.opponentsField = opponentsField;
    }

    public void drawField(int[][] field) {// отображение на экране текущего поля
        System.out.println("   0  1  2  3  4  5  6  7  8  9 ");
        for (int x = 0; x < field.length; x++) {
            System.out.print(x + " ");
            for (int y = 0; y < field.length; y++) {
                System.out.print(" " + field[x][y] + " ");
            }
            System.out.println();
        }
    }


    public void shipsOnField(String name) {
        System.out.println(name + ", добавляйте корабль. Введите количество палуб.");
        int deck = scanner.nextInt();
        System.out.println("Введите координаты X");
        int x = scanner.nextInt();
        System.out.println("Введите координаты У");
        int y = scanner.nextInt();
        System.out.println("Если установить по-вертикали - введите 1. Если по горизонтали - 2");
        int position = scanner.nextInt();

        if (isCorrected(playingField, x, y, deck, position)) {
            if (position == 1) {
                for (int a = 0; a < deck; a++) {//  по вертикали
                    playingField[x + a][y] = 1;
                }
                System.out.println("Установлен корабль по-вертикали ");
                countShipsPlayer++;
                countCell += deck;
                System.out.println("кол - во кораблей: " + countShipsPlayer);
            } else {
                for (int a = 0; a < deck; a++) {//  по горизонтали
                    playingField[x][y + a] = 1;
                }
                System.out.println("Установлен корабль по-горизонтали ");
                countShipsPlayer++;
                countCell += deck;
                System.out.println("кол - во кораблей: " + countShipsPlayer);
            }
        } else {
            System.out.println("Ошибка. Попробуйте ещё раз");
            shipsOnField(name);
        }
    }

    public void motion(int[][] field, String name, int[][] opponentsField) { // ход
        System.out.println(name + " , ведите координаты Х");
        int x = scanner.nextInt();
        System.out.println("Введите координаты Y");
        int y = scanner.nextInt();

        if (field[x][y] == 1) {
            countCell--;
            opponentsField[x][y] = 1;
            if (countCell != 0) {
                System.out.println("Вы попали. Ходите еще раз.");
                motion(field, name, opponentsField);
            }
            if (countCell == 0) {
                System.out.println("Кораблей больше не осталось.");
            }
        }
        if (field[x][y] == 0) {
            System.out.println("Мимо. Переход кода другому игроку");
            field[x][y] = 2;
            opponentsField[x][y] = 2;
        }
    }

    public boolean isCorrected(int[][] field, int x, int y, int deck, int position) {
        boolean ok = false;
        try {
            if (position == 1 && ((x + deck) > field.length)) { //  по-вертикали
                return false;
            }
            if (position == 2 && ((y + deck) > field.length)) { // по-горизонтали
                return false;
            }
            if (field[x + 1][y + 1] == 1 || field[x - 1][y - 1] == 1 || field[x - 1][y + 1] == 1 || field[x + 1][y - 1] == 1) { // по диагонали
                return false;
            }
            for (int i = deck; i > 0; i--) {
                        ok = field[x][y] != 1 &&
                        field[x - 1][y] != 1 &&
                        field[x][y - 1] != 1 &&
                        field[x + i][y] != 1 &&
                        field[x][y + i] != 1;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex){
            if (x == 0 && field[x + 1][y + 1] == 0 &&  field[x + 1][y - 1] == 0 ){
                ok = true;
            }
            if (y == 0 && field[x + 1][y + 1] == 0 &&  field[x - 1][y + 1] == 0 ){
                ok = true;
            }
            if (x == 9 && !(y == 9) && field[x - 1][y - 1] == 0 && field[x - 1][y + 1] == 0 ) {
                ok = true;
            }
            if (y == 9 && !(x == 9) && field[x - 1][y - 1] == 0 && field[x + 1][y - 1] == 0 ) {
                ok = true;
            }
            if (x == 9 && y == 9 && field[x - 1][y - 1] == 0 ) {
                ok = true;
            }
            return ok;
        }
        return ok;
    }
}
