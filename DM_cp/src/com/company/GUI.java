package com.company;

import com.sun.org.apache.xpath.internal.objects.XBoolean;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.*;

public class GUI extends JFrame {
    private JLabel empt = new JLabel("");
    private JButton button = new JButton("Начертить");

    private TextField forming_1 = new TextField("", 1);
    private TextField defining_left_1 = new TextField("", 1);
    private TextField defining_right_1 = new TextField("", 1);

    private TextField forming_2 = new TextField("", 1);
    private TextField defining_left_2 = new TextField("", 1);
    private TextField defining_right_2 = new TextField("", 1);

    private TextField forming_3 = new TextField("", 1);
    private TextField defining_left_3 = new TextField("", 1);
    private TextField defining_right_3 = new TextField("", 1);

    private TextField forming_4 = new TextField("", 1);
    private TextField defining_left_4 = new TextField("", 1);
    private TextField defining_right_4 = new TextField("", 1);

    private TextField forming_5 = new TextField("", 1);
    private TextField defining_left_5 = new TextField("", 1);
    private TextField defining_right_5 = new TextField("", 1);

    private TextField forming_6 = new TextField("", 1);
    private TextField defining_left_6 = new TextField("", 1);
    private TextField defining_right_6 = new TextField("", 1);


    private JLabel forming_label = new JLabel("Образующие");
    private JLabel forming_label_tip = new JLabel("(по одной образующей)");
    private JLabel forming_defining_left = new JLabel("Определяющие");
    private JLabel forming_defining_right = new JLabel("cоотношения");
    private JLabel forming_defining_left_tip = new JLabel("(a^2*b = e)        a2b ");
    private JLabel forming_defining_right_tip = new JLabel("                                   e");

    public GUI() {
        super("Задание графа по образующим и определяющим соотношениям");
        this.setBounds(100, 100, 600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(9, 3, 2, 2));
        container.add(forming_label);
        container.add(forming_defining_left);
        container.add(forming_defining_right);
        container.add(forming_label_tip);
        container.add(forming_defining_left_tip);
        container.add(forming_defining_right_tip);
        container.add(forming_1);
        container.add(defining_left_1);
        container.add(defining_right_1);
        container.add(forming_2);
        container.add(defining_left_2);
        container.add(defining_right_2);
        container.add(forming_3);
        container.add(defining_left_3);
        container.add(defining_right_3);
        container.add(forming_4);
        container.add(defining_left_4);
        container.add(defining_right_4);
        container.add(forming_5);
        container.add(defining_left_5);
        container.add(defining_right_5);
        container.add(forming_6);
        container.add(defining_left_6);
        container.add(defining_right_6);
        container.add(empt);
        container.add(empt);
        button.addActionListener(new ButtonEventListener());
        container.add(button);
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            String[][] matr = new String[3][6];

            matr[0][0] = forming_1.getText();
            matr[0][1] = forming_2.getText();
            matr[0][2] = forming_3.getText();
            matr[0][3] = forming_4.getText();
            matr[0][4] = forming_5.getText();
            matr[0][5] = forming_6.getText();

            matr[1][0] = defining_left_1.getText();
            matr[1][1] = defining_left_2.getText();
            matr[1][2] = defining_left_3.getText();
            matr[1][3] = defining_left_4.getText();
            matr[1][4] = defining_left_5.getText();
            matr[1][5] = defining_left_6.getText();

            matr[2][0] = defining_right_1.getText();
            matr[2][1] = defining_right_2.getText();
            matr[2][2] = defining_right_3.getText();
            matr[2][3] = defining_right_4.getText();
            matr[2][4] = defining_right_5.getText();
            matr[2][5] = defining_right_6.getText();
            Runner(matr);
        }

