package blog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {

    private final String text = "Amazing article !!!";
    private final String differentText = "Not amazing article !!!";
    private final String author = "Pablo Escobar";

    private Article article;
    @BeforeEach
    void articleCreation() {
        article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
    }

    @Test
    void itShouldAddOneCommentToArticleWithGivenTextAndAuthor() throws CommentAlreadyExistException {
        article.addComment(text, author);
        assertThat(article.getComments()).hasSize(1);
        assertThat(article.getComments().get(0).text()).isEqualTo(text);
        assertThat(article.getComments().get(0).author()).isEqualTo(author);
    }

    @Test
    void itShouldAddASecondCommentWhenOneDifferentCommentAlreadyPresent() throws CommentAlreadyExistException {
        article.addComment(text, author);
        article.addComment(differentText, author);

        assertThat(article.getComments()).hasSize(2);
        assertThat(article.getComments().getLast().text()).isEqualTo(differentText);
        assertThat(article.getComments().getLast().author()).isEqualTo(author);
    }

    @Test
    void itShouldThrowExceptionWhenAddingSecondCommentWhenSameCommentAlreadyPresent() throws CommentAlreadyExistException {
        article.addComment(text, author);

        assertThatThrownBy(() -> {
            article.addComment(text, author);
        }).isInstanceOf(CommentAlreadyExistException.class);
    }
}
