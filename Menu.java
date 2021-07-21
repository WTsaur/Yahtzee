package Yahtzee;

import java.util.Vector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Menu extends Panel {

    private GridBagLayout gbl;
    public JTextField TxtEntry;
    public JLabel menuLabel;
    public JButton startButton;
    public JButton addButton;
    private DefaultListModel<String> listModel;
    private JList<String> PlayerList;

    public Menu(String bgImgPath) {
        super(bgImgPath);
        gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints gbc = new GridBagConstraints();

        menuLabel = new JLabel("Players");
        menuLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(menuLabel, gbc);

        listModel = new DefaultListModel<>();

        PlayerList = new JList<>(listModel);
        PlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PlayerList.setLayoutOrientation(JList.VERTICAL);
        PlayerList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = PlayerList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        listModel.remove(selectedIndex);
                    }
                    PlayerList.setSelectedIndex(-1);
                }  
            }
        });

        JScrollPane ListScroller = new JScrollPane(PlayerList);
        ListScroller.setPreferredSize(new Dimension(250, 80));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(ListScroller, gbc);

        TxtEntry = new JTextField(15);
        TxtEntry.addKeyListener(new KeyListener() {
            private boolean enterKeyPressed = false;

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addButton.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enterKeyPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (enterKeyPressed) {
                    enterKeyPressed = false;
                    addButton.doClick();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(TxtEntry, gbc);
        
        addButton = new JButton("Add Player");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = TxtEntry.getText().trim();
                if (name.length() > 0) {
                    if (listModel.contains(name)) {
                        displayErrorMessage(Constants.PLAYER_NAME_ERROR_MESSAGE);
                    } else {
                        listModel.addElement(name);
                    }
                }
                TxtEntry.setText("");
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(addButton, gbc);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listModel.size() > 1 && listModel.size() < 21) {
                    Vector<Player> data = new Vector<Player>();
                    for(Object x : listModel.toArray()) {
                        data.add(new Player((String) x));
                    }
                    Yahtzee.StartGame(data);
                } else {
                    displayErrorMessage(Constants.MENU_ERROR_MESSAGE);
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(startButton, gbc);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void displayErrorMessage(String msg) {
        TxtEntry.setText("");
        JOptionPane.showMessageDialog(null, msg, "", JOptionPane.INFORMATION_MESSAGE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
