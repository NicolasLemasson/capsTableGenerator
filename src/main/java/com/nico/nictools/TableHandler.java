package com.nico.nictools;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nico.nictools.ScrollImageTest.TABLE_SIZE;

/**
 * Created by nico on 04/02/18.
 */
public class TableHandler {

    private final JPanel canvas;
    Image background;
    List<Capsule> capsules;
    ArrayList<PosCaps> posCaps = new ArrayList<>();
    Image currentCaps;
    File saveFile = new File("/home/nico/IdeaProjects/TableGenerator/save.json");

    String capsSelectionTouch = "azertyuiopqsdfghjklmwxcvbn";
    private int currentCapsIndex = 0;
    public List<PosCaps> allPos = new ArrayList<>();

    public TableHandler(JPanel canvas) throws IOException {
        this.canvas = canvas;
        capsules = new ArrayList<>();
        int canevaHeight = (TABLE_SIZE) / 8;
        int canevasWidth = canevaHeight + 300;
        background = new BufferedImage(canevasWidth, canevaHeight, BufferedImage.TYPE_INT_RGB);
//        background.getGraphics().setColor(new Color(0, 255, 0));
        Graphics g = background.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canevasWidth, canevaHeight);
//        g.fillOval(0, 0, canevaHeight, canevaHeight);
        g.setColor(new Color(175, 150, 0));
        int targetX = 0;
        int targetY = 0;
        g.fillOval(targetX, targetY, canevaHeight, canevaHeight);
        g.setColor(Color.BLACK);
        g.drawOval(targetX, targetY, canevaHeight, canevaHeight);

