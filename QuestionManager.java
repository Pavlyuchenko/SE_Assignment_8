
/**
 * Group 29
 * Michal Pavlíček, Luuk Dobbelaar
 * i6306065, i6331748
 */

import java.util.ArrayList;

public class QuestionManager {
    /*
     * There are 4 (number of categories) arrays, one per category. Each one holds
     * QUESTIONS_PER_CATEGORY questions
     */
    private ArrayList<ArrayList<String>> questions = new ArrayList<>();

    public void createQuestions(String[] questionCategories, int questionsPerCategory) {
        /*
         * this allows to increase the number of categories without needing to change
         * the code
         */
        ArrayList<ArrayList<String>> questions = new ArrayList<>();

        for (int i = 0; i < questionCategories.length; i++) {
            ArrayList<String> questionNames = new ArrayList<>();

            for (int j = 1; j <= questionsPerCategory; j++) {
                questionNames.add(questionCategories[i] + " Question " + j);
            }

            questions.add(questionNames);
        }

        this.questions = questions;
    }

    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }

    public String askQuestion(Player currentPlayer, int questionCategoriesLength) {
        int currCategoryIndex = currentPlayer.getPosition() % questionCategoriesLength;
        int lastQuestionIndex = questions.get(currCategoryIndex).size() - 1;

        if (lastQuestionIndex < 0) {
            return "Ran out of questions!";
        }

        String question = this.getQuestions().get(currCategoryIndex)
                .remove(lastQuestionIndex);

        System.out.println(questions);

        return question;
    }

}
