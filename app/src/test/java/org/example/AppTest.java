package org.example;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AppTest 
    extends TestCase
{
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
    public void testEvaluatePositionPlaying() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                ' ', ' ', ' '
        };
        game.symbol = 'X';
        assertEquals(-1, game.evaluatePosition(board, game.player1));
    }

    @Test
    public void testMinMoveNonTerminal() {
        char[] board = {
                'X', ' ', ' ',
                ' ', 'O', ' ',
                ' ', ' ', ' '
        };
        game.symbol = 'O';
        assertTrue(game.MinMove(board, game.player2) < Game.INF);
    }

    @Test
    public void testMaxMoveNonTerminal() {
        char[] board = {
                'X', ' ', ' ',
                ' ', 'O', ' ',
                ' ', ' ', 'X'
        };
        game.symbol = 'X';
        assertTrue(game.MaxMove(board, game.player1) > -Game.INF);
    }

    @Test
    public void testMiniMaxEmptyBoard() {
        char[] board = new char[9];
        Arrays.fill(board, ' ');
        int move = game.MiniMax(board, game.player1);
        assertTrue(move > 0 && move <= 9);
    }

    @Test
    public void testMiniMaxWinImmediately() {
        char[] board = {
                'X', 'X', ' ',
                'O', 'O', ' ',
                ' ', ' ', ' '
        };
        game.symbol = 'X';
        int move = game.MiniMax(board, game.player1);
        assertEquals(3, move); // Должен завершить линию
    }

    @org.junit.Test
    public void testCheckStateXWin() {
        char[] board = {
                'X', 'X', 'X',
                ' ', 'O', ' ',
                'O', ' ', ' '
        };
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }
    @org.junit.Test
    public void testCheckStatePlaying() {
        char[] board = {
                'X', 'O', 'X',
                'O', 'X', 'O',
                ' ', ' ', ' '
        };
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @org.junit.Test
    public void testEvaluatePositionLoss() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(board, game.player1));
    }

    @org.junit.Test
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

    @org.junit.Test
    public void testMinMoveTerminalState() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(Game.INF, game.MinMove(board, game.player1));
    }

    @org.junit.Test
    public void testMaxMoveTerminalState() {
        char[] board = {'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'O';
        assertEquals(Game.INF, game.MaxMove(board, game.player2));
    }
    /*@Test
    public void testActionPerformedPlayerSwitch() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        ActionEvent mockEvent = new ActionEvent(panel.cells[0], ActionEvent.ACTION_PERFORMED, "");

        panel.actionPerformed(mockEvent);
        assertEquals('O', panel.game.cplayer.symbol);

        panel.actionPerformed(mockEvent);
        assertEquals('X', panel.game.cplayer.symbol);
    }

    @Test
    public void testActionPerformedGameEnd() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        // Создаем выигрышную ситуацию для X
        panel.cells[0].setMarker("X");
        panel.cells[1].setMarker("X");
        panel.game.board[0] = 'X';
        panel.game.board[1] = 'X';

        ActionEvent mockEvent = new ActionEvent(panel.cells[2], ActionEvent.ACTION_PERFORMED, "");
        panel.actionPerformed(mockEvent);

        assertEquals(State.XWIN, panel.game.state);
    }*/

    @Test
    public void testCellGetters() {
        TicTacToeCell cell = new TicTacToeCell(5, 2, 1);
        assertEquals(5, cell.getNum());
        assertEquals(2, cell.getCol());
        assertEquals(1, cell.getRow());
    }

    @Test
    public void testCellStateAfterSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("O");
        assertFalse(cell.isEnabled());
        assertEquals('O', cell.getMarker());
    }

    @org.junit.Test
    public void testPrintIntArray() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Utility.print(arr);
    }
    @org.junit.Test
    public void testMainMethodRunsWithoutExceptions() {
        try {
            Program.main(new String[]{});
        } catch (Exception e) {
            fail("GUI launch failed: " + e.getMessage());
        }
    }


    @org.junit.Test
    public void testCellInitialState() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
    }


    @org.junit.Test
    public void testPlayerMoveInitialization() {
        Player player = new Player();
        player.move = 5;
        assertEquals(5, player.move);
    }

    @org.junit.Test
    public void testCheckStateOWin() {
        char[] board = {
                'O', ' ', ' ',
                'O', 'X', ' ',
                'O', ' ', 'X'
        };
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @org.junit.Test
    public void testCheckStateDraw() {
        char[] board = {
                'X', 'O', 'X',
                'X', 'O', 'O',
                'O', 'X', 'X'
        };
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @org.junit.Test
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

    @org.junit.Test
    public void testEvaluatePositionWin() {
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(board, playerX));
    }

    @org.junit.Test
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
    @org.junit.Test
    public void testPlayerInitialization() {
        Game game = new Game();
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
    }
    @org.junit.Test
    public void testPrintBoard() {
        Utility.print(new char[]{'X', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' '});
    }

    @org.junit.Test
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
