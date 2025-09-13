package fr.noahboos.essor.component;

import fr.noahboos.essor.component.challenge.Challenges;

public class EquipmentLevelingData {
    private int prestige;
    private int requiredLevelToPrestige;
    private int level;
    private float totalExperienceMultiplier;
    private float prestigeExperienceMultiplier;
    private float challengeExperienceMultiplier;
    private int requiredExperienceToLevelUp;
    private float currentExperience;
    private Challenges challenges;

    // Constructeur de la classe
    public EquipmentLevelingData() {
        this.prestige = 0;
        this.requiredLevelToPrestige = 10;
        this.level = 0;
        this.totalExperienceMultiplier = 1f;
        this.prestigeExperienceMultiplier = 0f;
        this.challengeExperienceMultiplier = 0f;
        this.requiredExperienceToLevelUp = 100;
        this.currentExperience = 0f;
        this.challenges = new Challenges();
    }

    public EquipmentLevelingData(int prestige, int requiredLevelToPrestige, int level, float totalExperienceMultiplier, float prestigeExperienceMultiplier, float challengeExperienceMultiplier, int requiredExperienceToLevelUp, float currentExperience, Challenges challenges) {
        this.prestige = prestige;
        this.requiredLevelToPrestige = requiredLevelToPrestige;
        this.level = level;
        this.totalExperienceMultiplier = totalExperienceMultiplier;
        this.prestigeExperienceMultiplier = prestigeExperienceMultiplier;
        this.challengeExperienceMultiplier = challengeExperienceMultiplier;
        this.requiredExperienceToLevelUp = requiredExperienceToLevelUp;
        this.currentExperience = currentExperience;
        this.challenges = challenges;
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
    public float GetTotalExperienceMultiplier() {
        return this.totalExperienceMultiplier;
    }
    public void SetTotalExperienceMultiplier() {
        this.totalExperienceMultiplier = 1.0f + this.prestigeExperienceMultiplier + this.challengeExperienceMultiplier;
    }

    public float GetPrestigeExperienceMultiplier() {
        return this.prestigeExperienceMultiplier;
    }

    public void SetPrestigeExperienceMultiplier(float prestigeExperienceMultiplier) {
        this.prestigeExperienceMultiplier = prestigeExperienceMultiplier;
    }

    public float GetChallengeExperienceMultiplier() {
        return this.challengeExperienceMultiplier;
    }

    public void SetChallengeExperienceMultiplier(float challengeExperienceMultiplier) {
        this.challengeExperienceMultiplier = challengeExperienceMultiplier;
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

    // Getter et setter de challenges.
    public Challenges GetChallenges() {
        return this.challenges;
    }
    public void SetChallenges(Challenges challenges) {
        this.challenges = challenges;
    }
}