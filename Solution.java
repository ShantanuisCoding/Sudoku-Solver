public class Solution {
    public boolean helper(char[][] board, char val, int r, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == val && i != c) {
                return false;
            }
            if (board[i][c] == val && i != r) {
                return false;
            }
            if (board[3 * (r / 3) + i / 3][3 * (c / 3) + i % 3] == val && r != (3 * (r / 3) + i / 3) && (3 * (c / 3) + i % 3) != c) {
                return false;
            }
        }

        return true;
    }

    public boolean help(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        if (helper(board, k, i, j)) {
                            board[i][j] = k;
                            if (help(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    if (board[i][j] == '.') {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void solveSudoku(char[][] board) {
        help(board);
    }
}
