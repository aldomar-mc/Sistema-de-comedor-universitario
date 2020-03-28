/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skin;

/**
 *
 * @author Omr
 */
import java.awt.Color;
import org.jvnet.substance.border.ClassicBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.color.BaseColorScheme;
import org.jvnet.substance.painter.AlphaControlBackgroundComposite;
import org.jvnet.substance.painter.ClassicGradientPainter;
import org.jvnet.substance.painter.SimplisticSoftBorderReverseGradientPainter;
import org.jvnet.substance.painter.decoration.ArcDecorationPainter;
import org.jvnet.substance.painter.decoration.DecorationAreaType;
import org.jvnet.substance.painter.highlight.ClassicHighlightPainter;
import org.jvnet.substance.skin.SubstanceAbstractSkin;
import org.jvnet.substance.theme.SubstanceComplexTheme;
import org.jvnet.substance.theme.SubstanceTheme;
import org.jvnet.substance.utils.ComponentState;
import org.jvnet.substance.watermark.SubstanceNullWatermark;

public class JyMSystemSoftBlueMetalSkin extends SubstanceAbstractSkin {

    public static String NAME = "JyM System Soft Blue Metal";

    public JyMSystemSoftBlueMetalSkin() {
        SubstanceTheme activeTheme = new SubstanceTheme(new BlueColorScheme(), "Tint Blue Metal", SubstanceTheme.ThemeKind.COLD).tint(0.15D);

        SubstanceTheme defaultTheme = new SubstanceTheme(new MetallicColorScheme(), "Tint Metallic", SubstanceTheme.ThemeKind.COLD).tint(0.05D);

        SubstanceTheme disabledTheme = new SubstanceTheme(new DisabledMetallicColorScheme(), "Tint Metallic", SubstanceTheme.ThemeKind.COLD).tint(0.05D);
        SubstanceTheme activeTitleTheme = new SubstanceTheme(new BlueColorScheme(), "Tint Blue Metal", SubstanceTheme.ThemeKind.COLD).tint(0.15D);

        SubstanceComplexTheme theme = new SubstanceComplexTheme(NAME, SubstanceTheme.ThemeKind.COLD, activeTheme, defaultTheme, disabledTheme, activeTitleTheme);

        theme.registerComponentHighlightStateTheme(activeTheme, 0.6F, new ComponentState[]{ComponentState.ROLLOVER_UNSELECTED});

        theme.registerComponentHighlightStateTheme(activeTheme, 0.8F, new ComponentState[]{ComponentState.SELECTED});

        theme.registerComponentHighlightStateTheme(activeTheme, 0.95F, new ComponentState[]{ComponentState.ROLLOVER_SELECTED});

        theme.registerComponentHighlightStateTheme(activeTheme, 0.8F, new ComponentState[]{ComponentState.ARMED, ComponentState.ROLLOVER_ARMED});

        SubstanceComplexTheme decorationTheme = new SubstanceComplexTheme("JyM System Soft Blue Metal Decoration", SubstanceTheme.ThemeKind.DARK, activeTitleTheme.shade(0.3D), activeTitleTheme, disabledTheme);

        theme.registerDecorationAreaTheme(decorationTheme, true, new DecorationAreaType[]{DecorationAreaType.PRIMARY_TITLE_PANE, DecorationAreaType.SECONDARY_TITLE_PANE});

        theme.registerDecorationAreaTheme(activeTitleTheme, false, new DecorationAreaType[]{DecorationAreaType.HEADER});
        SubstanceComplexTheme generalDecorationTheme = new SubstanceComplexTheme("JyM System Soft Blue Metal General Decoration", SubstanceTheme.ThemeKind.DARK, activeTheme.shade(0.1D).saturate(-0.5D), activeTheme.tint(0.3D).saturate(-0.7D), disabledTheme);

        generalDecorationTheme.registerComponentStateTheme(disabledTheme, 0.5F, false, new ComponentState[]{ComponentState.DISABLED_UNSELECTED});

        theme.registerDecorationAreaTheme(generalDecorationTheme, true, new DecorationAreaType[]{DecorationAreaType.GENERAL});

        theme.setSelectedTabFadeStart(0.1D);
        theme.setSelectedTabFadeEnd(0.3D);
        theme.setNonActivePainter(new SimplisticSoftBorderReverseGradientPainter());

        theme.setDefaultTitlePaneTheme(activeTitleTheme);

        theme.setPaintingToolbarDropShadows();

        this.theme = theme;

        this.buttonShaper = new ClassicButtonShaper();
        this.watermark = new SubstanceNullWatermark();
        this.gradientPainter = new ClassicGradientPainter();
        this.borderPainter = new ClassicBorderPainter();

        ArcDecorationPainter decorationPainter = new ArcDecorationPainter();
//        decorationPainter.setBaseDecorationPainter(new ArcDecorationPainter());
//        decorationPainter.setTextureAlpha(0.02F);
        this.decorationPainter = decorationPainter;

        this.highlightPainter = new ClassicHighlightPainter();

        this.tabBackgroundComposite = new AlphaControlBackgroundComposite(0.5F);

        this.borderPainter = new ClassicBorderPainter();
    }

