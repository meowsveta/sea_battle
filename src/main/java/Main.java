import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] playingField1 = new int[10][10]; //игровое поле первого игрока
        int[][] playingField2 = new int[10][10]; //поле с кораблями второго игрока

        int[][] opponentsField1 = new int[10][10]; //игровое поле соперника 1 игрока (корабли 2)
        int[][] opponentsField2 = new int[10][10]; //игровое поле соперника 2 игрока (корабли 1)

        int countShipsPlayer1 = 0; // кол - во кораблей 1го игрока
        int countShipsPlayer2 = 0; // кол - кораблей 2го игрока

        Field field1 = new Field(playingField1, countShipsPlayer1, opponentsField1, countShipsPlayer1); // поле первого игрока
        Field field2 = new Field(playingField2, countShipsPlayer2, opponentsField2, countShipsPlayer2); //поле второго игрока

        System.out.println("Игрок 1, введите ваше имя: ");
        String namePlayer1 = scanner.next();
        System.out.println("Игрок 2, введите ваше имя: ");
        String namePlayer2 = scanner.next();


        while (field1.countShipsPlayer < 4 && field2.countShipsPlayer < 4) {
            field1.drawField(playingField1);
            field1.shipsOnField(namePlayer1); // расстановка кораблей 1 игрока
            field2.drawField(playingField2);
            field2.shipsOnField(namePlayer2); // расстановка кораблей 2 игрока
        }

        System.out.println("Игра началась.");

        while (field1.countCell != 0 && field2.countCell != 0) {
            System.out.println("Поле игрока: " + namePlayer2 + ".  \n0 - неизвестно, 1 - корабль, 2 - мимо");
            field1.drawField(opponentsField1);
            field2.motion(playingField2, namePlayer1, opponentsField1);
            if (field2.countCell == 0) {
                System.out.println("Поздравляем! Игрок  " + namePlayer1 + " выиграл(а)!");
                break;
            }
            System.out.println("Поле игрока: " + namePlayer1+ ". 0 - неизвестно, 1 - корабль, 2 - мимо");
            field2.drawField(opponentsField2);
            field1.motion(playingField1, namePlayer2, opponentsField2);
                if (field1.countCell == 0) {
                    System.out.println("Поздравляем! Игрок " + namePlayer2 + " выиграл(а)!");
                    break;
                }
            }
        }
    }


