package fr.noahboos.essor.component;

public class ExperienceHandler {
    void AddExperience(EquipmentLevelingData data, float experienceToAdd) {
        data.SetCurrentExperience(data.GetCurrentExperience() + experienceToAdd);
        while (data.GetCurrentExperience() >= data.GetLevelExperienceThreshold()) {
            LevelUp(data);
        }
    }

    void LevelUp(EquipmentLevelingData data) {
        data.SetLevel(data.GetLevel() + 1);
        data.SetCurrentExperience(data.GetCurrentExperience() - data.GetLevelExperienceThreshold());
        data.SetLevelExperienceThreshold(data.GetLevelExperienceThreshold() + 100);
    }
}
