package GUI.Control;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.brick.WoodenBrick;

public class WoodenBrickControl extends Component {
    private WoodenBrick wb;
    private int Health;

    public void setBrick(WoodenBrick w) {
        wb = w;
        Health = 3;
    }

    public void isHit() {
        wb.hit();
        Health--;
        if (Health > 0) {
            FXGL.getAudioPlayer().playSound("wood_hit.wav");
            if (Health == 2) {
                this.getEntity().setView(FXGL.getAssetLoader().loadTexture("woodd.png", 60, 30));
            }
        }
        else {
            FXGL.getAudioPlayer().playSound("bbomb.wav");
            this.getEntity().removeFromWorld();
        }
    }
}
