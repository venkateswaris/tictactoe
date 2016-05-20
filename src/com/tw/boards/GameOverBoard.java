package com.tw.boards;

import com.tw.Player;

import java.util.Optional;

public class GameOverBoard extends Board {

    private Optional<Player> winner ;

    private GameOverBoard(Player[] cells, Optional<Player> winner) {
        this.winner = winner;
        this.cells = cells;
    }

    static GameOverBoard create(Player[] cells, Optional<Player> winner) {
        return new GameOverBoard(cells, winner);
    }

    public boolean isGameDraw(){
        return !winner.isPresent();
    }

    public Player whoWon(){
        return winner.isPresent() ? winner.get() : null;
    }
}
