package GUI.Control;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.geometry.Point2D;

public class PlayerControl  extends Component {

    public void moveRight() {
        if (entity.getX() >= 500) {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(500,525));
        }
        else {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(entity.getX() + 1,525));
        }
    }

    public void moveLeft() {
        if (entity.getX() <= 0) {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(0,525));
        }
        else {
            entity.getComponent(PhysicsComponent.class).reposition(new Point2D(entity.getX() - 1,525));
        }

   }
}
