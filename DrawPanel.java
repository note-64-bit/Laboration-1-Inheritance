import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;

public class DrawPanel extends JPanel {

    private List<Car> cars;

    private BufferedImage volvoImage;
    private BufferedImage saabImage;
    private BufferedImage scaniaImage;
    private BufferedImage volvoWorkshopImage;

    public DrawPanel(int x, int y, List<Car> cars) {
        this.cars = cars;
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(x, y));
        setBackground(Color.green);

        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rita verkstad
        Rectangle r = WorldConfig.VOLVO_WORKSHOP_BOUNDS;
        g.drawImage(volvoWorkshopImage, r.x, r.y, null);

        // Rita bilar
        for (Car car : cars) {
            int x = (int) Math.round(car.getX());
            int y = (int) Math.round(car.getY());

            BufferedImage img = null;
            if (car instanceof Volvo240) img = volvoImage;
            else if (car instanceof Saab95) img = saabImage;
            else if (car instanceof Scania) img = scaniaImage;

            if (img != null) g.drawImage(img, x, y, null);
        }
    }
}