package snake.game.entities.cells;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static snake.GlobalVariables.CELL_SIZE;

public abstract class Cell {
    protected final Sprite _sprite;

    private Cell(Texture texture) {
        _sprite = new Sprite(texture, CELL_SIZE, CELL_SIZE);
    }
    protected Cell(int gridX, int gridY, Texture texture) {
        this(texture);
        setPosition(gridX, gridY);
    }
    protected Cell(float px, float py, Texture texture) {
        this(texture);
        setPosition(px, py);
    }

    protected Cell(Vector2 position, Texture texture) {
        this(texture);
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
