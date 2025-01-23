#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    bool helper(vector<vector<char>>& board, char val, int r, int c) {
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

    bool help(vector<vector<char>>& board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char k = '1'; k <= '9'; k++) {
                        bool valid = helper(board, k, i, j);
                        if (valid) {
                            board[i][j] = k;
                            bool next_step = help(board);
                            if (!next_step) {
                                board[i][j] = '.';
                            } else {
                                break;
                            }
                        }
                    }
                    if (board[i][j] == '.') return false;
                }
            }
        }

        return true;
    }

    void solveSudoku(vector<vector<char>>& board) {
        help(board);
    }
};

// Function to display the Sudoku board
void displayBoard(const vector<vector<char>>& board) {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cout << board[i][j]<<" ";
        }
        cout << endl;
    }
}

int main() {
    Solution solver;
    vector<vector<char>> board(9, vector<char>(9, '.'));

    cout << "Enter the Sudoku puzzle (use '.' for empty cells):\n";
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> board[i][j];
        }
        cout<<endl;
    }
// 3 . 5 . 7 . . 2 6   

// 7 2 6 9 . . . . 1

// . . 3 . 2 4 . . 8

// 6 . . 5 1 . 3 4 2

// 2 . . . 8 . 1 . 7

// . 7 . 2 . . . 8 .

// 1 6 . 8 . . 7 3 4 

// . . . 4 . . . . .

    cout << "Input Sudoku Puzzle:\n";
    displayBoard(board);

    solver.solveSudoku(board);

    cout << "\nSolved Sudoku Puzzle:\n";
    displayBoard(board);

    return 0;
}
