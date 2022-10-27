package pl.pieter.utils;

import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;

public class SvgGlyphUtils extends Region {

    public SvgGlyphUtils(String svgPath, double width, double height) {
        SVGPath shape = new SVGPath();
        shape.setContent(svgPath);
        setShape(shape);

        setPrefWidth(width);
        setPrefHeight(height);

        setMinWidth(width);
        setMinHeight(height);

        setMaxWidth(width);
        setMaxHeight(height);
    }
}
