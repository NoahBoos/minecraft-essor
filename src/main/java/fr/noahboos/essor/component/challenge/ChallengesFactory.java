package fr.noahboos.essor.component.challenge;

public class ChallengesFactory {
    public static Challenges AddBossChallenges() {
        Challenges challenges = new Challenges();
        challenges.AddChallenge(new Challenge("kill-ender-dragon"));
        challenges.AddChallenge(new Challenge("kill-elder-guardian"));
        challenges.AddChallenge(new Challenge("kill-warden"));
        challenges.AddChallenge(new Challenge("kill-wither"));
        return challenges;
    }

    public static Challenges AddChallengeToWeapon() {
        Challenges challenges = AddBossChallenges();
        challenges.AddChallenge(new Challenge("kill-humans"));;
        challenges.AddChallenge(new Challenge("kill-zombies"));
        challenges.AddChallenge(new Challenge("kill-skeletons"));
        challenges.AddChallenge(new Challenge("kill-spiders"));
        challenges.AddChallenge(new Challenge("kill-creepers"));
        return challenges;
    }

    public static Challenges AddChallengeToRangedWeapon() {
        Challenges challenges = AddChallengeToWeapon();
        challenges.AddChallenge(new Challenge("kill-flyers"));
        return challenges;
    }

    public static Challenges AddChallengeToTrident() {
        Challenges challenges = AddChallengeToWeapon();
        challenges.AddChallenge(new Challenge("kill-guardians"));
        return challenges;
    }

    public static Challenges AddChallengeToTool() {
        Challenges challenges = new Challenges();
        challenges.AddChallenge(new Challenge("break-blocks.json"));
        return challenges;
    }

    public static Challenges AddChallengeToPickaxe() {
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

    public static Challenges AddChallengeToAxe() {
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

    public static Challenges AddChallengeToShovel() {
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

    public static Challenges AddChallengeToHoe() {
        Challenges challenges = AddChallengeToTool();
        challenges.AddChallenge(new Challenge("till-dirt"));
        challenges.AddChallenge(new Challenge("break-warped-wart"));
        challenges.AddChallenge(new Challenge("break-nether-wart"));
        challenges.AddChallenge(new Challenge("break-shroomlight"));
        return challenges;
    }
}
