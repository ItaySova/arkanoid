package gamebuilders.screens;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamebuilders.AnimationRunner;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itay Sova
 * @param <T> the type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> selections;
    private T status;
    private boolean stop;
    private KeyboardSensor keyboard;
    private AnimationRunner ar;
    private List<Selection<T>> subMenuSelections;
    private String keySaver = "";
  //  public List<Menu<T>> sub_menus;
    private String subMenuMess1 = "";

    /**
     * This method builds the menu animation from keyboard and animation runner.
     * @param keyboardSensor keyboard of the game.
     * @param ar animation runner.
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, AnimationRunner ar) {
        this.selections = new ArrayList<Selection<T>>();
        this.status = null;
        this.keyboard = keyboardSensor;
        this.ar = ar;
        this.stop = false;
        this.subMenuSelections = new ArrayList<Selection<T>>();
    //    this.sub_menus = new ArrayList<Menu<T>>();
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection<T>(key, message, returnVal));
    }
    @Override
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenuSelections.add(new Selection<T>(key, message, subMenu.getStatus()));
      //  sub_menus.add(subMenu);
    }
    @Override
    public T getStatus() {
        T statusSaver = this.status;
        this.stop = false;
        this.status = null;
        return statusSaver;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        Selection temp;
        if (subMenuMess1 != "") {
            d.drawText(240, 50, subMenuMess1, 20);
        }
        for (int i = 0; i < this.selections.size(); i++) {
            temp = selections.get(i);
            d.drawText(100, ((i + 1) * 120), " (" + temp.getKey() + ") " + temp.getMessage(), 20);
        }
       for (Selection<T> s : selections) {
            if (this.keyboard.isPressed(s.getKey())) {
                this.keySaver = s.getKey();
                this.status = s.getReturnVal();
                this.stop = true;
                for (Selection<T> subM : subMenuSelections) {
                    if (keySaver == subM.getKey()) {
                        subMenuMess1 = subM.getMessage();
                     //   System.out.println("sub Menue Message:"+s_m.getMessage());
                        break;
                    }
                }
            }
        }
      //  for (Menu<T> s_m : sub_menus) {
       //     if (s_m==this)  d.drawText(120, 80, "Sub Menue",20);  //this.SubMenuSelections.getMessage(), 20);
       // }

    }
    @Override
    public String getsubMenuMess1() {
        return subMenuMess1;
    }
    @Override
    public void setsubMenuMess1(String str) {
        subMenuMess1 = str;
    }
  /*  public T make_animation() {

        ar.run(this);
        return(this.status);
    }*/
    /*
       public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        Selection temp;
        for (int i = 0; i < this.selections.size(); i++) {
            temp = selections.get(i);
            d.drawText(100, ((i + 1) * 120), " (" + temp.getKey() + ") " + temp.getMessage(), 20);
        }

        for (Selection<T> s : selections) {
            if (this.keyboard.isPressed(s.getKey())) {
                this.keySaver=s.getKey();
                this.status = s.getReturnVal();
                this.stop = true;
            }
        }
    }
     */
  /*for (Selection<T> s_m : SubMenuSelections) {
                    if (keySaver == s_m.getKey()) {
                        for (int i = 0; i < this.SubMenuSelections.size(); i++) {
                            temp = SubMenuSelections.get(i);
                            d.drawText(100, ((i + 1) * 120), " (" + temp.getKey() + ") " + temp.getMessage(), 20);
                            break;
                        }
                    }
                }*/
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public String getKey() {
        return keySaver;
    }


}