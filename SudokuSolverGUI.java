import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class SudokuSolverGUI extends Frame {
    private JTextField[][] sudokuCells;
    private Button solveButton;
    private Button clearButton;

    public SudokuSolverGUI() {
        setTitle("Sudoku Solver");
        setSize(400, 400);
        setLayout(new GridLayout(8, 8));

        sudokuCells = new JTextField[8][8];
        solveButton = new Button("Solve");
        clearButton = new Button("Clear");

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sudokuCells[i][j] = new JTextField(1);
                add(sudokuCells[i][j]);

                // Limit input to one character
                JTextField cell = sudokuCells[i][j];
                PlainDocument doc = (PlainDocument) cell.getDocument();
                doc.setDocumentFilter(new DocumentFilter() {
                    @Override
                    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                            throws BadLocationException {
                        if (offset + text.length() > 1) {
                            return;
                        }
                        super.replace(fb, offset, length, text, attrs);
                    }
                });
            }
        }

        Panel buttonPanel = new Panel();
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        add(buttonPanel);

        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearSudoku();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new SudokuSolverGUI();
    }

    private void solveSudoku() {
        char[][] board = new char[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String cellValue = sudokuCells[i][j].getText();
                if (!cellValue.isEmpty()) {
                    board[i][j] = cellValue.charAt(0);
                } else {
                    board[i][j] = '.';
                }
            }
        }

        Solution solver = new Solution();
        solver.solveSudoku(board);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sudokuCells[i][j].setText(String.valueOf(board[i][j]));
            }
        }
    }

    private void clearSudoku() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sudokuCells[i][j].setText("");
            }
        }
    }
}
