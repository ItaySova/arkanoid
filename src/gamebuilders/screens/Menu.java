package gamebuilders.screens;

import gamebuilders.Animation;

/**
 * @author Itay Sova
 * @param <T> type of menu.
 */
public interface Menu<T> extends Animation {
    /**
     * Adding selection option to the menu.
     * @param key to press for this option.
     * @param message message to write about this option.
     * @param returnVal the value.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * Adding a sub menu to the menu.
     * @param key to press for this option.
     * @param message message to write about this option.
     * @param subMenu the submenu value.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * This method returns the status of the menu.
     * @return the status of the menu.
     */
    T getStatus();
    /**
     * This method returns the subMenu string type.
     * @return subMenu string type.
     */
    String getsubMenuMess1();
    /**
     * This method sets the subMenu string type.
     * @param str the string type.
     */
    void setsubMenuMess1(String str);

}