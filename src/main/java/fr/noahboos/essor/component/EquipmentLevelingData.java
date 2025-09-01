package fr.noahboos.essor.component;

public class EquipmentLevelingData {
    private int prestige;
    private int requiredLevelToPrestige;
    private int level;
    private float experienceMultiplier;
    private int requiredExperienceToLevelUp;
    private float currentExperience;

    // Constructeur de la classe
    public EquipmentLevelingData() {
        this.prestige = 0;
        this.requiredLevelToPrestige = 10;
        this.level = 0;
        this.experienceMultiplier = 1f;
        this.requiredExperienceToLevelUp = 100;
        this.currentExperience = 0f;
    }

    public EquipmentLevelingData(int prestige, int requiredLevelToPrestige, int level, float experienceMultiplier, int requiredExperienceToLevelUp, float currentExperience) {
        this.prestige = prestige;
        this.requiredLevelToPrestige = requiredLevelToPrestige;
        this.level = level;
        this.experienceMultiplier = experienceMultiplier;
        this.requiredExperienceToLevelUp = requiredExperienceToLevelUp;
        this.currentExperience = currentExperience;
    }

    // Getter et setter de prestige.
    public int GetPrestige() {
        return this.prestige;
    }
    public void SetPrestige(int prestige) {
        this.prestige = prestige;
    }

    // Getter et setter de prestigeLevelThreshold.
    public int GetRequiredLevelToPrestige() {
        return this.requiredLevelToPrestige;
    }
    public void SetRequiredLevelToPrestige(int requiredLevelToPrestige) {
        this.requiredLevelToPrestige = requiredLevelToPrestige;
    }

    // Getter et setter de level.
    public int GetLevel() {
        return level;
    }
    public void SetLevel(int level) {
        this.level = level;
    }

    // Getter et setter d'experienceMultiplier.
    public float GetExperienceMultiplier() {
        return this.experienceMultiplier;
    }
    public void SetExperienceMultiplier(float experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    // Getter et setter de levelExperienceThreshold.
    public int GetRequiredExperienceToLevelUp() {
        return requiredExperienceToLevelUp;
    }

    public void SetRequiredExperienceToLevelUp(int requiredExperienceToLevelUp) {
        this.requiredExperienceToLevelUp = requiredExperienceToLevelUp;
    }

    // Getter et setter de currentExperience.
    public float GetCurrentExperience() {
        return currentExperience;
    }

    public void SetCurrentExperience(float currentExperience) {
        this.currentExperience = currentExperience;
    }
}