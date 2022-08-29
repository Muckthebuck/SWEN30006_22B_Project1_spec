package src;

import ch.aplu.jgamegrid.Actor;
import src.utility.PropertiesLoader;

import java.awt.*;
import java.util.Arrays;
import java.util.Properties;

public class Driver {
    public static final String DEFAULT_PROPERTIES_PATH = "properties/test1.properties";

    /**
     * Starting point
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        String propertiesPath = DEFAULT_PROPERTIES_PATH;
        System.out.println("Arrays.asList(args) = " + Arrays.asList(args));
        if (args.length > 0) {
            propertiesPath = args[0];
        }
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        boolean isLoggingTest = Boolean.parseBoolean(properties.getProperty("logTest", "false"));
        TetrisGameCallback gameCallback = new TetrisGameCallback(isLoggingTest);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tetris(gameCallback, properties).setVisible(true);
            }
        });
    }
}
