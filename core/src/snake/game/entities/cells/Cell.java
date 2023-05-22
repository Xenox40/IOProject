package snake.game.entities.cells;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import snake.assets.Assets;

import static snake.GlobalVariables.CELL_SIZE;

public abstract class Cell {
    protected final Sprite _sprite = new Sprite(Assets.getInstance().getTexture(this), CELL_SIZE, CELL_SIZE);

    protected Cell(int gridX, int gridY) {
        setPosition(gridX, gridY);
    }
    protected Cell(float px, float py) {
        setPosition(px, py);
    }

    public Cell(Vector2 position) {
        _sprite.setPosition(position.x, position.y);
    }

    public void setPosition(int gridX, int gridY) {
        var posInPixels = new Vector2(gridX * CELL_SIZE, gridY * CELL_SIZE);
        _sprite.setPosition(posInPixels.x, posInPixels.y);
    }
    public void setPosition(float px, float py) {
        _sprite.setPosition(px, py);
    }
    public void setPosition(Cell cell) {
        _sprite.setPosition(cell.getX(), cell.getY());
    }

    public boolean collidesWith(Cell other) {
        return _sprite.getBoundingRectangle().overlaps(other._sprite.getBoundingRectangle());
    }
    public float getX() {
        return _sprite.getX();
    }
    public float getY() {
        return _sprite.getY();
    }

    public Vector2 getPosition() {
        return new Vector2(_sprite.getX(), _sprite.getY());
    }

    public void draw(SpriteBatch batch) {
        _sprite.draw(batch);
    }
}
