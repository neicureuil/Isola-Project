package fr.isola.ui.layouts;

import java.awt.*;

/**
 * LayoutManager custom permettant d'afficher un élément a gauche et un au centre sur la même ligne.
 */
public class LeftAndCenterLayout implements LayoutManager2 {

    /**
     * Id gauche.
     */
    public static final Integer LEFT = 0;

    /**
     * Id centre.
     */
    public static final Integer CENTERED = 1;

    /**
     * Composant gauche.
     */
    private Component leftComponent;

    /**
     * Composant centre.
     */
    private Component centeredComponent;

    @Override
    public void addLayoutComponent(String name, Component comp) { }

    /**
     * Supprime un composant de la layout.
     * @param comp
     */
    @Override
    public void removeLayoutComponent(Component comp) {
        if (leftComponent == comp) {
            leftComponent = null;
        } else if (centeredComponent == comp) {
            centeredComponent = null;
        }
    }

    /**
     * @param parent Containeur parent.
     * @return Les dimensions recommander de la layout.
     */
    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Dimension d = new Dimension();
        for (Component c : parent.getComponents()) {
            //wide enough to stack the left and center components horizontally without overlap
            d.width += c.getPreferredSize().width;
            //tall enough to fit the tallest component
            d.height = Math.max(d.height, c.getPreferredSize().height);
        }
        return d;
    }

    /**
     * @param parent L'élément parent.
     * @return Taille minimum de la layout.
     */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent);
    }

    /**
     * Gestion du placement des éléments dans la layout.
     * @param parent La conteneur parent.
     */
    @Override
    public void layoutContainer(Container parent) {
        //in this method we will:
        //1) position the left component on the left edge of the parent and center it vertically
        //2) position the center component in the center of the parent (as long as it would not overlap
        //the left component) and center it vertically

        int leftComponentWidth = leftComponent.getPreferredSize().width;
        int leftComponentHeight = leftComponent.getPreferredSize().height;
        int centeredComponentWidth = centeredComponent.getPreferredSize().width;
        int centeredComponentHeight = centeredComponent.getPreferredSize().height;

        leftComponent.setBounds(10, (parent.getHeight() - leftComponentHeight) / 2, leftComponentWidth, leftComponentHeight);
        int leftComponentRightEdge = leftComponent.getX() + leftComponent.getWidth();
        int centerComponentLeftEdge = (parent.getWidth() - centeredComponentWidth) / 2;
        int centerComponentTopEdge = (parent.getHeight() - centeredComponentHeight) / 2;

        if (leftComponentRightEdge >= centerComponentLeftEdge) {
            //Center component will "do its best" to remain in the center
            //but it will not do so if it would cause it to overlap the left component
            centerComponentLeftEdge = leftComponentRightEdge;
        }

        centeredComponent.setBounds(centerComponentLeftEdge,
                centerComponentTopEdge,
                centeredComponentWidth,
                centeredComponentHeight);
    }

    /**
     * Ajoute un composant a la layout.
     * @param comp Le composant a ajouter.
     * @param constraints La contrainte du composant.
     */
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (LEFT.equals(constraints)) {
            if (leftComponent != null) {
                throw new IllegalStateException("A left component has already been assigned to this layout.");
            }
            leftComponent = comp;
        } else if (CENTERED.equals(constraints)) {
            if (centeredComponent != null) {
                throw new IllegalStateException("A centered component has already been assigned to this layout.");
            }
            centeredComponent = comp;
        } else {
            throw new IllegalStateException("Unexpected constraints '" + constraints + "'.");
        }
    }

    /**
     * @param target Conteneur parent.
     * @return Taille maximum de la layout.
     */
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    /**
     * @param target Conteneyr parent.
     * @return L'alignement horizontal de la layout.
     */
    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    /**
     * @param target Conteneur parent.
     * @return L'alignement vertical de la layout.
     */
    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {

    }
}