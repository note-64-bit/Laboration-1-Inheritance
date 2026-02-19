
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {


    ArrayList<Car> cars;
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;

    // To keep track of a single car's position
    ArrayList<Point> carPoints = new ArrayList<>();
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(0, 465);

    void moveit(int index, int x, int y) {

        while (carPoints.size() <= index) {
            carPoints.add(new Point());
        }

        carPoints.get(index).x = x;
        carPoints.get(index).y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Car> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/VolvoBrand.jpg"));
            saabImage  = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("/pics/Scania.jpg"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < cars.size(); i++) {

            if (i >= carPoints.size()) continue;

            Point p = carPoints.get(i);
            Car car = cars.get(i);

            if (car instanceof Volvo240) {
                g.drawImage(volvoImage, p.x, p.y, null);
            }
            else if (car instanceof Saab95) {
                g.drawImage(saabImage, p.x, p.y, null);
            }
            else if (car instanceof Scania) {
                g.drawImage(scaniaImage, p.x, p.y, null);
            }
        }

        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }


}