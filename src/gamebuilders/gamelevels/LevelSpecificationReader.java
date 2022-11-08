package gamebuilders.gamelevels;

import gamebuilders.Velocity;
import sprites.collidables.Block;
import java.io.Reader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *@author Itay Sova
 * This class reads the specifications of the level and makes out list of levels out of it.
 */
public class LevelSpecificationReader {
    //File lvlDefinition = new File("level_definition.txt");
    //Reader reader2 = new FileReader("c:\\level_definition.txt");
    //throws IOException

    /**
     * This method reads a file with the level specifications and makes a list of levels.
     * @param reader the reader of the file.
     * @return list of levels.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
               List<List<String>> listOfLevels = this.fileToLevels(reader);
               //List<Velocity> listOfVelocities = new ArrayList<>();
               List<LevelInformation> listOfLevelsInformation = new ArrayList<LevelInformation>();
               BlocksFromSymbolsFactory blocksFact = new BlocksFromSymbolsFactory();
                String levelName = "";
                Image imageVar = null;
                int paddleSpeed = 0;
                int paddleWidth = 0;
                int blockStartX = 0;
                int blockStartY = 0;
                int numBlocks = 0;
                int rowHeight = 0;
                String defFilename = "";
                String[] splitedBlocksSymbols = null;
               for (List<String> level: listOfLevels) {
                   List<Block> blocksA = new ArrayList<Block>();
                   List<Velocity> listOfVelocities = new ArrayList<>();
                   for (String levelstr : level) {
                       String[] levelStrS = levelstr.split(":");
                       if (levelStrS.length > 1) {
                           switch (levelStrS[0].trim()) {
                               case "level_name":
                                   levelName = levelStrS[1].substring(0, levelStrS[1].trim().length());
                                   break;
                               case "ball_velocities":
                                   String[] splitedVel = levelStrS[1].trim().split(",");
                                   for (int i = 0; i < splitedVel.length / 2; i++) {
                                       Double dx = Double.parseDouble(splitedVel[i]);
                                       Double dy = Double.parseDouble(splitedVel[i + 1]);
                                       Velocity velocityT = new Velocity(dx, dy);
                                       listOfVelocities.add(velocityT);
                                   }
                                   break;
                               case "background":
                                   String[] splitedImage = levelStrS[1].split("/");
                                   String imageStr1 = splitedImage[0].substring(6);
                                   String imageStr2 = splitedImage[1].substring(0, splitedImage[1].length() - 1);
                                   try {
                                       imageVar = ImageIO.read(new File("resources//" + imageStr1 + "//" + imageStr2));
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                                   break;
                               case "paddle_speed":
                                   paddleSpeed = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "paddle_width":
                                   paddleWidth = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "block_definitions":
                                   String[] defSplited = levelStrS[1].trim().split("/");
                                   String defStr1 = defSplited[0].trim();
                                   String defStr2 = defSplited[1].trim();
                                   defFilename = "resources//" + defStr1 + "//" + defStr2;
                                   break;
                               case "blocks_start_x":
                                   blockStartX = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "blocks_start_y":
                                   blockStartY = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "row_height":
                                   rowHeight = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "num_blocks":
                                   numBlocks = Integer.parseInt(levelStrS[1].trim());
                                   break;
                               case "START_BLOCKS":
                                   splitedBlocksSymbols = levelStrS[1].trim().split(",");
                                   break;
                               default:
                                   break;
                           }
                       }

                   }

                   try {

                       FileInputStream fstream = new FileInputStream(defFilename);
                       InputStreamReader blockreader = new InputStreamReader(fstream);
                       BlocksDefinitionReader blockRead = new BlocksDefinitionReader();
                       blocksFact = blockRead.fromReader(blockreader);
                       if (splitedBlocksSymbols != null) {
                           Block blockAdder = null;
                          for (int bIndex = 0; bIndex < splitedBlocksSymbols.length; bIndex++) {
                              int tBlockStartX = blockStartX;
                              int tBlockStartY = blockStartY;  //+b_index*20;

                              for (int m = 0; m < splitedBlocksSymbols[bIndex].length(); m++) {

                                  if (splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("-")
                                          || splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("_")
                                          || splitedBlocksSymbols[bIndex].substring(m, m + 1).contains(".")) {
                                      if (splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("-")) {
                                          tBlockStartX += blocksFact.getSpaceWidth("-");
                                      } else if (splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("_")) {
                                          tBlockStartX += blocksFact.getSpaceWidth("_");
                                      } else {
                                          tBlockStartX += blocksFact.getSpaceWidth(".");
                                      }

                                      continue;
                                  }

                                  BlockCreator blockCreator =
                                          blocksFact.getBlockCreators(splitedBlocksSymbols[bIndex].substring(m, m + 1));
                                  int h = blockCreator.getheight();
                                  if (splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("m")) {
                                      h = rowHeight;
                                  }
                                  String otherColor = "";
                                  if ((levelName.contains("Thirsty?")
                                          && splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("c"))) {
                                      otherColor = "Cola";
                                  } else if (levelName.contains("Paycheck")
                                          && splitedBlocksSymbols[bIndex].substring(m, m + 1).contains("c")) {
                                      otherColor = "Safebox";
                                  }
                                  Block b = new Block(tBlockStartX, tBlockStartY + bIndex * h, blockCreator.getWidth(),
                                          blockCreator.getheight(), blockCreator.getfillColor(),
                                          blockCreator.getstrokeColor(), otherColor);
                                  b.setIconImage(blockCreator.getImageIcon());
                                  b.setHitCounter(blockCreator.gethitPoints());
                                  blocksA.add(b);
                                  tBlockStartX += blockCreator.getWidth();
                              }
                           }

                       }
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   int numBalls = 1;
                   //if (levelName.contains("Thirsty?")) numBalls = 2;
                  // if (levelName.contains("Paycheck")) numBalls = 3;
                   LevelInformation newLevel = new GenericLevel(levelName, listOfVelocities, imageVar, paddleSpeed,
                           paddleWidth, numBlocks, blocksA);  //num_blocks);
                   listOfLevelsInformation.add(newLevel);



               }

            return listOfLevelsInformation;  //null;
    }

    /**
     * This method splits each level in the file and returns a list of the splited levels(string type).
     * @param reader the reader of the file.
     * @return list of splited levels(string type).
     */
    public List<List<String>> fileToLevels(Reader reader) {
        BufferedReader bufferReader = new BufferedReader(reader);
        List<List<String>> listOfLevels = new ArrayList<List<String>>();
        List<String> newLevel = new ArrayList<String>();
        try {
            String line;

            while ((line = bufferReader.readLine()) != null) {
                boolean startBlock = false;
                String blockStr = "";
                while ((!line.trim().contains("END_LEVEL")) && (line != null)) {

                 if (line.indexOf(":") >= 0) {
                     newLevel.add(line.trim());
                 } else if (startBlock && !line.trim().contains("END_BLOCKS")) {
                     blockStr += line.trim() + ",";
                 }
                 if (line.trim().contains("START_BLOCKS")) {
                     startBlock = true;
                     blockStr = "START_BLOCKS:";
                 }
                 if (line.trim().contains("END_BLOCKS")) {
                     startBlock = false;
                    newLevel.add(blockStr);
                 }
                 line = bufferReader.readLine();
                }
                listOfLevels.add(newLevel);

                newLevel = new ArrayList<String>();
            }

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
        return listOfLevels;
    }

}