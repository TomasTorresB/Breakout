package GUI.Control;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import logic.brick.GlassBrick;
/**
 * Component used on the GlassBrick entity
 * Can be binded to a logic Glassbrick
 *
 */

public class GlassBrickControl extends Component {
    private GlassBrick gb;

    public void setBrick(GlassBrick g) {
        gb = g;
    }

    public void isHit() {
        FXGL.getAudioPlayer().playSound("glass_hit.wav");
        gb.hit();
        this.getEntity().removeFromWorld();
    }
}
