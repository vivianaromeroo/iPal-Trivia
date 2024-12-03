package com.example.trivia;
import java.util.ArrayList;
import java.util.List;

public class TriviaQuestions {
    List<TriviaQuestion> questions = new ArrayList<>();
    // List to hold all the questions and answers
    public TriviaQuestions() {

        // Add trivia questions
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
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public TriviaQuestion getQuestion(int index) { return questions.get(index); }

    public int getSize() {
        return questions.size();
    }
}

