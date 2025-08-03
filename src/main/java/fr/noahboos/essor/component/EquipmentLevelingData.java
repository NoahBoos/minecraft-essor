package fr.noahboos.essor.component;

public class EquipmentLevelingData {
    private int level;
    private int levelExperienceThreshold;
    private float currentExperience;

    // Constructeur de la classe
    public EquipmentLevelingData() {
        this.level = 1;
        this.levelExperienceThreshold = 100;
        this.currentExperience = 0f;
    }

    public EquipmentLevelingData(int level, int levelExperienceThreshold, float currentExperience) {
        this.level = level;
        this.levelExperienceThreshold = levelExperienceThreshold;
        this.currentExperience = currentExperience;
    }

    // Getter et setter de level.
    public int GetLevel() {
        return level;
    }
    public void SetLevel(int level) {
        this.level = level;
    }

    // Getter et setter de levelExperienceThreshold.
    public int GetLevelExperienceThreshold() {
        return levelExperienceThreshold;
    }

    public void SetLevelExperienceThreshold(int levelExperienceThreshold) {
        this.levelExperienceThreshold = levelExperienceThreshold;
    }

    // Getter et setter de currentExperience.
    public float GetCurrentExperience() {
        return currentExperience;
    }

    public void SetCurrentExperience(float currentExperience) {
        this.currentExperience = currentExperience;
    }
}
