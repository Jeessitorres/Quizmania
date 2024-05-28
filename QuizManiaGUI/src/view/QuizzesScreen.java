package view;

import controller.Controller;
import model.FlashcardSet;
import model.Quiz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class QuizzesScreen extends JFrame {
    private JPanel pnlMain = new JPanel();
    private JButton btnBack = new JButton();
    private JButton btnAddNewQuiz = new JButton();
    private JLabel lblTitle = new JLabel();
    private JButton btnOpen = new JButton();
    private JButton btnPlay = new JButton();
    private Controller controller;
    private JList quizList = new JList();

    public QuizzesScreen(Controller controller) {
        this.controller = controller;
        this.setTitle("Quizmania");
        SpringLayout springLayout = new SpringLayout();
        this.setContentPane(pnlMain);
        pnlMain.setLayout(springLayout);


        btnBack.setText("Back");
        pnlMain.add(btnBack);
        btnBack.addActionListener(e -> onBackBtnClick());
        springLayout.putConstraint(SpringLayout.WEST, btnBack, 20, SpringLayout.WEST, pnlMain);
        springLayout.putConstraint(SpringLayout.NORTH, btnBack, 20, SpringLayout.NORTH, pnlMain);

        lblTitle.setText("Quizzes");
        pnlMain.add(lblTitle);
        springLayout.putConstraint(SpringLayout.WEST, lblTitle, 20, SpringLayout.EAST, btnBack);
        springLayout.putConstraint(SpringLayout.EAST, lblTitle, -20, SpringLayout.WEST, btnAddNewQuiz);
        springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 0, SpringLayout.NORTH, btnBack);
        springLayout.putConstraint(SpringLayout.SOUTH, lblTitle, 0, SpringLayout.SOUTH, btnBack);

        btnAddNewQuiz.setText("+");
        pnlMain.add(btnAddNewQuiz);
        springLayout.putConstraint(SpringLayout.EAST, btnAddNewQuiz, -20, SpringLayout.EAST, pnlMain);
        springLayout.putConstraint(SpringLayout.NORTH, btnAddNewQuiz, 0, SpringLayout.NORTH, btnBack);
        btnAddNewQuiz.addActionListener(e -> onBtnAddNewQuizClick());

        pnlMain.add(quizList);
        springLayout.putConstraint(SpringLayout.WEST, quizList, 20, SpringLayout.WEST, pnlMain);
        springLayout.putConstraint(SpringLayout.EAST, quizList, -20, SpringLayout.EAST, pnlMain);
        springLayout.putConstraint(SpringLayout.NORTH, quizList, 20, SpringLayout.SOUTH, btnBack);
        springLayout.putConstraint(SpringLayout.SOUTH, quizList, -60, SpringLayout.SOUTH, pnlMain);

        btnOpen.setText("Open");
        pnlMain.add(btnOpen);
        springLayout.putConstraint(SpringLayout.EAST, btnOpen, 0, SpringLayout.EAST, quizList);
        springLayout.putConstraint(SpringLayout.SOUTH, btnOpen, -20, SpringLayout.SOUTH, pnlMain);
        btnOpen.addActionListener(e -> onBtnOpenClick());
        btnOpen.setVisible(true);

        btnPlay.setText("Play");
        pnlMain.add(btnPlay);
        springLayout.putConstraint(SpringLayout.WEST, btnPlay, 0, SpringLayout.WEST, quizList);
        springLayout.putConstraint(SpringLayout.SOUTH, btnPlay, -20, SpringLayout.SOUTH, pnlMain);
        btnPlay.addActionListener(e -> onBtnPlayClick());
        btnPlay.setVisible(true);

        this.pack();
        this.setVisible(true);
        this.setSize(new Dimension(400, 400));
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void onBtnAddNewQuizClick () {
        controller.handleAddNewQuiz();
    }

    private void onBackBtnClick () {
        controller.handleBackToMainScreen();
    }

    private void onBtnOpenClick () {
        controller.openSelectedQuiz();
    }

    private void onBtnPlayClick() {
        controller.onPlayButtonClick();
    }

    public Quiz getSelectedQuiz() {
        Quiz quiz = (Quiz) quizList.getSelectedValue();
        return quiz;
    }

    public JList getQuizList() { //Returns JList component for displaying quizzes
        return quizList;
    }

    public void displayQuizzesList (List < Quiz > quiz) {
       List<String> quizzes = new ArrayList<>();
        for (Quiz quiz1 : quiz) {
            quizzes.add(quiz1.getTitle());
        }
        quizList.setListData(quizzes.toArray());
        if (!quiz.isEmpty()) {
            quizList.setSelectedIndex(0);
        }
    }
}
