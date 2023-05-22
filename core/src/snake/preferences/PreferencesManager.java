package snake.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {
    private static PreferencesManager instance;
    public static PreferencesManager getInstance() {
        if(instance == null) {
            instance = new PreferencesManager();
        }
        return instance;
    }
    private PreferencesManager() {
        resetIfIncorrectValues();
    }

    private final Preferences _preferences = Gdx.app.getPreferences("options");
    private final String minCoolDownKey = "minCoolDown", maxCoolDownKey = "maxCoolDown",
            initialCoolDownKey = "initialCoolDown";

    public final float minCoolDownLimit = 0.01f, maxCoolDownLimit = 0.5f, minCoolDownDefault = 0.05f,
            maxCoolDownDefault = 0.2f, initialCoolDownDefault = 0.12f;

    private void resetIfIncorrectValues()
    {
        var minCoolDown = _preferences.getFloat(minCoolDownKey);
        var maxCoolDown = _preferences.getFloat(maxCoolDownKey);
        var initialCoolDown = _preferences.getFloat(initialCoolDownKey);
        if(minCoolDown < minCoolDownLimit) {
            reset();
            return;
        }
        if(maxCoolDown > maxCoolDownLimit) {
            reset();
            return;
        }
        if(initialCoolDown < minCoolDown || initialCoolDown > maxCoolDown) {
            reset();
        }
    }

    private void reset() {
        _preferences.putFloat(minCoolDownKey, minCoolDownDefault);
        _preferences.putFloat(maxCoolDownKey, maxCoolDownDefault);
        _preferences.putFloat(initialCoolDownKey, initialCoolDownDefault);
        _preferences.flush();
    }

    public float getMinCoolDown() {
        return _preferences.getFloat(minCoolDownKey);
    }

    public float getMaxCoolDown() {
        return _preferences.getFloat(maxCoolDownKey);
    }

    public float getInitialCoolDown() {
        return _preferences.getFloat(initialCoolDownKey);
    }

    public void setMinCoolDown(float value) {
        putFloatAndFlush(minCoolDownKey, value);
    }

    public void setMaxCoolDown(float value) {
        putFloatAndFlush(maxCoolDownKey, value);
    }

    public void setInitialCoolDown(float value) {
        putFloatAndFlush(initialCoolDownKey, value);
    }

    private void putFloatAndFlush(String key, float value) {
        _preferences.putFloat(key, value);
        _preferences.flush();
    }
}
