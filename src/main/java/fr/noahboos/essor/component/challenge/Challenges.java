package fr.noahboos.essor.component.challenge;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.List;

public class Challenges {
    public List<Challenge> challenges;

    public Challenges() {
        this.challenges = new ArrayList<>();
    }

    public Challenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public void AddChallenge(Challenge challenge) {
        this.challenges.add(challenge);
    }

    public void UpdateChallenge(String id, int amount) {
        for (Challenge challenge : this.challenges) {
            if (challenge.id.equals(id)) {
                challenge.IncrementProgress(amount);
                break;
            }
        }
    }

    public List<Challenge> GetChallenges() {
        return this.challenges;
    }

    public static final Codec<Challenges> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(Challenge.CODEC)
                    .fieldOf("challenges")
                    .forGetter(Challenges::GetChallenges)
    ).apply(instance, Challenges::new));
}
