
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 *
 * @author waterbucket
 */
public class NumVis {

    private final JFrame frame;
    private final int[] digits;
    final static int WIDTH = 500;
    final static int HEIGHT = 500;
    final static int MAX_FROM_FILE = 200;

    public NumVis(String input) {
        this.digits = this.isFilePath(input) ? this.initialiseFromFile(new File(input.substring(5))) : this.initialiseInput(input);
        System.out.println(Arrays.toString(this.digits));
        this.frame = new JFrame();
        this.frame.setTitle("NumVis");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(new Dimension(WIDTH, HEIGHT));
        this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        GridVisualiser visualisation = new GridVisualiser(this.digits);
        this.frame.add(visualisation);
        this.frame.pack();
        this.frame.setAlwaysOnTop(true);
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        NumVis m = new NumVis(args[0]);
    }

    private int[] initialiseFromFile(File file) {
        int[] digitsTemp = new int[MAX_FROM_FILE];
        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            int cur;
            int i = 0;
            while ((cur = br.read()) != -1 && i < digitsTemp.length) {
                // check that the character is a digit
                if ((cur -= 48) >= 0 && cur <= 9) {
                    digitsTemp[i] = cur;
                    i++;
                }
            }
            return digitsTemp;
        } catch (FileNotFoundException | SecurityException ex) {
            System.out.println(ex);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println(ex);
            System.exit(0);
        }
        return null;
    }

    private boolean isFilePath(String input) {
        return input.startsWith("path:");
    }

    private int[] initialiseInput(String input) {
        int[] digitsTemp = new int[input.length()];
        for (int i = 0, j = 0; i < digitsTemp.length; i++) {
            int cur = input.charAt(i);
            if ((cur -= 48) >= 0 && cur <= 9) {
                digitsTemp[j] = cur;
                j++;
            }
        }
        return digitsTemp;
    }
}
