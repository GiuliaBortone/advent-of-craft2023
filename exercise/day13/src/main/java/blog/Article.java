package blog;

import java.util.ArrayList;

public class Article {
    private final String name;
    private final String content;
    private final ArrayList<Comment> comments;

    public Article(String name, String content) {
        this(name, content, new ArrayList<>());
    }

    public Article(String name, String content, ArrayList<Comment> comments) {
        this.name = name;
        this.content = content;
        this.comments = comments;
    }

    public Comment add(Comment comment) throws CommentAlreadyExistException {
        if (comments.contains(comment))
            throw new CommentAlreadyExistException();

        comments.add(comment);
        return comment;
    }
}

