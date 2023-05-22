package snake.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import snake.game.entities.cells.terrain.BrightTerrainCell;
import snake.game.entities.cells.terrain.DarkTerrainCell;
import snake.game.entities.cells.terrain.TerrainCell;

public class Board {
    private final Array<Array<TerrainCell>> _cells = new Array<>();
    public Board(int width, int height) {
        for (int i = 0; i < height; ++i) {
            _cells.add(new Array<>());
            for(int j = 0; j < width; ++j) {
                _cells.get(i).add((i + j) % 2 == 0 ? new DarkTerrainCell(j, i) : new BrightTerrainCell(j, i));
            }
        }
    }

    public int getWidth() {
        return _cells.get(0).size;
    }
    public int getHeight() {
        return _cells.size;
    }

    public Array<Array<TerrainCell>> getCells() {
        return _cells;
    }
    public void draw(SpriteBatch batch) {
        for(var row : _cells) {
            for(var cell : row) {
                cell.draw(batch);
            }
        }
    }
}
