package jhelp;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import jhelp.orm.Term;
import jhelp.repos.DefinitionRepository;
import jhelp.repos.TermRepository;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GUIFrame extends JFrame {
    private JPanel contentPane;
    private JTextField textField1;
    private JButton findButton;
    private JButton editButton;
    private JButton nextButton;
    private JButton previousButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton exitButton;
    private JMenuBar menuBar1;
    private JMenu File;
    private JMenu Edit;
    private JMenu Settings;
    private JMenu Help;
    private JTextPane textPane1;
    private JButton clearFormButton;

    private TermRepository termRepository;
    private DefinitionRepository definitionRepository;
    private DataSource dataSource;

    private static final String NOT_FOUND = "Definition not found";
    private String currentTerm;
    private byte currentDefinitionIndex = -1;
    private List<String> foundDefinitions = new ArrayList<>();

    public void setTermRepository(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    public void setDefinitionRepository(DefinitionRepository definitionRepository) {
        this.definitionRepository = definitionRepository;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GUIFrame() {
        super("JHelp");
        $$$setupUI$$$();
        setContentPane(contentPane);
//        pack();
//        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        exitButton.addActionListener(e -> dispose());

        findButton.addActionListener(e -> {
            editButton.setEnabled(false);
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            deleteButton.setEnabled(false);
            String s = textField1.getText().trim();
            Optional <Term> found = termRepository.findByTerm(s);
            if (found.isPresent()) {
                editButton.setEnabled(true);
                clearFormButton.setEnabled(true);
                foundDefinitions.clear();
                found.get().getDefinitions().forEach(d -> foundDefinitions.add(d.getDefinition()));
                if(foundDefinitions.size() > 1){
                    nextButton.setEnabled(true);
                }
                textPane1.setText(foundDefinitions.get(0));
            }else textPane1.setText(NOT_FOUND);
        });

        clearFormButton.addActionListener(e -> {
            textPane1.setText("");
            textField1.setText("");
            editButton.setEnabled(false);
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            deleteButton.setEnabled(false);
            addButton.setEnabled(false);
            findButton.setEnabled(false);
            clearFormButton.setEnabled(false);
        });

        textField1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                addButton.setEnabled(true);
                findButton.setEnabled(true);
                clearFormButton.setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField1.getText().isEmpty()) {
                    addButton.setEnabled(false);
                    findButton.setEnabled(false);
                    clearFormButton.setEnabled(false);
                }
            }
        });

        nextButton.addActionListener(e -> {
            int i = foundDefinitions.indexOf(textPane1.getText());
            if (i != foundDefinitions.size() - 1) {
                textPane1.setText(foundDefinitions.get(++i));
            }
            if (i == 1) {
                previousButton.setEnabled(true);
            }
            if (i == foundDefinitions.size() - 1) {
                nextButton.setEnabled(false);
            }
        });

        previousButton.addActionListener(e -> {
            int i = foundDefinitions.indexOf(textPane1.getText());
            if (i != 0) {
                textPane1.setText(foundDefinitions.get(--i));
            }
            if (i == foundDefinitions.size() - 2) {
                nextButton.setEnabled(true);
            }
            if (i == 0) {
                previousButton.setEnabled(false);
            }
        });
    }

    public void initWindow() {
        editButton.setEnabled(false);
        nextButton.setEnabled(false);
        previousButton.setEnabled(false);
        deleteButton.setEnabled(false);
        addButton.setEnabled(false);
        findButton.setEnabled(false);
        clearFormButton.setEnabled(false);
    }

    public static void main(String[] args) {
        new GUIFrame();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setMinimumSize(new Dimension(-1, -1));
        panel1.setName("");
        panel1.setPreferredSize(new Dimension(-1, -1));
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(contentPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        contentPane.setBorder(BorderFactory.createTitledBorder(""));
        menuBar1 = new JMenuBar();
        menuBar1.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(menuBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        Edit = new JMenu();
        Edit.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Edit.setLabel("Edit");
        Edit.setName("Edit");
        Edit.setText("Edit");
        menuBar1.add(Edit, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        File = new JMenu();
        File.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        File.setLabel("File");
        File.setName("File");
        menuBar1.add(File, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        menuBar1.add(spacer1, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, new Dimension(-1, 30), 0, false));
        Settings = new JMenu();
        Settings.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Settings.setLabel("Settings");
        Settings.setName("Settings");
        menuBar1.add(Settings, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Help = new JMenu();
        Help.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        Help.setLabel("Help");
        Help.setName("Help");
        menuBar1.add(Help, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JTabbedPane tabbedPane1 = new JTabbedPane();
        contentPane.add(tabbedPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(11, 2, new Insets(0, 0, 0, 20), -1, -1));
        panel2.setMinimumSize(new Dimension(-1, -1));
        panel2.setPreferredSize(new Dimension(800, 500));
        tabbedPane1.addTab("Main", panel2);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-8091351));
        panel3.setForeground(new Color(-4490888));
        panel2.add(panel3, new GridConstraints(1, 0, 10, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textField1 = new JTextField();
        panel2.add(textField1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 30), new Dimension(-1, 30), new Dimension(-1, 30), 0, false));
        findButton = new JButton();
        findButton.setText("Find");
        panel2.add(findButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        panel2.add(editButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(105, 30), null, 0, false));
        nextButton = new JButton();
        nextButton.setText("Next");
        panel2.add(nextButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(105, 30), null, 0, false));
        previousButton = new JButton();
        previousButton.setText("Previous");
        panel2.add(previousButton, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel2.add(spacer2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        panel2.add(addButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(105, 30), null, 0, false));
        deleteButton = new JButton();
        deleteButton.setText("Delete");
        panel2.add(deleteButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel2.add(spacer3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel2.add(spacer4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setText("Exit");
        panel2.add(exitButton, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel2.add(spacer5, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setMinimumSize(new Dimension(-1, -1));
        panel4.setPreferredSize(new Dimension(-1, -1));
        tabbedPane1.addTab("Settings", panel4);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setMinimumSize(new Dimension(-1, -1));
        panel5.setPreferredSize(new Dimension(-1, -1));
        panel5.setRequestFocusEnabled(true);
        tabbedPane1.addTab("Help", panel5);
    }
}
