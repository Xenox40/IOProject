package snake.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    public static final AssetDescriptor<Texture> HeadTexture =
            new AssetDescriptor<>("snake/head.png", Texture.class);
    public static final AssetDescriptor<Texture> BodyTexture =
            new AssetDescriptor<>("snake/body.png", Texture.class);
    public static final AssetDescriptor<Texture> DarkTerrainTexture =
            new AssetDescriptor<>("terrain/dark.png", Texture.class);

    public static final AssetDescriptor<Texture> BrightTerrainTexture =
            new AssetDescriptor<>("terrain/bright.png", Texture.class);
    public static final AssetDescriptor<Texture> WallTexture =
            new AssetDescriptor<>("terrain/wall.png", Texture.class);

    public static final AssetDescriptor<Texture> TeleportTexture =
            new AssetDescriptor<>("terrain/teleport.png", Texture.class);

    public static final AssetDescriptor<Texture> SandTexture =
            new AssetDescriptor<>("terrain/sand.png", Texture.class);

    public static final AssetDescriptor<Texture> IceTexture =
            new AssetDescriptor<>("terrain/ice.png", Texture.class);
    public static final AssetDescriptor<Texture> PointTexture =
            new AssetDescriptor<>("pickup/point.png", Texture.class);

    public static final AssetDescriptor<Texture> SpeedUpTexture =
            new AssetDescriptor<>("pickup/speedup.png", Texture.class);

    public static final AssetDescriptor<Texture> SpeedDownTexture =
            new AssetDescriptor<>("pickup/speeddown.png", Texture.class);

    public static final AssetDescriptor<Texture> DirectionsReverseTexture =
            new AssetDescriptor<>("pickup/directionsreverse.png", Texture.class);

    public static final AssetDescriptor<Texture> GodModeTexture =
            new AssetDescriptor<>("pickup/godmode.png", Texture.class);

    public static final AssetDescriptor<Texture> MorePickupsTexture =
            new AssetDescriptor<>("pickup/morepickups.png", Texture.class);

    public static final AssetDescriptor<Texture> LessPickupsTexture =
            new AssetDescriptor<>("pickup/lesspickups.png", Texture.class);

    public static final AssetDescriptor<Texture> NegativePointTexture =
            new AssetDescriptor<>("pickup/negativepoint.png", Texture.class);

    public static final AssetDescriptor<Texture> BombTexture =
            new AssetDescriptor<>("pickup/bomb.png", Texture.class);

    public static final AssetDescriptor<Texture> MorePointsTexture =
            new AssetDescriptor<>("pickup/morepoints.png", Texture.class);

    public static final AssetDescriptor<Texture> LessPointsTexture =
            new AssetDescriptor<>("pickup/lesspoints.png", Texture.class);

    public static final AssetDescriptor<Texture> MoreFrequentPickupsTexture =
            new AssetDescriptor<>("pickup/morefrequentpickups.png", Texture.class);

    public static final AssetDescriptor<Texture> LessFrequentPickupsTexture =
            new AssetDescriptor<>("pickup/lessfrequentpickups.png", Texture.class);

    public static final AssetDescriptor<BitmapFont> MonoFont =
            new AssetDescriptor<>("fonts/Mono.fnt", BitmapFont.class);

    public static final AssetDescriptor<Skin> ComicSkin =
            new AssetDescriptor<>("skins/comic/comic-ui.json", Skin.class);
    private final AssetManager _manager = new AssetManager();

    private static Assets instance;

    public static Assets getInstance() {
        if(instance == null) {
            instance = new Assets();
        }
        return instance;
    }
    private Assets() {}

    public void load() {
        _manager.load(HeadTexture);
        _manager.load(BodyTexture);
        _manager.load(BrightTerrainTexture);
        _manager.load(DarkTerrainTexture);
        _manager.load(WallTexture);
        _manager.load(TeleportTexture);
        _manager.load(SandTexture);
        _manager.load(IceTexture);
        _manager.load(PointTexture);
        _manager.load(SpeedUpTexture);
        _manager.load(SpeedDownTexture);
        _manager.load(GodModeTexture);
        _manager.load(MorePickupsTexture);
        _manager.load(LessPickupsTexture);
        _manager.load(DirectionsReverseTexture);
        _manager.load(BombTexture);
        _manager.load(MorePointsTexture);
        _manager.load(LessPointsTexture);
        _manager.load(NegativePointTexture);
        _manager.load(MoreFrequentPickupsTexture);
        _manager.load(LessFrequentPickupsTexture);
        _manager.load(MonoFont);
        _manager.load(ComicSkin);
    }

    public void finishLoading() {
        _manager.finishLoading();
    }

    public <T> T get(AssetDescriptor<T> assetDescriptor) {
        return _manager.get(assetDescriptor);
    }

    @Override
    public void dispose() {
        _manager.dispose();
    }
}
