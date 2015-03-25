import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

public class BSTPrint extends JFrame {
    private BSTPlayGround playGround;
    private int x, y;
    private BST bst;

    public BSTPrint(BST bst) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cnt = getContentPane();
        cnt.setLayout(new BorderLayout());

        this.bst = bst;

        playGround = new BSTPlayGround();
        BSTControlCenter controlCenter = new BSTControlCenter();

        cnt.add(playGround, BorderLayout.CENTER);
        cnt.add(controlCenter, BorderLayout.SOUTH);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent componentEvent) {
                super.componentMoved(componentEvent);
                x = getWidth() / 2;
                playGround.repaint();

            }
        });

        pack();
        setVisible(true);

    }

    class BSTPlayGround extends JPanel {
        private static final int SIZE = 30, X_SPACE = 1, Y_SPACE = 40;

        public BSTPlayGround() {
            setPreferredSize(new Dimension(800, 800));
            setSize(800, 800);
            setBorder(new TitledBorder(""));

            x = getWidth() / 2;
            y = 100;

        }

        private void paintSon(Graphics2D g2, Node n, int h, int x, int y, int xSon, int ySon, int sign) {
            g2.drawLine(x + sign * SIZE / 4, y + SIZE / 2, xSon - sign * SIZE / 4, ySon - SIZE / 2);
            paintNode(g2, n, h, xSon, ySon);

        }

        private void paintNode(Graphics2D g2, Node n, int h, int x, int y) {
            g2.draw(new Ellipse2D.Double(x - SIZE / 2, y - SIZE / 2, SIZE, SIZE));
            g2.drawString("" + n.key(), x - SIZE / 7, y + SIZE / 6);

            if (n.left() != null) paintSon(g2, n.left(), h - 1, x, y, x - X_SPACE * (int)Math.pow(2, h), y + Y_SPACE, -1);
            if (n.right() != null) paintSon(g2, n.right(), h - 1, x, y, x + X_SPACE * (int)Math.pow(2, h), y + Y_SPACE, 1);

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;

            if (bst != null && bst.root() != null) paintNode(g2, bst.root(), bst.maxHeight() + 1, x, y);

        }
    }

    class BSTControlCenter extends JPanel {
        private JButton add, delete, deleteAll, newBST, refresh;
        private JTextField addTxt, delTxt, newTxt;

        public BSTControlCenter() {
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBorder(new TitledBorder("BST Control Center"));

            add = new JButton("Add");
            delete = new JButton("Delete");
            deleteAll = new JButton("Delete All");
            newBST = new JButton("New");
            refresh = new JButton("Refresh");

            addTxt = new JTextField();
            delTxt = new JTextField();
            newTxt = new JTextField();

            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int n = Integer.parseInt(addTxt.getText());

                        if (bst == null) bst = new BST(n);
                        else bst.insert(n);

                        addTxt.setText("");
                        delete.setEnabled(true);
                        deleteAll.setEnabled(true);
                        playGround.repaint();

                    } catch (NumberFormatException e) {addTxt.setText("Invalid key");}
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int n = Integer.parseInt(delTxt.getText());
                        bst.delete(n);

                        delTxt.setText("");
                        if (bst.root() == null) delete.setEnabled(false);
                        playGround.repaint();

                    } catch (NumberFormatException e) {delTxt.setText("Invalid key");}
                }
            });

            deleteAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    bst = null;
                    delete.setEnabled(false);
                    deleteAll.setEnabled(false);
                    playGround.repaint();

                }
            });

            newBST.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int n = Integer.parseInt(newTxt.getText());
                        int[] A = new int[n];

                        for (int i = 0; i < A.length; i++) A[i] = (int)(100 * Math.random());
                        bst = new BST(A);

                        newTxt.setText("");
                        delete.setEnabled(true);
                        deleteAll.setEnabled(true);
                        playGround.repaint();

                    } catch (NumberFormatException e) {newTxt.setText("Invalid number");}
                }
            });

            refresh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    playGround.repaint();

                }
            });

            add(add);
            add(addTxt);
            add(delete);
            add(delTxt);
            add(deleteAll);
            add(newBST);
            add(newTxt);
            add(refresh);

        }
    }

    public static void main(String[] args) {
        int[] A = new int[10];
        for (int i = 0; i < A.length; i++) A[i] = (int)(100 * Math.random());

        BST bst = new BST(A);
        new BSTPrint(bst);

    }
}
