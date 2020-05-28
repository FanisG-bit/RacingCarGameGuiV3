package Game;

public class AdditionalMethods {
    public boolean playsFirst(Player player1, Player player2){
        return player1.throwDie() > player2.throwDie();
    }

    public boolean landedOnEndTile(Car car){
        boolean endGame = false;
        if (car.getPositionRow() == 0 && car.getPositionColumn() == 0){
            endGame = true;
        }
        return endGame;
    }
}