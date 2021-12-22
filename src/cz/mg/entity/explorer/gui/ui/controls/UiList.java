package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Value;
import cz.mg.entity.explorer.gui.ui.UiColorUtilities;
import cz.mg.entity.explorer.gui.ui.UiConstants;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;

import javax.swing.*;
import java.awt.*;


public @Utility class UiList extends UiPanel {
    private static final int BORDER = 2;
    private static final int PADDING = 2;
    private static final Color FOCUSED_SELECTION_COLOR = UiConstants.getListSelectionBackgroundColor();
    private static final Color UNFOCUSED_SELECTION_COLOR = UiColorUtilities.grayscale(FOCUSED_SELECTION_COLOR);

    private @Optional @Value Integer selectedIndex;

    public UiList() {
        super(BORDER, PADDING, UiAlignment.TOP);
        setOpaque(true);
        setBorder(BorderFactory.createEtchedBorder());
        setBackground(UiConstants.getListBackgroundColor());
    }

    public void addItem(@Mandatory Component component){
        addVertical(component, 1, 0, UiAlignment.MIDDLE, UiFill.BOTH);
    }

    public @Optional Integer getSelectedIndex() {
        return selectedIndex;
    }

    public void selectItem(@Optional Integer index){
        if(index == null){
            selectedIndex = null;
        } else {
            if(getComponents().length < 1){
                selectedIndex = null;
            } else {
                if(index < 0){
                    selectedIndex = 0;
                } else if(index > (getComponents().length - 1)){
                    selectedIndex = getComponents().length - 1;
                } else {
                    selectedIndex = index;
                }
            }
        }
        repaint();
    }

    private void selectItem(@Mandatory Component target) {
        int i = 0;
        for(Component component : getComponents()){
            if(component == target){
                selectItem(i);
                break;
            }
            i++;
        }
    }

    @Override
    protected void paintComponent(@Mandatory Graphics g) {
        super.paintComponent(g);
        if(selectedIndex != null){
            Component component = getComponent(selectedIndex);
            if(childHasFocus()){
                g.setColor(FOCUSED_SELECTION_COLOR);
            } else {
                g.setColor(UNFOCUSED_SELECTION_COLOR);
            }
            g.fillRect(
                component.getX() - BORDER,
                component.getY(),
                component.getWidth() + 2*BORDER,
                component.getHeight()
            );
        }
    }
}
