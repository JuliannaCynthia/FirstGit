package com.techelevator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
class TestCatGui {
    public static void main(String[] args) throws IOException {
        //Frame
        JFrame frame = new JFrame("**~Adoption Menu~**");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);

        //menubar
        JMenuBar miniDefinition = new JMenuBar();
        JMenu  menu = new JMenu("                                                                                                        Meet the Kitty-Cats!");
        miniDefinition.add(menu);
        JMenuItem menuItem = new JMenuItem("Press next to cycle through the menu, and and enter a number when prompted. Get ready to meet your new furry friend!");
        menu.add(menuItem);

        //bottom panel
        JPanel panel = new JPanel();
        JLabel label = new JLabel( "Pick your cat!");
        JButton lyLe = new JButton("Lyle!");
        JTextArea tf=new JTextArea();
        tf.setBounds(75,50, 425,150);
        lyLe.addActionListener(e -> tf.setText("        *********~ Lyle ~*********\nMeet Lyle! Lyle is a pure 'bread' loaf who just wants to be loved.\nHe is a standard Maine-Coon, who has a 'toe' up on the competition" + "\ndue to his funky polydactyly condition!" +
                "\nWeighing in at a whopping 19lbs, Lyle's crumb structure is certainly dense." +
                "\nWhile active and playful definitely describe this feline friend," +
                "\nwe ask that Lyle bakes in the oven all on his own as his dislike" +
                "\nfor other bread types is 'rising'!" +
                "\nIf Lyle suits your style, lets get together and make biscuits!"));
        frame.add(tf);
//////////////////////////////////////////////////////////////////
        JButton paintBrush = new JButton("Paint Brush!");
        JTextArea lf=new JTextArea();
        lf.setBounds(400,225, 375,200);
        paintBrush.addActionListener(e -> lf.setText("        *********~ Paint Brush ~*********\nMeet Paint Brush! Paint Brush is a multi-color kitty looking\nfor an artist to inspire. She is a rare calico cat with black mitts!\nLooks like someone spent too much time playing in the ink.\nPaint Brush, or PB for short, is also short. She's a munchkin,\nso this painted cat is certainly getting places.... just not high ones!\nPB would really prefer help getting her canvas done, so we ask\nthat PB not be the only brush in the bucket.\nLet us know if PB, is the muse, for YOU!"));
        frame.add(lf);

//////////////////////////////////////////////////////////////////
        JButton spaceMan = new JButton("SpaceMan!");
        JTextArea gf=new JTextArea();
        gf.setBounds(75,400, 425,200);
        spaceMan.addActionListener(e -> gf.setText("        *********~ SpaceMan ~*********\nAre you looking for a cat from a galaxy far, far away?\nIf yes, then SpaceMan is futuristic furry friend for you!\nA tuxedo cat by birth, SpaceMan is always dressed to impress,\nand certainly has no problem fooling those nasty intergalactic spies!\nSpaceMan is a little larger-than-life, and thus both his tummy\nand his food bill are of planetary proportions. We recommend\na strict freeze-dried diet for SpaceMan, because he's from space.\nSpace cats only eat astronaut food, duh!\nIf SpaceMan is the intergalactic co-cat for you, that's Out-Of-This-WORLD!"));
        frame.add(gf);
/////////////////////////////////////////////////////////////////
//        JButton clearScree= new JButton("Clear Screen!");
//        JTextArea cleScre = new JTextArea();
//        cleScre.setBounds(0,0, 790,690);
//        clearScree.addActionListener(e -> cleScre.setText(""));
//        frame.add(cleScre);
//
//        panel.add(clearScree);
        panel.add(label);
        panel.add(lyLe);
        panel.add(paintBrush);
        panel.add(spaceMan);

        //center panel
        JTextArea textpanel = new JTextArea();





        //Add stuff to frame
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, miniDefinition);
        frame.getContentPane().add(BorderLayout.CENTER, textpanel);
        frame.setVisible(true);






    }
}