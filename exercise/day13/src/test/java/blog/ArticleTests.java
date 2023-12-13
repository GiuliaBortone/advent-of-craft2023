package blog;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {
    public static final String AUTHOR = "Pablo Escobar";
    private static final String COMMENT_TEXT = "Amazing article !!!";
    private static final LocalDate anyDate = LocalDate.of(2023, 12, 13);

    @Test
    void should_add_comment_in_an_article() throws CommentAlreadyExistException {
        Article article = new Article("any name", "any comment");
        Comment newComment = new Comment(COMMENT_TEXT, AUTHOR, anyDate);

        assertThat(article.add(newComment)).isEqualTo(newComment);
    }

    @Test
    void should_add_comment_in_an_article_containing_already_a_comment() throws CommentAlreadyExistException {
        ArrayList<Comment> comments = new ArrayList<>(List.of(new Comment(COMMENT_TEXT, AUTHOR, anyDate)));
        Article article = new Article("any name", "any content", comments);
        Comment newComment = new Comment("Finibus Bonorum et Malorum", "Al Capone", anyDate);

        assertThat(article.add(newComment)).isEqualTo(newComment);
    }

    @Nested
    class Fail {
        @Test
        void when_adding_an_existing_comment() {
            Comment duplicateComment = new Comment(COMMENT_TEXT, AUTHOR, anyDate);
            ArrayList<Comment> comments = new ArrayList<>(List.of(duplicateComment));
            Article article = new Article("any name", "any content", comments);

            assertThatThrownBy(() -> {
                article.add(duplicateComment);
            }).isInstanceOf(CommentAlreadyExistException.class);
        }
    }
}
