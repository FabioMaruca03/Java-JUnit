package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.GregorianCalendar;

@SuppressWarnings("MagicConstant")
public class PeoplePane extends JPanel {
    private final Model model;

    public PeoplePane(Model model) {
        this.model = model;
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void init() {
        setBounds(getParent().getBounds().width/2, 0, 600, 300);

        final GridBagConstraints gpc = new GridBagConstraints();
        final JTextField fName = new JTextField();
        final JTextField lName = new JTextField();
        final JTextField date = new JTextField("dd/MM/yyyy");
        final JTextField addressDetail = new JTextField();
        final JTextField postcode = new JTextField();
        final JButton add = new JButton("Add");

        gpc.weightx = 1;

        gpc.gridx = 0; gpc.gridy = 0; gpc.fill = GridBagConstraints.CENTER;
        add(new JLabel("Person Data"), gpc);

        gpc.gridy = 1; gpc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("First name: "), gpc);
        gpc.gridx = 1;
        add(fName, gpc);
        gpc.gridx = 3;
        add(new JLabel("last name: "), gpc);
        gpc.gridx = 4;
        add(lName, gpc);

        gpc.gridx = 0; gpc.gridy = 2;
        add(new JLabel("Birth date: "), gpc);
        gpc.gridx = 1;
        add(date, gpc);

        gpc.gridx = 0; gpc.gridy = 3;
        add(new JLabel("Address detail: "), gpc);
        gpc.gridx = 1;

        add(addressDetail, gpc);
        gpc.gridx = 0; gpc.gridy = 4;
        add(new JLabel("Postcode: "), gpc);
        gpc.gridx = 1;
        add(postcode, gpc);
        gpc.gridx = 4; gpc.gridy = 5;
        add(add, gpc);

        add.addActionListener(e -> {
            try {
                final int[] fragments = Arrays.stream(date.getText().split("/")).mapToInt(Integer::parseInt).toArray();
                model.addPerson(fName.getText(), lName.getText(), new GregorianCalendar(fragments[2], fragments[1], fragments[0]), postcode.getText(), addressDetail.getText());
            } catch (NumberFormatException ignore) {
                model.getErrors().addElement("Unable to parse date: ".concat(date.getText()));
            }
        });

    }
}
