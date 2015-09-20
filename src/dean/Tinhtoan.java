/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dean;

import java.util.Stack;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

/**
 *
 *@author Tuan Anh
 */
public class Tinhtoan {

    float T[];
    float P[];
    float B[][];

    float C[][];
    double min = 0;
    float sum1;

    int col, row;

    int x, y;
    float[] r;
    float[] s;

    Stack<Nodepro> Duongdi = new Stack<>();
    Stack<Nodepro> Chutrinh = new Stack<>();
    Nodepro node;

    public float[][] setAll(float[] Thu, float[] Phat, float[][] Cost, int x, int y) {
        this.T = Thu;
        this.P = Phat;
        this.B = Cost;
        this.x = x;
        this.y = y;

        System.out.println("Test thu phat" + Thu[0] + " " + Phat[0]);

        PACB();
        Quayvong();

        sum1 = mincuoc();
        System.out.println("Min cuoc: " + mincuoc());
        System.out.println("Phuong an: ");
        for (int a = 0; a < x; a++) {
            {
                for (int b = 0; b < y; b++) {
                    System.out.print(" " + C[a][b]);
                }
                System.out.println("");
            }
        }

        return C;
    }

    public float mincuoc() {

        float sum = 0;
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                sum += this.C[a][b] * this.B[a][b];
            }
        }
        return sum;
    }

    boolean isSet[][];
    boolean isColSet[];
    boolean isRowSet[];

    private void Quayvong() {
        while (true) {

            isSet = new boolean[x][y];
            isColSet = new boolean[y];
            isRowSet = new boolean[x];

            Giaihe();
            TinhC2();
            System.out.println("Day la min: " + min2);
            if (min2 >= 0) {
                break;
            }
            Chutrinh.clear();

            Timochonngang(TinhC2().gtrix, TinhC2().gtriy);
            System.out.println("");

            for (int mm = 0; mm < Chutrinh.size(); mm++) {
                System.out.println(Chutrinh.elementAt(mm).gtrix + " " + Chutrinh.elementAt(mm).gtriy);
            }

            SuaPACB();

        }
    }

    TilePane tilepro = new TilePane();
    BorderPane bd = new BorderPane();

    public boolean Check() {

        for (int a = 0; a < x; a++) {
            if (P[a] != 0) {
                return false;
            }
        }
        return true;
    }

    private void PACB() {

        C = new float[x][y];

        int p = 0;
        while (!Check()) {
            min = Float.MAX_VALUE;
            for (int a = 0; a < x; a++) {
                if (P[a] != 0) {
                    for (int b = 0; b < y; b++) {
                        if (T[b] != 0) {
                            if (B[a][b] <= min) {
                                min = B[a][b];

                            }
                        }
                    }
                }
            }

            for (int a = 0; a < x; a++) {
                for (int b = 0; b < y; b++) {
                    if (B[a][b] == min && C[a][b] == 0) {
                        if (T[b] >= P[a]) {
                            C[a][b] = P[a];
                            T[b] = T[b] - P[a];
                            P[a] = 0;

                        } else {
                            C[a][b] = T[b];
                            P[a] = P[a] - T[b];
                            T[b] = 0;

                        }
                    }
                }

            }
        }

        float sum = 0;
        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                System.out.print(C[a][b] + " ");
                sum += C[a][b] * B[a][b];
            }
            System.out.println("");
        }
        System.out.println(sum);

    }

    //PHAN GIAI HE
    public void Giaihe() {
        System.out.println(isSet[0][0]);

        for (int i2 = 0; i2 < x; i2++) {
            for (int j2 = 0; j2 < y; j2++) {
                isSet[i2][j2] = C[i2][j2] != 0;
            }
        }

        for (int i2 = 0; i2 < x; i2++) {
            isRowSet[i2] = false;
        }
        for (int i2 = 0; i2 < y; i2++) {
            isColSet[i2] = false;
        }
        r = new float[x];
        s = new float[y];

        r[0] = 0;
        isRowSet[0] = true;
        Timscon(0);

        for (int ii = 0; ii < x; ii++) {
            System.out.print(r[ii] + " ");
        }
        for (int ii = 0; ii < y; ii++) {
            System.out.print(s[ii] + " ");
        }

    }

    public void Timscon(int ii) {
        for (col = 0; col < y; col++) {
            if (isSet[ii][col] && !isColSet[col]) {
                s[col] = -(B[ii][col] + r[ii]);
                isColSet[col] = true;
                Timrcon(col);
            }
        }
    }

    public void Timrcon(int jj) {
        for (row = 0; row < x; row++) {
            if (isSet[row][jj] && !isRowSet[row]) {
                r[row] = -(B[row][jj] + s[jj]);
                isRowSet[row] = true;
                Timscon(row);
            }
        }
    }

    float min2;

    public Nodepro TinhC2() {
        node = new Nodepro();
        float[][] C2 = new float[x][y];
        min2 = Float.MAX_VALUE;

        for (int i3 = 0; i3 < x; i3++) {
            for (int j3 = 0; j3 < y; j3++) {
                C2[i3][j3] = B[i3][j3] + r[i3] + s[j3];
                if (C2[i3][j3] < min2) {
                    min2 = C2[i3][j3];
                    node = new Nodepro(i3, j3);
                }

            }
        }
        //System.out.println(min2);
        return node;
    }

    public void Timochonngang(int row2, int col2) {
        for (int j3 = 0; j3 < y; j3++) {

            node = new Nodepro(row2, j3);
            Duongdi.push(node);

            if (Duongdi.size() >= 4 && TinhC2().gtrix == row2 && TinhC2().gtriy == j3) {
                for (int mm = 0; mm < Duongdi.size(); mm++) {
                    Chutrinh.push(Duongdi.elementAt(mm));
                }
            }

            if (isSet[row2][j3] && j3 != col2) {
                Timochondoc(row2, j3);
            }

            Duongdi.pop();
        }
    }

    public void Timochondoc(int row2, int col2) {
        for (int i3 = 0; i3 < x; i3++) {
            node = new Nodepro(i3, col2);
            Duongdi.push(node);

            if (Duongdi.size() >= 4 && TinhC2().gtrix == i3 && TinhC2().gtriy == col2) {
                for (int mm = 0; mm < Duongdi.size(); mm++) {
                    Chutrinh.push(Duongdi.elementAt(mm));
                }
            }

            if (isSet[i3][col2] && i3 != row2) {
                Timochonngang(i3, col2);
            }

            Duongdi.pop();
        }
    }

    public void SuaPACB() {
        float minle = 0;
        if (Chutrinh.size() > 0) {
            minle = C[Chutrinh.elementAt(0).gtrix][Chutrinh.elementAt(0).gtriy];
        }

        for (int ii = 0; ii < Chutrinh.size(); ii += 2) {
            if (minle > C[Chutrinh.elementAt(ii).gtrix][Chutrinh.elementAt(ii).gtriy]) {
                minle = C[Chutrinh.elementAt(ii).gtrix][Chutrinh.elementAt(ii).gtriy];
            }
        }

        for (int ii = 0; ii < Chutrinh.size(); ii += 2) {
            C[Chutrinh.elementAt(ii).gtrix][Chutrinh.elementAt(ii).gtriy] -= minle;
        }

        for (int ii = 1; ii < Chutrinh.size(); ii += 2) {
            C[Chutrinh.elementAt(ii).gtrix][Chutrinh.elementAt(ii).gtriy] += minle;
        }

        for (int a = 0; a < x; a++) {
            for (int b = 0; b < y; b++) {
                System.out.print(C[a][b] + " ");
            }
            System.out.println("");

        }

    }

    public class Nodepro {

        int gtrix;
        int gtriy;

        public Nodepro() {
            this.gtrix = 0;
            this.gtriy = 0;
        }

        public Nodepro(int xxx, int yyy) {
            this.gtrix = xxx;
            this.gtriy = yyy;
        }

        public void setNode(int xxx, int yyy) {
            this.gtrix = xxx;
            this.gtriy = yyy;

        }

    }

}
