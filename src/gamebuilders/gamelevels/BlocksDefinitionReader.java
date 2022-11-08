package gamebuilders.gamelevels;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;
/**
 *@author Itay Sova
 * This class reads the definitions of block from definition_blocks files.
 */
public class BlocksDefinitionReader {
    /**
     * This method reads the file and make a blocksfromSymbolsFactory.
     * @param reader the reader of the file.
     * @return the blocks from symblos factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader bufferReader = new BufferedReader(reader);
        BlocksFromSymbolsFactory blocks = new BlocksFromSymbolsFactory();
        Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
        Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
        try {
            String line = "";
            int defaultH = 0, defaultW = 0, hitPoints = 0, blockH = 0, blockW = 0;
            Color stroke = null, fill = null;
            String symbol = "";
            while ((line = bufferReader.readLine()) != null) {
                if (line.contains("#")) {
                    continue;
                }
                String[] splited = line.split(" ");
                Image imageVar = null;
                for (int index = 0; index < splited.length; index++) {
                    if (splited[index].contains("height")) {
                        String[] splitedAt0 = splited[index].split(":");
                        if (splitedAt0.length > 1) {
                            blockH = Integer.parseInt(splitedAt0[1]);
                            if (splited[0].contains("default")) {
                                defaultH = blockH;
                            }
                        }
                    } else if (splited[index].contains("width")) {
                        String[] splitedAt0 = splited[index].split(":");
                        if (splitedAt0.length > 1) {
                            blockW = Integer.parseInt(splitedAt0[1]);
                            if (splited[0].contains("default")) {
                                defaultW = blockW;
                            }
                        }
                    } else if (splited[index].contains("hit_points")) {
                        String[] splitedAt0 = splited[index].split(":");
                        if (splitedAt0.length > 1) {
                            hitPoints = Integer.parseInt(splitedAt0[1]);
                        }
                    } else if (splited[index].contains("color(")) {
                        int ci1 = splited[index].indexOf('(');
                        int ci2 = splited[index].indexOf(')');
                        try {
                            Field field = null;
                            String cStr = splited[index].trim().substring(ci1 + 1, ci2);
                            field = Class.forName("java.awt.Color").getField(cStr);
                            if (field != null) {
                                if (splited[index].contains("stroke")) {
                                    stroke = (Color) field.get(null);
                                } else {
                                    fill = (Color) field.get(null);
                                }
                                if (fill == null && stroke != null) {
                                    fill = stroke;
                                }
                            }

                        } catch (Exception e) {
                            if (splited[index].contains("Burger")) {
                                fill = new Color(97, 67, 7);
                            } else if (splited[index].contains("LightGray")) {
                                fill = new Color(228, 228, 228);
                            } else if (splited[index].contains("Cola")) {
                                fill = Color.red;
                            }
                        }
                    } else if (splited[index].contains("image(")) {
                        int imageIndex = splited[index].indexOf("image(");
                        String[] splitedImage = splited[index].substring(imageIndex + 6).split("/");
                        String imageStr1 = splitedImage[0];
                        String imageStr2 = splitedImage[1].substring(0, splitedImage[1].length() - 1);
                        try {
                            imageVar = ImageIO.read(new File("resources//" + imageStr1 + "//" + imageStr2));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
                if (line.contains("sdef")) {
                    String symbolStr = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);
                    int width = Integer.parseInt(line.substring(line.indexOf("width:") + 6));
                    spacerWidths.put(symbolStr, width);
                }
                if (line.contains("bdef")) {
                    symbol = line.substring(line.indexOf("symbol:") + 7, line.indexOf("symbol:") + 8);
                    BlockCreator blockToCreate = new BlockMaker(blockH, blockW, hitPoints, fill, stroke, imageVar, "");
                    blockCreators.put(symbol, blockToCreate);
                }
            }
            blocks.setBlockCreators(blockCreators);
            blocks.setSpacerWidths(spacerWidths);
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            if (bufferReader != null) { // Exception might have happened at constructor
                try {
                    bufferReader.close(); // closes FileInputStream too
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }

        return blocks;
    }
}
