import java.util.ArrayList;

public class QuestionManager {
    /*
     * There are 4 (number of categories) arrays, one per category. Each one holds
     * QUESTIONS_PER_CATEGORY questions
     */
    private ArrayList<ArrayList<String>> questions = new ArrayList<>();

    public ArrayList<ArrayList<String>> createQuestions(String[] questionCategories, int questionsPerCategory) {
        /*
         * this allows to increase the number of categories without needing to change
         * the code
         */
        ArrayList<ArrayList<String>> questions = new ArrayList<>();

        for (int i = 0; i < questionCategories.length; i++) {
            ArrayList<String> questionNames = new ArrayList<>();

            for (int j = 0; j < questionsPerCategory; j++) {
                questionNames.add(questionCategories[i] + " Question " + j);
            }

            questions.add(questionNames);
        }

        return questions;
    }

    public ArrayList<ArrayList<String>> getQuestions() {
        return questions;
    }

    public void askQuestion(Player currentPlayer, QuestionManager questionManager, int questionCategoriesLengthh) {
        int currCategoryIndex = currentPlayer.getPosition() % questionCategoriesLengthh;
        int lastQuestionIndex = questions.get(currCategoryIndex).size() - 1;

        String question = questionManager.getQuestions().get(currCategoryIndex)
                .remove(lastQuestionIndex);

        System.out.println(question);
    }

}