    public String getDisplayName() {
        return NAME;
    }

    static class BlueColorScheme extends BaseColorScheme {

        private static final Color mainUltraLightColor = new Color(120, 230, 222);
        private static final Color mainExtraLightColor = new Color(81, 152, 195);
        private static final Color mainLightColor = new Color(58, 132, 186);
        private static final Color mainMidColor = new Color(50, 111, 180);
        private static final Color mainDarkColor = new Color(43, 100, 177);
        private static final Color mainUltraDarkColor = new Color(32, 71, 143);
        private static final Color foregroundColor = new Color(0, 0, 0);


        public Color getForegroundColor() {
            return foregroundColor;
        }

        public Color getUltraLightColor() {
            return mainUltraLightColor;
        }

        public Color getExtraLightColor() {
            return mainExtraLightColor;
        }

        public Color getLightColor() {
            return mainLightColor;
        }

        public Color getMidColor() {
            return mainMidColor;
        }

        public Color getDarkColor() {
            return mainDarkColor;
        }

        public Color getUltraDarkColor() {
            return mainUltraDarkColor;
        }
    }

    static class MetallicColorScheme extends BaseColorScheme {

        private static final Color mainUltraLightColor = new Color(251, 252, 255);
        private static final Color mainExtraLightColor = new Color(243, 247, 251);
        private static final Color mainLightColor = new Color(213, 221, 228);
        private static final Color mainMidColor = new Color(198, 202, 206);
        private static final Color mainDarkColor = new Color(123, 127, 131);
        private static final Color mainUltraDarkColor = new Color(87, 91, 95);
        private static final Color foregroundColor = new Color(25, 29, 33);

        public Color getForegroundColor() {
            return foregroundColor;
        }

        public Color getUltraLightColor() {
            return mainUltraLightColor;
        }

        public Color getExtraLightColor() {
            return mainExtraLightColor;
        }

        public Color getLightColor() {
            return mainLightColor;
        }

        public Color getMidColor() {
            return mainMidColor;
        }

        public Color getDarkColor() {
            return mainDarkColor;
        }

        public Color getUltraDarkColor() {
            return mainUltraDarkColor;
        }
    }

    static class DisabledMetallicColorScheme extends BaseColorScheme {

        private static final Color mainUltraLightColor = new Color(250, 251, 252);
        private static final Color mainExtraLightColor = new Color(240, 242, 244);
        private static final Color mainLightColor = new Color(225, 228, 231);
        private static final Color mainMidColor = new Color(210, 214, 218);
        private static final Color mainDarkColor = new Color(180, 185, 190);
        private static final Color mainUltraDarkColor = new Color(100, 106, 112);
        private static final Color foregroundColor = new Color(120, 125, 130);

        public Color getForegroundColor() {
            return foregroundColor;
        }

        public Color getUltraLightColor() {
            return mainUltraLightColor;
        }

        public Color getExtraLightColor() {
            return mainExtraLightColor;
        }

        public Color getLightColor() {
            return mainLightColor;
        }

        public Color getMidColor() {
            return mainMidColor;
        }

        public Color getDarkColor() {
            return mainDarkColor;
        }

        public Color getUltraDarkColor() {
            return mainUltraDarkColor;
        }
    }
}
