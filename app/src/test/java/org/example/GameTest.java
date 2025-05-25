package org.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class GameTest {
    private Game game;
    private Player playerX;
    private Player playerO;

    @Before
    public void setUp() {
        game = new Game();
        playerX = game.player1;
        playerO = game.player2;
    }

    @Test
    public void testCheckStateXWin() {
        char[] board = {
                'X', 'X', 'X',
                ' ', 'O', ' ',
                'O', ' ', ' '
        };
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    @Test
    public void testCheckStatePlaying() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                ' ', ' ', ' '
        };
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    public void testEvaluatePositionLoss() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(board, game.player1));
    }

    @Test
    public void testMiniMaxBlocksWinScenario() {
        char[] board = {
                'X', ' ', ' ',
                ' ', 'O', ' ',
                ' ', ' ', 'X'
        };
        game.board = board;
        int bestMove = game.MiniMax(board, game.player2);
        assertTrue(bestMove > 0);
    }

    @Test
    public void testMinMoveTerminalState() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(Game.INF, game.MinMove(board, game.player1));
    }

    @Test
    public void testMaxMoveTerminalState() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(Game.INF, game.MaxMove(board, game.player2));
    }


    @Test
    public void testPrintIntArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(arr);
    }
    @Test
    public void testMainMethodRunsWithoutExceptions() {
        try {
            Program.main(new String[]{});
        } catch (Exception e) {
            fail("GUI launch failed: " + e.getMessage());
        }
    }


    @Test
    public void testCellInitialState() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
    }


    @Test
    public void testPlayerMoveInitialization() {
        Player player = new Player();
        player.move = 5;
        assertEquals(5, player.move);
    }

    @Test
    public void testCheckStateOWin() {
        char[] board = {
                'O', ' ', ' ',
                'O', 'X', ' ',
                'O', ' ', 'X'
        };
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    public void testCheckStateDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    public void testGenerateMoves() {
        char[] board = {
                'X', ' ', 'O',
                ' ', ' ', ' ',
                ' ', 'X', ' '
        };
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 6, 8}, moves.toArray());
    }

    @Test
    public void testEvaluatePositionWin() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(board, playerX));
    }

    @Test
    public void testMiniMaxBlocksImmediateLoss() {
        char[] board = {
                'X', ' ', 'X',
                'O', 'O', ' ',
                ' ', ' ', ' '
        };
        game.board = board;
        int bestMove = game.MiniMax(board, playerO);
        assertEquals(2, bestMove); // Должен заблокировать X
    }
    @Test
    public void testPlayerInitialization() {
        Game game = new Game();
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }
    @Test
    public void testPrintBoard() {
        Utility.print(new char[]{'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' '});
    }

    @Test
    public void testPrintMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(1);
        moves.add(3);
        Utility.print(moves);
    }
    @Test
    public void testSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }
}
