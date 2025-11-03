import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ButtonMessage extends JFrame {
    private final java.util.List<String> questions = new java.util.ArrayList<>();
    private final java.util.List<String[]> options = new java.util.ArrayList<>();
    private final java.util.List<Integer> answers = new java.util.ArrayList<>();
    private int currentQ = 0, score = 0;
    private JLabel questionLabel, progressLabel;
    private JProgressBar progressBar;
    private JButton[] optionButtons = new JButton[4];
    private java.util.List<String> userAnswers = new java.util.ArrayList<>();
    private CardLayout cardLayout;
    private JPanel loginPanel, signupPanel, homePanel, welcomePanel, quizPanel;
    private final Map<String, String> users = new HashMap<>();

    public ButtonMessage() {
        setTitle("Smart Quiz");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        getContentPane().setLayout(cardLayout);
        initializeQuestions();
        initLoginPanel();
        initSignupPanel();
        initHomePanel();
        initWelcomePanel();
        initQuizPanel();
        getContentPane().add(loginPanel, "Login");
        getContentPane().add(signupPanel, "Signup");
        getContentPane().add(homePanel, "Home");
        getContentPane().add(welcomePanel, "Welcome");
        getContentPane().add(quizPanel, "Quiz");
        cardLayout.show(getContentPane(), "Login");
        setVisible(true);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(232, 245, 233));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel box = new JPanel(new BorderLayout(15, 15));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(56, 142, 60), 2, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        box.setPreferredSize(new Dimension(350, 250));
        JLabel title = new JLabel("Smart Quiz Login", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(new Color(56, 142, 60));
        box.add(title, BorderLayout.NORTH);
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(Color.WHITE);
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        form.add(new JLabel("Username:"));
        form.add(usernameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);
        box.add(form, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        JButton loginBtn = new JButton("Login");
        JButton signupBtn = new JButton("Signup");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        signupBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBackground(new Color(56, 142, 60));
        loginBtn.setForeground(Color.WHITE);
        signupBtn.setBackground(new Color(25, 118, 210));
        signupBtn.setForeground(Color.WHITE);
        buttons.add(loginBtn);
        buttons.add(signupBtn);
        box.add(buttons, BorderLayout.SOUTH);
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(box, gbc);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter username and password!");
            } else if (users.containsKey(user) && users.get(user).equals(pass)) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome " + user);
                cardLayout.show(getContentPane(), "Home");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        });
        signupBtn.addActionListener(e -> cardLayout.show(getContentPane(), "Signup"));
    }

    private void initSignupPanel() {
        signupPanel = new JPanel(new GridBagLayout());
        signupPanel.setBackground(new Color(227, 242, 253));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel box = new JPanel(new BorderLayout(15, 15));
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(30, 136, 229), 2, true),
            BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));
        box.setPreferredSize(new Dimension(380, 300));
        JLabel title = new JLabel("Create New Account", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setForeground(new Color(30, 136, 229));
        box.add(title, BorderLayout.NORTH);
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(Color.WHITE);
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmField = new JPasswordField();
        form.add(new JLabel("Username:"));
        form.add(usernameField);
        form.add(new JLabel("Password:"));
        form.add(passwordField);
        form.add(new JLabel("Confirm Password:"));
        form.add(confirmField);
        box.add(form, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.WHITE);
        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back to Login");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBackground(new Color(30, 136, 229));
        registerBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(244, 67, 54));
        backBtn.setForeground(Color.WHITE);
        buttons.add(registerBtn);
        buttons.add(backBtn);
        box.add(buttons, BorderLayout.SOUTH);
        gbc.gridx = 0;
        gbc.gridy = 0;
        signupPanel.add(box, gbc);
        registerBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            String pass = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
            } else if (!pass.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
            } else if (users.containsKey(user)) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
            } else {
                users.put(user, pass);
                JOptionPane.showMessageDialog(this, "Signup successful! You can now log in.");
                cardLayout.show(getContentPane(), "Login");
            }
        });
        backBtn.addActionListener(e -> cardLayout.show(getContentPane(), "Login"));
    }

    private void initHomePanel() {
        homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(new Color(232, 245, 233));
        JLabel title = new JLabel("Welcome to Smart Quiz", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        title.setForeground(new Color(56, 142, 60));
        homePanel.add(title, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        buttons.setBackground(new Color(232, 245, 233));
        JButton startBtn = new JButton("Start Quiz"), logoutBtn = new JButton("Logout"), exitBtn = new JButton("Exit");
        startBtn.setFont(new Font("Arial", Font.BOLD, 18));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 18));
        exitBtn.setFont(new Font("Arial", Font.BOLD, 18));
        startBtn.setBackground(new Color(56, 142, 60));
        logoutBtn.setBackground(new Color(30, 136, 229));
        exitBtn.setBackground(new Color(198, 40, 40));
        startBtn.setForeground(Color.WHITE);
        logoutBtn.setForeground(Color.WHITE);
        exitBtn.setForeground(Color.WHITE);
        buttons.add(startBtn); buttons.add(logoutBtn); buttons.add(exitBtn);
        homePanel.add(buttons, BorderLayout.SOUTH);
        startBtn.addActionListener(e -> cardLayout.show(getContentPane(), "Welcome"));
        logoutBtn.addActionListener(e -> cardLayout.show(getContentPane(), "Login"));
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void initWelcomePanel() {
        welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(227, 242, 253));
        JLabel title = new JLabel("Ready to Start the Quiz?", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(new Color(30, 136, 229));
        welcomePanel.add(title, BorderLayout.CENTER);
        JButton continueBtn = new JButton("Continue");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 18));
        continueBtn.setBackground(new Color(30, 136, 229));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.addActionListener(e -> {
            currentQ = 0;
            score = 0;
            userAnswers.clear();
            loadQuestion();
            cardLayout.show(getContentPane(), "Quiz");
        });
        JPanel south = new JPanel();
        south.setBackground(new Color(227, 242, 253));
        south.add(continueBtn);
        welcomePanel.add(south, BorderLayout.SOUTH);
    }

    private void initQuizPanel() {
        quizPanel = new JPanel(new BorderLayout(12, 12));
        quizPanel.setBackground(new Color(245, 245, 255));
        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        questionLabel.setForeground(new Color(0, 70, 120));
        quizPanel.add(questionLabel, BorderLayout.NORTH);
        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setBackground(new Color(245, 245, 255));
        for (int i = 0; i < 4; i++) {
            final int idx = i;
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 16));
            optionButtons[i].setBackground(new Color(187, 222, 251));
            optionButtons[i].addActionListener(evt -> checkAnswer(idx));
            optionsPanel.add(optionButtons[i]);
        }
        quizPanel.add(optionsPanel, BorderLayout.CENTER);
        JPanel bottom = new JPanel(new BorderLayout(6, 6));
        bottom.setBackground(new Color(245, 245, 255));
        progressLabel = new JLabel("Question 1 of " + questions.size(), SwingConstants.LEFT);
        progressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        progressBar = new JProgressBar(0, questions.size());
        progressBar.setValue(0);
        progressBar.setForeground(new Color(76, 175, 80));
        bottom.add(progressLabel, BorderLayout.NORTH);
        bottom.add(progressBar, BorderLayout.SOUTH);
        quizPanel.add(bottom, BorderLayout.SOUTH);
    }

    private void initializeQuestions() {
        questions.add("What is 15 + 25?");
        options.add(new String[]{"35", "40", "45", "50"});
        answers.add(2);
        questions.add("Who is known as the Father of the Indian Constitution?");
        options.add(new String[]{"Mahatma Gandhi", "B. R. Ambedkar", "Jawaharlal Nehru", "Sardar Patel"});
        answers.add(2);
        questions.add("Which keyword is used to define a class in Java?");
        options.add(new String[]{"function", "def", "class", "struct"});
        answers.add(3);
        questions.add("Which planet is known as the Red Planet?");
        options.add(new String[]{"Earth", "Mars", "Jupiter", "Venus"});
        answers.add(2);
        questions.add("Who wrote 'Vande Mataram'?");
        options.add(new String[]{"Rabindranath Tagore", "Bankim Chandra Chatterjee", "Sarojini Naidu", "Subramania Bharati"});
        answers.add(2);
    }

    private void loadQuestion() {
        if (currentQ < questions.size()) {
            questionLabel.setText("<html><div style='text-align:center;'>" +
                (currentQ + 1) + ". " + questions.get(currentQ) + "</div></html>");
            String[] opts = options.get(currentQ);
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(opts[i]);
                optionButtons[i].setEnabled(true);
            }
            progressLabel.setText("Question " + (currentQ + 1) + " of " + questions.size());
            progressBar.setValue(currentQ);
        } else {
            showResults();
        }
    }

    private void checkAnswer(int choice) {
        String[] opts = options.get(currentQ);
        if (choice + 1 == answers.get(currentQ)) {
            score++;
            userAnswers.add("Q" + (currentQ + 1) + ": ✅ Correct (" + opts[choice] + ")");
        } else {
            String correct = opts[answers.get(currentQ) - 1];
            userAnswers.add("Q" + (currentQ + 1) + ": ❌ Wrong (Your: " + opts[choice] + ", Correct: " + correct + ")");
        }
        currentQ++;
        loadQuestion();
    }

    private void showResults() {
        StringBuilder sb = new StringBuilder();
        sb.append("🎯 Your score: ").append(score).append(" / ").append(questions.size()).append("\n\n");
        for (String s : userAnswers) sb.append(s).append("\n");
        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(600, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scroll, BorderLayout.CENTER);
        JPanel btns = new JPanel();
        JButton home = new JButton("Home"), playAgain = new JButton("Play Again"), exit = new JButton("Exit");
        btns.add(home); btns.add(playAgain); btns.add(exit);
        panel.add(btns, BorderLayout.SOUTH);
        JDialog dialog = new JDialog(this, "Final Result", true);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        home.addActionListener(e -> { dialog.dispose(); cardLayout.show(getContentPane(), "Home"); });
        playAgain.addActionListener(e -> { dialog.dispose(); currentQ = 0; score = 0; userAnswers.clear(); loadQuestion(); cardLayout.show(getContentPane(), "Quiz"); });
        exit.addActionListener(e -> System.exit(0));
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ButtonMessage::new);
    }
}
