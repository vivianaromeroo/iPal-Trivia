package com.example.trivia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TriviaQuestions {
    List<TriviaQuestion> questions = new ArrayList<>();
    Random random = new Random();

    // List to hold all the questionSet1 and answers
    public TriviaQuestions() {
        int randomNumber = random.nextInt(100) % 3;

        if (randomNumber == 0) {
            questions.add(new TriviaQuestion("A snail can sleep for three years at a time.", "True", "True, some snails can hibernate for extended periods, up to three years!"));
            questions.add(new TriviaQuestion("Polar bears have black skin and actually camouflage by covering themselves in snow.", "False", "False, polar bears do have black skin, but their fur naturally appears white to blend into the snowy environment."));
            questions.add(new TriviaQuestion("Pineapples take around two years to grow.", "True", "True, the mighty pineapple takes its sweet time!"));
            questions.add(new TriviaQuestion("In Switzerland, it’s illegal to own just one guinea pig.", "True", "True, they get lonely, so it’s against the law to have just one!"));
            questions.add(new TriviaQuestion("Octopuses are famous escape artists and have been known to climb out of their tanks at night to explore.", "True", "True, some clever octopuses have even unlocked tank lids to make their great escapes!"));
            questions.add(new TriviaQuestion("The inventor of the light bulb, Thomas Edison, was afraid of the dark.", "False", "False, although this would be ironic, there’s no evidence that Edison feared the dark."));
            questions.add(new TriviaQuestion("Cows have best friends and get stressed when they’re separated.", "True", "True, studies have shown cows have strong social bonds!"));
            questions.add(new TriviaQuestion("It’s possible to sneeze with your eyes open.", "False", "False, it’s practically impossible due to reflexes keeping your eyes shut to protect them!"));
            questions.add(new TriviaQuestion("If you lift a kangaroo’s tail off the ground, it can’t hop.", "True", "True, kangaroos rely on their tail for balance."));
            questions.add(new TriviaQuestion("Lobsters can communicate by peeing out of their faces.", "True", "True, they use specialized glands near their eyes to mark territory and communicate."));
        } else if (randomNumber == 1) {
            questions.add(new TriviaQuestion("Bananas are berries, but strawberries are not.", "True", "True, botanically speaking, bananas meet the criteria for berries while strawberries do not!"));
            questions.add(new TriviaQuestion("Goldfish have a memory span of only three seconds.", "False", "False, goldfish can actually remember things for weeks or even months!"));
            questions.add(new TriviaQuestion("Sharks are the only fish that can blink with both eyes.", "True", "True, sharks have eyelids that allow them to blink, unlike other fish."));
            questions.add(new TriviaQuestion("Humans share 50% of their DNA with bananas.", "True", "True, at a molecular level, we share about half of our DNA with bananas."));
            questions.add(new TriviaQuestion("You can hear a blue whale's heartbeat from over 2 miles away.", "True", "True, the blue whale’s massive heart produces powerful sounds!"));
            questions.add(new TriviaQuestion("The Great Wall of China is visible from space.", "False", "False, while impressive, the wall is not visible to the naked eye from space."));
            questions.add(new TriviaQuestion("The Eiffel Tower can grow up to 15 centimeters taller in the summer.", "True", "True, the metal expands due to heat, making it grow temporarily!"));
            questions.add(new TriviaQuestion("Lightning never strikes the same place twice.", "False", "False, lightning often strikes the same location multiple times, especially tall structures."));
            questions.add(new TriviaQuestion("An octopus has three hearts and blue blood.", "True", "True, two hearts pump blood to the gills and one to the rest of the body, with copper-rich blue blood."));
            questions.add(new TriviaQuestion("Pigeons can recognize themselves in mirrors.", "True", "True, pigeons are one of the few animals with self-recognition abilities!"));
        }
        else {
            questions.add(new TriviaQuestion("Sloths can hold their breath longer than dolphins.", "True", "True, sloths can slow their heart rate and hold their breath for up to 40 minutes!"));
            questions.add(new TriviaQuestion("The average cloud weighs about a million pounds.", "True", "True, despite floating, clouds are incredibly dense and heavy!"));
            questions.add(new TriviaQuestion("Humans are the only animals that blush.", "True", "True, blushing is a unique human response caused by social emotions."));
            questions.add(new TriviaQuestion("Water spirals down a drain differently in the Northern and Southern Hemispheres.", "False", "False, the Coriolis effect doesn’t influence small-scale water drainage."));
            questions.add(new TriviaQuestion("Some mushrooms can glow in the dark.", "True", "True, bioluminescent fungi like foxfire glow to attract insects and spread spores!"));
            questions.add(new TriviaQuestion("A group of flamingos is called a flamboyance.", "True", "True, fittingly, these colorful birds are called a flamboyance when grouped."));
            questions.add(new TriviaQuestion("Owls can turn their heads a full 360 degrees.", "False", "False, owls can rotate their heads about 270 degrees, but not a full circle."));
            questions.add(new TriviaQuestion("The fingerprints of a koala are so similar to humans that they can confuse crime scene investigations.", "True", "True, koala prints are nearly indistinguishable from human ones under a microscope!"));
            questions.add(new TriviaQuestion("Bees can recognize human faces.", "True", "True, bees use a technique called ‘configural processing’ to remember faces!"));
            questions.add(new TriviaQuestion("Venus is the hottest planet in our solar system.", "True", "True, Venus’s thick atmosphere traps heat, making it even hotter than Mercury!"));
        }
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public TriviaQuestion getQuestion(int index) { return questions.get(index); }

    public int getSize() {
        return questions.size();
    }
}