        int ang1 = -1, ang2 = -1, ang3 = -1, ang4 = -1, ang5 = -1, ang6 = -1;
        int mult1 = -1, mult2 = -1, mult3 = -1, mult4 = -1, mult5 = -1, mult6 = -1;
        int[][] secmat = new int[100][2];
        JFrame frame = new JFrame("Граф");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        public void Runner(String[][] matrex) {
            int kx = 100, ky = 300;
            String[][] matr = matrex;
            for (int i = 0; i < 3; i++) {
                for (int l = 0; l < 6; l++) {
                    matr[i][l] = " ";
                }
            }
            matr = matrex;


            for (int i = 1; i < 100; i++) {
                for (int k = 0; k < 2; k++) {
                    secmat[i][k] = -10000;
                }
            }
            secmat[0][0] = 100;
            secmat[0][1] = 300;

            char[] matr_form = new char[6];

            for (int i = 0; i < 6; i++) {
                matr_form[i] = ' ';
                if (matr[0][i].charAt(0) != ' ') {
                    matr_form[i] = matr[0][i].charAt(0);
                } else {
                    break;
                }
            }

            int[] matr_form_num = new int[6];
            for (int i = 0; i < 6; i++) {
                matr_form_num[i] = -1;
                if ((matr[2][i]).charAt(0) == 'e' && matr[1][i].length() == 2) {
                    for (int k = 0; k < 6; k++) {
                        if (matr_form[k] == matr[1][i].charAt(0)) {
                            matr_form_num[k] = (int) matr[1][i].charAt(1) - 48;
                        }
                    }
                }
            }

            if (matr_form[0] != ' ' && matr_form_num[0] != -1) {
                mult1 = matr_form_num[0];
                ang1 = mult1;
                mult1 *= 100;
            }
            if (matr_form[1] != ' ' && matr_form_num[1] != -1) {
                mult2 = matr_form_num[1];
                ang2 = mult2;
                mult2 *= 100;
            }
            if (matr_form[2] != ' ' && matr_form_num[2] != -1) {
                mult3 = matr_form_num[2];
                ang3 = mult3;
                mult3 *= 100;
            }
            if (matr_form[3] != ' ' && matr_form_num[3] != -1) {
                mult4 = matr_form_num[3];
                ang4 = mult4;
                mult4 *= 100;
            }
            if (matr_form[4] != ' ' && matr_form_num[4] != -1) {
                mult5 = matr_form_num[4];
                ang5 = mult5;
                mult5 *= 100;
            }
            if (matr_form[5] != ' ' && matr_form_num[5] != -1) {
                mult6 = matr_form_num[5];
                ang6 = mult6;
                mult6 *= 100;
            }

            JOptionPane.showMessageDialog(null, "188", "yuy1", JOptionPane.PLAIN_MESSAGE);
            mult1 = 500;
            ang1 = 5;
            mult2 = 200;
            ang2 = 2;

            if (mult1 != -1) {
                JOptionPane.showMessageDialog(null, "188.1", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult1(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            } else if (mult2 != -1) {
                JOptionPane.showMessageDialog(null, "188.2", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult2(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            } else if (mult3 != -1) {
                JOptionPane.showMessageDialog(null, "188.3", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult3(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            } else if (mult4 != -1) {
                JOptionPane.showMessageDialog(null, "188.4", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult4(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            } else if (mult5 != -1) {
                JOptionPane.showMessageDialog(null, "188.5", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult5(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            } else if (mult6 != -1) {
                JOptionPane.showMessageDialog(null, "188.6", "yuy1", JOptionPane.PLAIN_MESSAGE);
                mult6(mult1,mult2,mult3,mult4,mult5,mult6, 100, 300);
            }
            JOptionPane.showMessageDialog(null, "208", "yuy1", JOptionPane.PLAIN_MESSAGE);

        }

        public void mult1(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult1", "yuy1", JOptionPane.PLAIN_MESSAGE);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang1) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.BLACK;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang1 - tml1 % ang1) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang1 - tml1 % ang1) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult1 -= 1;
            tml1-=1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;


            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 10 && Math.abs(secmat[j][1] - ky) < 10) {
                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;

                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }

        public void mult2(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult2", "yuy1", JOptionPane.PLAIN_MESSAGE);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang2) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.RED;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang2 - tml2 % ang2) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang2 - tml2 % ang2) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult2 -= 1;
            tml2-=1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;
            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 10 && Math.abs(secmat[j][1] - ky) < 10) {
                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;
                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }

        public void mult3(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult3", "yuy1", JOptionPane.PLAIN_MESSAGE);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang3) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.BLUE;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang3 - mult3 % ang3) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang3 - mult3 % ang3) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult3 -= 1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;
            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 5 && Math.abs(secmat[j][1] - ky) < 5) {

                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;
                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }

        public void mult4(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult4", "yuy1", JOptionPane.PLAIN_MESSAGE);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang4) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.GREEN;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang4 - mult4 % ang4) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang4 - mult4 % ang4) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult4 -= 1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;
            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 5 && Math.abs(secmat[j][1] - ky) < 5) {

                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;
                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }

        public void mult5(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult5", "yuy1", JOptionPane.PLAIN_MESSAGE);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang5) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.YELLOW;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang5 - mult5 % ang5) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang5 - mult5 % ang5) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult5 -= 1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;
            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 5 && Math.abs(secmat[j][1] - ky) < 5) {

                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;
                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }

        public void mult6(int tml1, int tml2, int tml3, int tml4, int tml5, int tml6, int kx, int ky) {
            JOptionPane.showMessageDialog(null, "mult6", "yuy1", JOptionPane.PLAIN_MESSAGE);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            double angle = (360 / ang6) * (Math.PI / 180);
            Drawing draw = new Drawing();
            draw.setBackground(Color.WHITE);
            draw.color = Color.MAGENTA;
            draw.x1 = kx;
            draw.x2 = (int) (kx + (100 * Math.sin((ang6 - mult6 % ang6) * angle)));
            draw.y1 = ky;
            draw.y2 = (int) (ky - (100 * Math.cos((ang6 - mult6 % ang6) * angle)));
            frame.add(draw);
            frame.setSize(600, 600);
            frame.setVisible(true);
            kx = draw.x2;
            ky = draw.y2;
            mult6 -= 1;
            int tmp1 = tml1;
            int tmp2 = tml2;
            int tmp3 = tml3;
            int tmp4 = tml4;
            int tmp5 = tml5;
            int tmp6 = tml6;
            for (int j = 0; j < 100; j++) {
                if (Math.abs(secmat[j][0] - kx) < 5 && Math.abs(secmat[j][1] - ky) < 5) {

                    break;
                } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                    (secmat)[j][0] = kx;
                    (secmat)[j][1] = ky;
                    if (mult1 != -1) mult1(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult2 != -1) mult2(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult3 != -1) mult3(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult4 != -1) mult4(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult5 != -1) mult5(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    if (mult6 != -1) mult6(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, kx, ky);
                    break;
                }
            }
        }



/*
        public void circ1() {
JFrame frame = new JFrame("Граф");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            double angle = (360 / mult) * (Math.PI / 180);
            for (int i = 0; i < 10000; i++) {
                Drawing draw = new Drawing();
                draw.setBackground(Color.WHITE);
                draw.x1 = kx;
                draw.x2 = (int) (kx + (100 * Math.sin(i * angle)));
                draw.y1 = ky;
                draw.y2 = (int) (ky - (100 * Math.cos(i * angle)));
                frame.add(draw);
                frame.setSize(600, 600);
                frame.setVisible(true);
                kx = draw.x2;
                ky = draw.y2;
                for (int j = 0; j < 100; j++) {
                    if (Math.abs(secmat[j][0] - kx) < mult && Math.abs(secmat[j][1] - ky) < mult) {
                        i = 10000;
                        break;
                    } else if ((secmat)[j][0] == -10000 && (secmat)[j][1] == -10000) {
                        (secmat)[j][0] = kx;
                        (secmat)[j][1] = ky;
                        break;
                    }
                }
            }

        }
*/
    }

}
