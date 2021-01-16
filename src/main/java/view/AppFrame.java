package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

/**
 * @author ET
 * @version 04/11/2020 16:30
 */
public class AppFrame extends JFrame {
    private int width = 1000;
    private int height = 600;
    private view.ErrorPanel errorPanel;

    public AppFrame(Model model) {
        super("Temperature Recording");
        setDefaultBehaviour();
        initFields(model);
        initFields(model);
        this.setVisible(true);
    }

    private void setDefaultBehaviour() {
        getContentPane().setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setMinimumSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(screenSize.width - 50, screenSize.height - 50));
        if (height > screenSize.height) {
            height = screenSize.height;
        }
        if (width > screenSize.width) {
            width = screenSize.width;
        }
        this.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        this.setSize(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
    }

    private void initFields(Model model) {
        Container pane = this.getContentPane();
        final PeoplePane comp = new PeoplePane(model);
        pane.add(comp);
        comp.init();

        errorPanel = new view.ErrorPanel(width - 10, view.ErrorPanel.MIN_HEIGHT, model.getErrors());
        errorPanel.setLocation(5, height - view.ErrorPanel.MIN_HEIGHT - 25);
        errorPanel.setFocusable(false);
        this.add(errorPanel);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        if (this.getHeight() != height || this.getWidth() != width) {
            height = this.getHeight();
            width = this.getWidth();
            final int errorPanelY = errorPanel.getY();
            errorPanel.setBounds(errorPanel.getX(), errorPanelY, width - 10,
                    this.getHeight() - errorPanelY - 29);
        }
    }
}
