package fr.noahboos.essor.component.challenge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.noahboos.essor.loader.JsonLoader;

import java.util.*;

public class Challenge {
    public String id;
    public Map<Integer, Integer> tiers;
    public Integer maximumTier;
    public Integer currentTier;
    public Integer progression;
    public Set<String> targets;
    public boolean isCompleted;

    public Challenge() {

    }

    public Challenge(String pathToChallengeJSON) {
        Challenge challengeBuffer = JsonLoader.LoadChallenge(pathToChallengeJSON);

        if (challengeBuffer == null) {
            System.out.println("Failed to load challenge data from " + pathToChallengeJSON);
            return;
        }

        this.id = challengeBuffer.id;
        this.tiers = challengeBuffer.tiers;
        this.maximumTier = challengeBuffer.maximumTier;
        this.currentTier = challengeBuffer.currentTier;
        this.progression = challengeBuffer.progression;
        this.targets = challengeBuffer.targets;
        this.isCompleted = challengeBuffer.isCompleted;
    }

    public void IncrementProgress(int amount) {
        if (!isCompleted) {
            this.progression += amount;
            if (this.progression >= this.tiers.get(this.currentTier)) {
                this.currentTier += 1;
                if (this.currentTier.equals(this.maximumTier)) {
                    this.isCompleted = true;
                } else {
                    this.progression -= this.tiers.get(this.currentTier);
                }
            }
        }
    }

    public boolean IsTarget(String targetId) {
        return this.targets.contains(targetId);
    }

    public String GetId() {
        return this.id;
    }

    public Map<Integer, Integer> GetTiers() {
        return this.tiers;
    }

    public Integer GetMaximumTier() {
        return this.maximumTier;
    }

    public Integer GetCurrentTier() {
        return this.currentTier;
    }

    public Integer GetProgression() {
        return this.progression;
    }

    public Set<String> GetTargets() {
        return this.targets;
    }

    public boolean IsCompleted() {
        return this.isCompleted;
    }

    public static final Codec<Challenge> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(challenge -> challenge.id),
            Codec.unboundedMap(Codec.STRING, Codec.INT).fieldOf("tiers").forGetter(challenge -> {
                Map<String, Integer> tiersMap = new HashMap<String, Integer>();
                challenge.tiers.forEach((key, value) -> tiersMap.put(String.valueOf(key), value));
                return tiersMap;
            }),
            Codec.INT.fieldOf("maximumTier").forGetter(c -> c.maximumTier),
            Codec.INT.fieldOf("currentTier").forGetter(c -> c.currentTier),
            Codec.INT.fieldOf("progression").forGetter(c -> c.progression),
            Codec.STRING.listOf().fieldOf("targets")
                    .forGetter(c -> c.targets != null ? new ArrayList<>(c.targets) : new ArrayList<>()),
            Codec.BOOL.fieldOf("isCompleted").forGetter(c -> c.isCompleted)
    ).apply(instance, (id, tiers, maxTier, curTier, prog, targets, completed) -> {
        Challenge challenge = new Challenge();
        challenge.id = id;
        Map <Integer, Integer> tiersMap = new HashMap<Integer, Integer>();
        tiers.forEach((key, value) -> {tiersMap.put(Integer.valueOf(key), value);});
        challenge.tiers = tiersMap;
        challenge.maximumTier = maxTier;
        challenge.currentTier = curTier;
        challenge.progression = prog;
        challenge.targets = new HashSet<>(targets);
        challenge.isCompleted = completed;
        return challenge;
    }));
}
