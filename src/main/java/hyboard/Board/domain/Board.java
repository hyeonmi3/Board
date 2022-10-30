package hyboard.Board.domain;

import java.time.LocalDateTime;

public class Board {
    private Long idx;

    private String title;

    private String content;

    private String author;

    private Integer readCount;

    private LocalDateTime createdAt;

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Board() {
    }

    public Board(Long idx, String title, String content, String author, int readCount, LocalDateTime createdAt) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.author = author;
        this.readCount = readCount;
        this.createdAt = createdAt;
    }
}