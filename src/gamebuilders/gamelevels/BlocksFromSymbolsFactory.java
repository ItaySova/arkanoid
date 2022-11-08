package gamebuilders.gamelevels;
import sprites.collidables.Block;
import java.util.Map;
/**
 *@author Itay Sova
 * This class creates a factory that builds blocks from symbols.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
   // private BlockCreator block;   //==18.6.19==
    /**
     * returns true if 's' is a valid space symbol.
     * @param s the symbol string.
     * @return returns true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        if (spacerWidths.containsKey("s")) {
            return true;
        }
        return false;
    }

    /**
     * returns true if 's' is a valid block symbol.
     * @param s the symbol string.
     * @return returns true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.containsKey("s")) {
            return true;
        }
        return false;
    }
    /**
     * Returns a block according to the definitions associated
     * with symbol s. The block will be located at position (xpos, ypos).
     * @param s the string representing the block.
     * @param xpos the x position.
     * @param ypos the y position.
     * @return a block.
     */
    public Block getBlock(String s, int xpos, int ypos) {

        return this.blockCreators.get(s).create(xpos, ypos);
    }

    //

    /**
     * Returns the width in pixels associated with the given spacer-symbol.
     * @param s the string representing the block.
     * @return the width in pixels.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }

    /**
     * This method sets the block creators map.
     * @param blockCreatorsParam the block creators map.
     */
    public void setBlockCreators(Map<String, BlockCreator> blockCreatorsParam) {
        this.blockCreators = blockCreatorsParam;

    }

    /**
     * This method returns the blockcreators map according to a string symbol.
     * @param s the symbol.
     * @return a blockcreator.
     */
    public  BlockCreator  getBlockCreators(String s) {
        return this.blockCreators.get(s);

    }

    /**
     * This method sets the space widths map.
     * @param spacerWidthsParam the spacer widths map.
     */
    public void setSpacerWidths(Map<String, Integer> spacerWidthsParam) {
        this.spacerWidths = spacerWidthsParam;
    }
}