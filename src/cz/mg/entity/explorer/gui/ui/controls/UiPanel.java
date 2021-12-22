package cz.mg.entity.explorer.gui.ui.controls;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.annotations.storage.Shared;
import cz.mg.annotations.storage.Value;
import cz.mg.collections.list.List;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment;
import cz.mg.entity.explorer.gui.ui.enums.UiFill;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiHorizontalAlignment;
import cz.mg.entity.explorer.gui.ui.enums.alignment.UiVerticalAlignment;

import javax.swing.*;
import java.awt.*;

import static cz.mg.entity.explorer.gui.ui.enums.alignment.UiAlignment.MIDDLE;
import static cz.mg.entity.explorer.gui.ui.enums.UiFill.BOTH;


public @Utility class UiPanel extends JPanel implements UiComponent {
    private final @Value int border;
    private final @Value int padding;
    private final @Mandatory @Value UiHorizontalAlignment horizontalAlignment;
    private final @Mandatory @Value UiVerticalAlignment verticalAlignment;
    protected final @Mandatory @Part List<ComponentSettings> components = new List<>();

    public UiPanel(int border, int padding, @Mandatory UiAlignment alignment) {
        this.border = border;
        this.padding = padding;
        this.horizontalAlignment = alignment.getHorizontal();
        this.verticalAlignment = alignment.getVertical();
        setOpaque(false);
        setBackground(null);
        rebuild();
    }

    public void add(
        @Mandatory Component component,
        int x, int y, int wx, int wy,
        @Mandatory UiAlignment alignment,
        @Mandatory UiFill fill
    ){
        add(component, x, y, wx, wy, alignment, fill, 1, 1);
    }

    public void add(
        @Mandatory Component component,
        int x, int y, int wx, int wy,
        @Mandatory UiAlignment alignment,
        @Mandatory UiFill fill,
        int spanX, int spanY
    ){
        components.addLast(new ComponentSettings(component, x, y, wx, wy, alignment, fill, spanX, spanY));
    }

    public void addHorizontal(
        @Mandatory Component component,
        int wx, int wy,
        @Mandatory UiAlignment alignment,
        @Mandatory UiFill fill
    ) {
        add(component, components.count(), 0, wx, wy, alignment, fill);
    }

    public void addVertical(
        @Mandatory Component component,
        int wx, int wy,
        @Mandatory UiAlignment alignment,
        @Mandatory UiFill fill
    ) {
        add(component, 0, components.count(), wx, wy, alignment, fill);
    }

    public void clear(){
        removeAll();
        components.clear();
    }

    public void rebuild(){
        removeAll();
        setLayout(new GridBagLayout());

        int maxX = 0;
        int maxY = 0;
        int maxWX = 0;
        int maxWY = 0;

        for(ComponentSettings component : components){
            maxX = Math.max(maxX, component.x);
            maxY = Math.max(maxY, component.y);
            maxWX = Math.max(maxWX, component.wx);
            maxWY = Math.max(maxWY, component.wy);
        }

        for(ComponentSettings component : components){
            int pLeft = component.x == 0 ? border : 0;
            int pRight = component.x == maxX ? border : padding;
            int pTop = component.y == 0 ? border : 0;
            int pBottom = component.y == maxY ? border : padding;

            add(component.component, constraints(
                component.x, component.y, component.wx, component.wy,
                pTop, pLeft, pBottom, pRight,
                component.alignment, component.fill,
                component.spanX, component.spanY
            ));
        }

        if(maxWX == 0){
            if(horizontalAlignment == UiHorizontalAlignment.LEFT){
                add(createDummy(), constraints(-1, 0, 1, 0, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }

            if(horizontalAlignment == UiHorizontalAlignment.RIGHT){
                add(createDummy(), constraints(maxX + 1, 0, 1, 0, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }
        }

        if(maxWY == 0){
            if(verticalAlignment == UiVerticalAlignment.TOP){
                add(createDummy(), constraints(0, -1, 0, 1, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }

            if(verticalAlignment == UiVerticalAlignment.BOTTOM){
                add(createDummy(), constraints(0, maxY + 1, 0, 1, 0, 0, 0, 0, MIDDLE, BOTH, 1, 1));
            }
        }

        repaint();
        revalidate();
    }

    private JPanel createDummy(){
        JPanel dummy = new JPanel();
        dummy.setMinimumSize(new Dimension(0, 0));
        dummy.setPreferredSize(new Dimension(0, 0));
        dummy.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        dummy.setOpaque(false);
        dummy.setBackground(null);
        return dummy;
    }

    private GridBagConstraints constraints(
        int x, int y, int wx, int wy,
        int pTop, int pLeft, int pBottom, int pRight,
        @Mandatory UiAlignment alignment,
        @Mandatory UiFill fill,
        int spanX, int spanY
    ){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.weightx = wx;
        constraints.weighty = wy;
        constraints.fill = fill.getInternalCode();
        constraints.insets = new Insets(pTop, pLeft, pBottom, pRight);
        constraints.anchor = alignment.getInternalAnchor();
        constraints.gridwidth = spanX;
        constraints.gridheight = spanY;
        return constraints;
    }

    private static class ComponentSettings {
        public final @Mandatory @Shared Component component;
        public final @Value int x;
        public final @Value int y;
        public final @Value int wx;
        public final @Value int wy;
        public final @Mandatory @Value UiAlignment alignment;
        public final @Mandatory @Value UiFill fill;
        public final @Value int spanX;
        public final @Value int spanY;

        public ComponentSettings(
            @Mandatory Component component,
            int x, int y, int wx, int wy,
            @Mandatory UiAlignment alignment,
            @Mandatory UiFill fill,
            int spanX, int spanY
        ) {
            this.component = component;
            this.x = x;
            this.y = y;
            this.wx = wx;
            this.wy = wy;
            this.alignment = alignment;
            this.fill = fill;
            this.spanX = spanX;
            this.spanY = spanY;
        }
    }

}
