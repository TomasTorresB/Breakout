package GUI.Control;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.MetalBrick;

public class MetalBrickControl extends Component {
    private MetalBrick mb;
    private int Health;

    public void setBrick(MetalBrick m) {
        mb = m;
        Health  = 10;
    }

    public void isHit() {
        mb.hit();
        Health--;
        if (Health > 0) {
            FXGL.getAudioPlayer().playSound("metal_hit.wav");
            if (Health == 5) {
                this.getEntity().setView(FXGL.getAssetLoader().loadTexture("metald.png", 60, 30));
            }
        }
        else {
            FXGL.getAudioPlayer().playSound("bbomb.wav");
            this.getEntity().removeFromWorld();
        }
    }

}
