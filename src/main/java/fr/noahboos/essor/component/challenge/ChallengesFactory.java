package fr.noahboos.essor.component.challenge;

import fr.noahboos.essor.component.EquipmentLevelingData;
import fr.noahboos.essor.component.ModDataComponentTypes;
import fr.noahboos.essor.utils.Constants;
import net.minecraft.world.item.*;

public class ChallengesFactory {
    public static void AssignChallenges(ItemStack item) {
        if (!Constants.UPGRADABLE_ITEM_CLASSES_NO_ARMOUR.contains(item.getItem().getClass())) {
            return;
        }
        EquipmentLevelingData data = item.getComponents().get(ModDataComponentTypes.DC_EQUIPMENT_LEVELING_DATA);

        if (data == null) {
            return;
        }

        if (data.GetChallenges() != null && !data.GetChallenges().challenges.isEmpty()) {
            return;
        }

        Item itemType = item.getItem();

        switch (itemType) {
            case AxeItem axeItem -> data.SetChallenges(AddChallengeToAxe());
            case BowItem bowItem -> data.SetChallenges(AddChallengeToRangedWeapon());
            case CrossbowItem crossbowItem -> data.SetChallenges(AddChallengeToRangedWeapon());
            case HoeItem hoeItem -> data.SetChallenges(AddChallengeToHoe());
            case MaceItem maceItem -> data.SetChallenges(AddChallengeToWeapon());
            case PickaxeItem pickaxeItem -> data.SetChallenges(AddChallengeToPickaxe());
            case ShovelItem shovelItem -> data.SetChallenges(AddChallengeToShovel());
            case ShieldItem shieldItem -> data.SetChallenges(AddChallengeToWeapon());
            case SwordItem swordItem -> data.SetChallenges(AddChallengeToWeapon());
            case TridentItem tridentItem -> data.SetChallenges(AddChallengeToTrident());
            default -> {}
        }
    }

    private static Challenges AddBossChallenges() {
        Challenges challenges = new Challenges();
        challenges.AddChallenge(new Challenge("kill-ender-dragon"));
        challenges.AddChallenge(new Challenge("kill-elder-guardian"));
        challenges.AddChallenge(new Challenge("kill-warden"));
        challenges.AddChallenge(new Challenge("kill-wither"));
        return challenges;
    }

    private static Challenges AddChallengeToWeapon() {
        Challenges challenges = AddBossChallenges();
        challenges.AddChallenge(new Challenge("kill-humans"));;
        challenges.AddChallenge(new Challenge("kill-zombies"));
        challenges.AddChallenge(new Challenge("kill-skeletons"));
        challenges.AddChallenge(new Challenge("kill-spiders"));
        challenges.AddChallenge(new Challenge("kill-creepers"));
        return challenges;
    }

    private static Challenges AddChallengeToRangedWeapon() {
        Challenges challenges = AddChallengeToWeapon();
        challenges.AddChallenge(new Challenge("kill-flyers"));
        return challenges;
    }

    private static Challenges AddChallengeToTrident() {
        Challenges challenges = AddChallengeToWeapon();
        challenges.AddChallenge(new Challenge("kill-guardians"));
        return challenges;
    }

    private static Challenges AddChallengeToTool() {
        Challenges challenges = new Challenges();
        challenges.AddChallenge(new Challenge("break-blocks"));
        return challenges;
    }

    private static Challenges AddChallengeToPickaxe() {
        Challenges challenges = AddChallengeToTool();
        challenges.AddChallenge(new Challenge("break-coal-ore"));
        challenges.AddChallenge(new Challenge("break-copper-ore"));
        challenges.AddChallenge(new Challenge("break-iron-ore"));
        challenges.AddChallenge(new Challenge("break-gold-ore"));
        challenges.AddChallenge(new Challenge("break-redstone-ore"));
        challenges.AddChallenge(new Challenge("break-lapis-ore"));
        challenges.AddChallenge(new Challenge("break-diamond-ore"));
        challenges.AddChallenge(new Challenge("break-emerald-ore"));
        challenges.AddChallenge(new Challenge("break-ancient-debris"));
        return challenges;
    }

    private static Challenges AddChallengeToAxe() {
        Challenges challenges = AddChallengeToTool();
        challenges.AddChallenge(new Challenge("break-oak"));
        challenges.AddChallenge(new Challenge("break-birch"));
        challenges.AddChallenge(new Challenge("break-dark-oak"));
        challenges.AddChallenge(new Challenge("break-pale-oak"));
        challenges.AddChallenge(new Challenge("break-spruce"));
        challenges.AddChallenge(new Challenge("break-acacia"));
        challenges.AddChallenge(new Challenge("break-jungle"));
        challenges.AddChallenge(new Challenge("break-cherry"));
        challenges.AddChallenge(new Challenge("break-mangrove"));
        challenges.AddChallenge(new Challenge("break-crimson"));
        challenges.AddChallenge(new Challenge("break-warped"));
        return challenges;
    }

    private static Challenges AddChallengeToShovel() {
        Challenges challenges = AddChallengeToTool();
        challenges.AddChallenge(new Challenge("break-dirt"));
        challenges.AddChallenge(new Challenge("break-podzol"));
        challenges.AddChallenge(new Challenge("break-mycelium"));
        challenges.AddChallenge(new Challenge("break-sand"));
        challenges.AddChallenge(new Challenge("break-gravel"));
        challenges.AddChallenge(new Challenge("break-clay"));
        challenges.AddChallenge(new Challenge("break-soul-sand"));
        challenges.AddChallenge(new Challenge("break-snow"));
        return challenges;
    }

    private static Challenges AddChallengeToHoe() {
        Challenges challenges = AddChallengeToTool();
        challenges.AddChallenge(new Challenge("till-dirt"));
        challenges.AddChallenge(new Challenge("break-warped-wart"));
        challenges.AddChallenge(new Challenge("break-nether-wart"));
        challenges.AddChallenge(new Challenge("break-shroomlight"));
        return challenges;
    }
}
