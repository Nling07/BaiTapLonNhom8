import javax.swing.*;
import java.awt.event.*;
class Pen {
    String name;
    double price;
    boolean sold;

    public Pen(String name, double price) {
        this.name = name;
        this.price = price;
        this.sold = false;
    }
}
class User {
    String name;
    double balance;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
}

class PenMarket {

    Pen pen;

    public void listPen(String name, double price) {
        pen = new Pen(name, price);
    }

    public boolean buyPen(User user) {

        if (pen == null || pen.sold)
            return false;

        if (user.balance >= pen.price) {
            user.balance -= pen.price;
            pen.sold = true;
            return true;
        }

        return false;
    }
}

public class Main {
    static PenMarket market = new PenMarket();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Pen Market");

        frame.setLayout(null);

        JLabel penNameLabel = new JLabel("Pen name:");
        penNameLabel.setBounds(20, 20, 100, 30);
        frame.add(penNameLabel);

        JTextField penNameField = new JTextField();
        penNameField.setBounds(120, 20, 150, 30);
        frame.add(penNameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 60, 100, 30);
        frame.add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(120, 60, 150, 30);
        frame.add(priceField);

        JButton listButton = new JButton("Seller: List Pen");
        listButton.setBounds(20, 100, 250, 30);
        frame.add(listButton);

        JLabel buyerLabel = new JLabel("Buyer name:");
        buyerLabel.setBounds(20, 150, 100, 30);
        frame.add(buyerLabel);

        JTextField buyerField = new JTextField();
        buyerField.setBounds(120, 150, 150, 30);
        frame.add(buyerField);

        JButton buyButton = new JButton("Buy Pen");
        buyButton.setBounds(20, 190, 250, 30);
        frame.add(buyButton);

        JLabel status = new JLabel("Status: No pen listed");
        status.setBounds(20, 240, 300, 30);
        frame.add(status);

        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = penNameField.getText();
                    double price = Double.parseDouble(priceField.getText());

                    market.listPen(name, price);

                    status.setText("Pen listed: " + name + " - $" + price);

                } catch (Exception ex) {
                    status.setText("Invalid price input");
                }
            }
        });
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String buyerName = buyerField.getText();

                User buyer = new User(buyerName, 100);

                boolean success = market.buyPen(buyer);

                if (success) {
                    status.setText(buyerName + " bought the pen!");
                } else {
                    status.setText("Purchase failed");
                }
            }
        });

        frame.setSize(320, 340);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}