        g.setColor(new Color(100, 100, 100));
        g.fillRect(950, 0, 250, 700);
//        background.getGraphics().setColor(Color.YELLOW);
        canvas.revalidate();
        canvas.repaint();

//        background = ImageIO.read( Thread.currentThread().getContextClassLoader().getResource( "backg/background.png" ) );
//         = image.getScaledInstance( image.getWidth() / 8, image.getHeight() / 8, Image.SCALE_SMOOTH );
//        background.getGraphics().setColor(Color.black);

    }

    public void loadCaps(String directory) throws IOException {
        File f = new File(directory);
        for (File capsFile : f.listFiles()) {
            if (capsFile.getName().contains("png"))
                addCaps(capsFile);
        }
        currentCaps = capsules.get(0).image;
    }

    public void addCaps(File filePath) throws IOException {
        BufferedImage caps = ImageIO.read(filePath);
        capsules.add(new Capsule(
                        caps.getScaledInstance(caps.getWidth() / 8, caps.getHeight() / 8, Image.SCALE_SMOOTH),
                        filePath.getName().split("\\.")[0]
                )
        );
    }


    public Image getImage() {
        return background;
    }

    public void clickAt(int xClick, int yClick) {
        xClick = xClick - 16;
        yClick = yClick - 16;
        int xB, yB;
        int posCapsMinDist = 0;
        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < posCaps.size(); i++) {
            PosCaps pos = posCaps.get(i);
            xB = pos.x;// / 8;
            yB = pos.y;// / 8;
            double dist = Math.sqrt(Math.pow(new Double(xB - xClick), 2.0) + Math.pow(new Double(yB - yClick), 2.0));
            if (dist < minDist) {
                minDist = dist;
//                System.out.println( minDist );
                posCapsMinDist = i;
            }
        }
        if (minDist > 30) {

        }
        changeCaps(posCapsMinDist);

    }

    public void changeCaps(int posIndex) {
        PosCaps pos = this.posCaps.get(posIndex);
        posCaps.get(posIndex).index = currentCapsIndex;
        background.getGraphics().drawImage(currentCaps, pos.x, pos.y, null);
        canvas.revalidate();
        canvas.repaint();
//        canvas.repaint( new Rectangle( pos.x - 320, pos.y + 320 ) );
    }

    public void printCaps() {
        for (PosCaps pos : posCaps) {
            background.getGraphics().drawImage(capsules.get(pos.index).image, pos.x, pos.y, null);
        }
        canvas.revalidate();
        canvas.repaint();
    }

    public void keyPressed(KeyEvent keyEvent) {
        int selected = capsSelectionTouch.indexOf(keyEvent.getKeyChar());
        if (selected >= 0 && selected < capsules.size()) {
            this.currentCaps = capsules.get(selected).image;
            currentCapsIndex = selected;
            printCurrentCaps();
        } else {
            switch (keyEvent.getKeyChar()) {
                case 'U':
                    printUsage();
                    break;
                case 'S':
                    saveCurrentTable();
                    break;
                case 'L':
                    loadTable();
                    break;
                case 'C':
                    System.out.println("Clearing tab");
                    posCaps.clear();
                    try {
                        background = ImageIO.read(new File("/home/nico/IdeaProjects/TableGenerator/background.png"));
                        canvas.revalidate();
                        canvas.repaint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 'R':
                    System.out.println("Randomizing !");
                    randomizeThisShit();
                    break;
            }

        }

    }

    private void randomizeThisShit() {
        boolean isMotherFuckingHere = false;
        for (PosCaps all : allPos) {
            for (PosCaps present : posCaps) {
                if (all.getX() == present.getX() && all.getY() == present.getY()) {
                    isMotherFuckingHere = true;
                    break;
                }
            }
            if (!isMotherFuckingHere) {
                int nombreAleatoire = (int) (Math.random() * ((capsules.size())));
                if (nombreAleatoire == 3 ||
                        nombreAleatoire == 1 ||
                        nombreAleatoire == 11 ||
                        nombreAleatoire == 16
                )
                    nombreAleatoire--;
                System.out.println("Adding caps " + nombreAleatoire);
                posCaps.add(new PosCaps(all.getX(), all.getY(), nombreAleatoire));
            }
            isMotherFuckingHere = false;
        }
        printCaps();
        canvas.revalidate();
        canvas.repaint();
    }


    private void loadTable() {
        System.out.println("Loading");
        ObjectMapper mapper = new ObjectMapper();
        try {
            CapsList capsList = mapper.readValue(saveFile, CapsList.class);
            this.posCaps.clear();
            this.posCaps.addAll(capsList.posCaps);
            printCaps();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCurrentTable() {
        System.out.println("Saving..");
        ObjectMapper mapper = new ObjectMapper();
        try {
            CapsList value = new CapsList();
            for (PosCaps p : posCaps) {
                if (p.index != 0)
                    value.posCaps.add(p);
            }
//            value.posCaps.addAll( posCaps );
            saveFile.delete();
            mapper.writeValue(saveFile, value);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printCurrentCaps() {
        Graphics graphics = background.getGraphics();
        graphics.setColor(new Color(0,0,0));
        graphics.drawString("Capsule sélectionnée:", 800, 45);

        graphics.drawImage(currentCaps, 850, 50, null);
        canvas.revalidate();
        canvas.repaint();
    }


    public void printAvailableCaps() {
        int x, y;
        int Xdepart = 1000;
        x = Xdepart;
        y = 100;
        Graphics graphics = background.getGraphics();

        for (int i = 0; i < capsules.size(); i++) {
            Capsule cap = capsules.get(i);
            Image image = cap.image;
            graphics.drawImage(image, x, y, null);
            graphics.drawString(capsSelectionTouch.charAt(i) + ": ", x - 25, y + 20);
            if (x == Xdepart)
                x += 100;
            else if (x == (Xdepart + 100)) {
                y += 50;
                x -= 100;
            }
        }
        canvas.revalidate();
        canvas.repaint();
    }


    void printUsage() {
        Map<String, Integer> usage = new HashMap<>();
        System.out.println("----------------------");
        for (PosCaps pos : posCaps) {
            String name = capsules.get(pos.index).name;
            usage.putIfAbsent(name, 0);
            usage.replace(name, usage.get(name) + 1);
        }

        for (Map.Entry<String, Integer> entry : usage.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
